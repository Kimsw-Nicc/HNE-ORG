package egovframework.nicc.hne.com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.com.model.dto.ComTeamDTO;
import egovframework.nicc.hne.com.model.entity.ComOrg;
import egovframework.nicc.hne.com.model.entity.ComStaff;
import egovframework.nicc.hne.com.model.entity.ComTeam;
import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.com.repo.ComStaffRepo;
import egovframework.nicc.hne.com.repo.ComTeamRepo;
import egovframework.nicc.hne.com.service.TeamService;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class TeamService {
	String[] STAFF_DIR_PAHT = { "resources", "upload", "staff" };
	@Autowired
	ComOrgRepo comOrgRepo;
	@Autowired
	ComTeamRepo comTeamRepo;
	@Autowired
	ComStaffRepo comStaffRepo;

	@Autowired
	ModelConverter modelConverter;

	public Integer countAllTeam(String orgCd) {
		if (orgCd.equals("")) {
			return comTeamRepo.findAll().size();
		} else {
			return comTeamRepo.findAll(orgCd).size();
		}
	}

	public List<ComTeamDTO> getTeams(String orgCd) {
		if (orgCd.equals(""))
			return modelConverter.mapList(comTeamRepo.findAll(), ComTeamDTO.class);
		else
			return modelConverter.mapList(comTeamRepo.findAll(orgCd), ComTeamDTO.class);
	}

	public ComTeamDTO getTeam(String teamCd) {
		return modelConverter.map(comTeamRepo.findOne(teamCd), ComTeamDTO.class);
	}

	public void insertTeam(ComTeamDTO comTeamDTO) throws Exception {
		if (comTeamRepo.findOne(comTeamDTO.getTeamCd()) != null) {
			throw new Exception();
		}
		ComTeam comTeam = modelConverter.map(comTeamDTO, ComTeam.class);
		comTeam.setTeamSync(true);
		comTeamRepo.save(comTeam);
	}

	// NAME
	public void updateTeamName(ComTeamDTO comTeamDTO) {
		ComTeam comTeam = comTeamRepo.findOne(comTeamDTO.getTeamCd());
		comTeam.setTeamName(comTeamDTO.getTeamName());
		comTeamRepo.save(comTeam);
	}

	// SORT
	public void updateTeamSort(ComTeamDTO comTeamDTO) {
		ComTeam comTeam = comTeamRepo.findOne(comTeamDTO.getTeamCd());
		comTeam.setTeamSort(comTeamDTO.getTeamSort());
		comTeamRepo.save(comTeam);
	}

	// INFO
	public void updateTeamInfo(ComTeamDTO comTeamDTO) {
		ComTeam comTeam = comTeamRepo.findOne(comTeamDTO.getTeamCd());
		comTeam.setTeamInfo(comTeamDTO.getTeamInfo());
		comTeamRepo.save(comTeam);
	}

	// VISIBLE
	public void updateTeamVisible(ComTeamDTO comTeamDTO) {
		ComTeam comTeam = comTeamRepo.findOne(comTeamDTO.getTeamCd());
		comTeam.setTeamVisible(!comTeam.getTeamVisible());
		comTeamRepo.save(comTeam);
	}

	// SYNC
	public void updateTeamSync(ComTeamDTO comTeamDTO) {
		ComTeam comTeam = comTeamRepo.findOne(comTeamDTO.getTeamCd());
		comTeam.setTeamSync(!comTeam.getTeamSync());
		comTeamRepo.save(comTeam);
	}

	public void switchTeamVisible(ComTeamDTO comTeamDTO) {
		ComTeam comTeam = comTeamRepo.findOne(comTeamDTO.getTeamCd());
		comTeam.setTeamVisible(!comTeam.getTeamVisible());
		comTeamRepo.save(comTeam);
	}

	public void switchTeamSync(ComTeamDTO comTeamDTO) {
		ComTeam comTeam = comTeamRepo.findOne(comTeamDTO.getTeamCd());
		comTeam.setTeamSync(!comTeam.getTeamSync());
		comTeamRepo.save(comTeam);
	}

	public void deleteTeam(String teamCd) {
		// 해당 팀에 속한 Staff들을 모두 null로 바꿔줘야함
		List<ComStaff> staffs = comStaffRepo.findByTeamCd(teamCd);
		for (ComStaff staff : staffs) {
			staff.setComTeam(null);
		}
		comStaffRepo.save(staffs);
		comTeamRepo.delete(teamCd);
	}

	public void sortTeam(String orgCd) {
		if (!orgCd.equals("")) {
			List<ComTeam> comTeams = comTeamRepo.findAll(orgCd);
			saveSortedTeams(comTeams);
		} else {
			List<ComOrg> comOrgs = comOrgRepo.findAll();
			for (ComOrg comOrg : comOrgs) {
				List<ComTeam> comTeams = comTeamRepo.findAll(comOrg.getOrgCd());
				saveSortedTeams(comTeams);
			}
		}
	}

	private void saveSortedTeams(List<ComTeam> comTeams) {
		List<ComTeam> comTeams2 = new ArrayList<>();
		int sort = 0;
		for (ComTeam comTeam : comTeams) {
			if (comTeam.getTeamSort() == 99) {
				continue;
			}
			comTeam.setTeamSort(sort++);
			comTeams2.add(comTeam);
		}
		comTeamRepo.save(comTeams2);
	}
}
