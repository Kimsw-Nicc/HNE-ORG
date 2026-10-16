package egovframework.nicc.hne.com.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.auth.model.dto.AuthUserDTO;
import egovframework.nicc.hne.auth.model.enumeration.Role;
import egovframework.nicc.hne.auth.repo.AuthUserRepo;
import egovframework.nicc.hne.com.model.dto.ComOrgDTO;
import egovframework.nicc.hne.com.model.dto.ComOrgTreeDTO;
import egovframework.nicc.hne.com.model.entity.ComOrg;
import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.com.repo.ComStaffRepo;
import egovframework.nicc.hne.com.repo.ComTeamRepo;
import egovframework.nicc.hne.com.service.OrgService;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class OrgService {

	@Value("#{basic['code.root']}")
	public String ROOT_CODE;
	@Value("#{basic['code.locale']}")
	public String LOCALE_CODE;
	@Value("#{basic['code.parliament']}")
	public String PARLIAMENT_CODE;

	String ROOT_NODE_ICON = "/resources/js/root_node.png";
	String SECOND_NODE_ICON = "/resources/js/second_node.png";
	String THIRD_NODE_ICON = "/resources/js/third_node.png";

	@Autowired
	ComOrgRepo orgRepo;
	@Autowired
	ComTeamRepo teamRepo;
	@Autowired
	ComStaffRepo staffRepo;

	@Autowired
	AuthUserRepo authUserRepo;

	@Autowired
	ModelConverter modelConverter;

	// fullname을 가공
	private void splitFullnameAll(List<ComOrgDTO> orgDTOs) {
		for (ComOrgDTO orgDTO : orgDTOs) {
			splitFullname(orgDTO);
		}
	}

	// 데이터를 표출시 fullname을 가공
	private void splitFullname(ComOrgDTO orgDTO) {
		String[] fullname = orgDTO.getOrgFullname().split(" ");
		fullname[0] = "[" + fullname[0] + "]";
		orgDTO.setOrgFullname(String.join(" ", fullname));
	}

	private List<ComOrg> makeOrgList(String rootCode) {
		List<ComOrg> comOrgs = orgRepo.findAll();

		Map<String, ComOrg> orgMap = new HashMap<String, ComOrg>();
		for (ComOrg comOrg : comOrgs) {
			orgMap.put(comOrg.getOrgCd(), comOrg);
		}
		// Map<orgUprCd, comOrgs>
		Map<String, List<ComOrg>> tree = new HashMap<String, List<ComOrg>>();
		for (ComOrg comOrg : comOrgs) {
			List<ComOrg> children = tree.get(comOrg.getOrgUprCd());
			if (children == null) { // 하위 부서가 없다면 빈 List를 넣어줌
				children = new ArrayList<ComOrg>();
				tree.put(comOrg.getOrgUprCd(), children);
			}
			children.add(comOrg);
		}
		//
		for (Map.Entry<String, List<ComOrg>> entry : tree.entrySet()) {
			Collections.sort(entry.getValue(), new Comparator<ComOrg>() {
				public int compare(ComOrg o1, ComOrg o2) {
					return Integer.compare(o1.getOrgSort(), o2.getOrgSort());
				}
			});
		}
		//
		List<ComOrg> sortedOrgs = new ArrayList<>();
		buildSortedOrgList(sortedOrgs, tree, rootCode);

		return sortedOrgs;
	}

	private void buildSortedOrgList(List<ComOrg> sortedOrgs, Map<String, List<ComOrg>> tree, String parentOrgCd) {
		List<ComOrg> children = tree.get(parentOrgCd);
		if (children != null) {
			for (ComOrg child : children) {
				sortedOrgs.add(child);
				buildSortedOrgList(sortedOrgs, tree, child.getOrgCd());
			}
		}
	}

	public List<ComOrgTreeDTO> getOrgTree(AuthUserDTO authUserDTO) {
		List<ComOrg> comOrgs = new ArrayList<ComOrg>();
		List<ComOrgTreeDTO> comOrgTreeDTOs = new ArrayList<ComOrgTreeDTO>();
		if (authUserDTO.getUserRole() == Role.ADMIN_ROLE) { // ADMIN이면 다 가져오기
			comOrgs = makeOrgList(ROOT_CODE);
			for (ComOrg comOrg : comOrgs) {
				comOrgTreeDTOs.add(toTree(comOrg, LOCALE_CODE));
			}
		}
		if (authUserDTO.getUserRole() == Role.MANAGER_ROLE) { // MANAGER면 일부만
			comOrgs = makeOrgList(authUserDTO.getOrgUprCd());
			for (ComOrg comOrg : comOrgs) {
				comOrgTreeDTOs.add(toTree(comOrg, authUserDTO.getOrgCd()));
			}
		}

		return comOrgTreeDTOs;
	}

	public List<ComOrgTreeDTO> getOrgTreeParliament() {
		List<ComOrg> orgs = new ArrayList<ComOrg>();
		List<ComOrgTreeDTO> orgTreeDTOs = new ArrayList<ComOrgTreeDTO>();
		orgs = makeOrgList(LOCALE_CODE);
		for (ComOrg org : orgs) {
			orgTreeDTOs.add(toTree(org, PARLIAMENT_CODE));
		}

		return orgTreeDTOs;
	}

	public ComOrgTreeDTO toTree(ComOrg comOrg, String MY_ROOT_CODE) {
		ComOrgTreeDTO comOrgTreeDTO = new ComOrgTreeDTO();
		comOrgTreeDTO.setId(comOrg.getOrgCd());
		comOrgTreeDTO.setText(comOrg.getOrgName());
		comOrgTreeDTO.setSort(comOrg.getOrgSort());

		// 최상위 기관
		if (comOrg.getOrgCd().equals(MY_ROOT_CODE)) {
			comOrgTreeDTO.setParent("#");
			comOrgTreeDTO.setIcon(ROOT_NODE_ICON);
			return comOrgTreeDTO;
		}
		// 상위 기관
		if (comOrg.getOrgUprCd().equals(MY_ROOT_CODE)) {
			comOrgTreeDTO.setParent(MY_ROOT_CODE);
			comOrgTreeDTO.setIcon(SECOND_NODE_ICON);
			return comOrgTreeDTO;
		}
		// 하위 기관
		if (!comOrg.getOrgUprCd().equals(LOCALE_CODE)) {
			comOrgTreeDTO.setParent(comOrg.getOrgUprCd());
			comOrgTreeDTO.setIcon(THIRD_NODE_ICON);
			return comOrgTreeDTO;
		}
		return null;
	}

	public Integer countAllOrg() {
		return orgRepo.findAll().size();
	}

	public List<ComOrgDTO> getOrgs() {
		List<ComOrgDTO> orgDTOs = modelConverter.mapList(makeOrgList(ROOT_CODE), ComOrgDTO.class);
		splitFullnameAll(orgDTOs);
		return orgDTOs;
	}

	public List<ComOrgDTO> getOrgsByOrgUprCd(String orgUprCd) {
		List<ComOrgDTO> orgDTOs = modelConverter.mapList(orgRepo.findByOrgUprCd(orgUprCd), ComOrgDTO.class);
		splitFullnameAll(orgDTOs);
		return orgDTOs;
	}

	public ComOrgDTO getOrg(String ordCd) {
		return modelConverter.map(orgRepo.findOne(ordCd), ComOrgDTO.class);
	}

	public void insertOrg(ComOrgDTO comOrgDTO) {
		ComOrg comOrg = modelConverter.map(comOrgDTO, ComOrg.class);
		comOrg.setOrgDirection(true);
		orgRepo.save(comOrg);
		orgRepo.makeOrgFullname();
	}

	public void updateOrgSort(ComOrgDTO comOrgDTO) {
		ComOrg comOrg = orgRepo.findOne(comOrgDTO.getOrgCd());
		comOrg.setOrgSort(comOrgDTO.getOrgSort());
		orgRepo.save(comOrg);
	}

	public void updateOrgName(ComOrgDTO comOrgDTO) {
		ComOrg comOrg = orgRepo.findOne(comOrgDTO.getOrgCd());
		comOrg.setOrgName(comOrgDTO.getOrgName());
		orgRepo.save(comOrg);
		for (int i = 0; i < 3; i++) {
			orgRepo.makeOrgFullname();
		}
	}

	public void updateOrgEnglishName(ComOrgDTO comOrgDTO) {
		ComOrg comOrg = orgRepo.findOne(comOrgDTO.getOrgCd());
		comOrg.setOrgEnglishName(comOrgDTO.getOrgEnglishName());
		orgRepo.save(comOrg);
	}
	
	public void updateOrgLocation(ComOrgDTO comOrgDTO) {
	  ComOrg comOrg = orgRepo.findOne(comOrgDTO.getOrgCd());
    comOrg.setOrgLocation(comOrgDTO.getOrgLocation());
    orgRepo.save(comOrg);
	}

	public Boolean deleteOrg(String orgCd) {
		// 하위에 팀이 존재하는가?
		if (!orgRepo.findByOrgUprCd(orgCd).isEmpty()) { // 하위 부서가 없다면?
			return false;
		} else if (!teamRepo.findAll(orgCd).isEmpty()) {
			return false;
		}
		orgRepo.delete(orgCd);
		return true;
	}

	public List<String> getContainedOrgCd(String orgCd) {
		if (orgCd == null)
			return null;

		List<String> list = new ArrayList<String>();
		ComOrg comOrg = ComOrg.builder().orgCd("").build();
		String keyword = orgCd;
		while (!comOrg.getOrgCd().equals(LOCALE_CODE)) { // comOrg가 ROOT NODE면 멈추기
			comOrg = orgRepo.findOne(keyword);
			list.add(comOrg.getOrgCd()); // 여기서 ㅔ러!
			keyword = comOrg.getOrgUprCd();
		}
		return list;
	}

	public void sortOrg() {
	}

	public List<ComOrgDTO> getBureaus() {
		List<ComOrgDTO> orgDTOs = modelConverter.mapList(orgRepo.findByOrgUprCd(ROOT_CODE), ComOrgDTO.class);
		splitFullnameAll(orgDTOs);
		return orgDTOs;
	}

	public List<ComOrgDTO> getSections(String orgUprCd) {
		if (orgUprCd == null) {
			orgUprCd = LOCALE_CODE;
		}
		List<ComOrgDTO> orgDTOs = modelConverter.mapList(orgRepo.findByOrgUprCd(orgUprCd), ComOrgDTO.class);
		splitFullnameAll(orgDTOs);
		return orgDTOs;
	}

}
