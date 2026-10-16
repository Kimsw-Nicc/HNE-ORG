package egovframework.nicc.hne.client.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.client.model.dto.ClientPatchDTO;
import egovframework.nicc.hne.client.model.entity.ClientPatch;
import egovframework.nicc.hne.client.model.enumeration.PatchType;
import egovframework.nicc.hne.client.model.jsonvo.ClientPatchVO;
import egovframework.nicc.hne.client.model.jsonvo.ClientPatchsVO;
import egovframework.nicc.hne.client.repo.ClientPatchRepo;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class PatchService {

	@Autowired
	ClientPatchRepo clientPatchRepo;

	@Autowired
	ModelConverter modelConverter;

	public List<ClientPatchDTO> getPatchAll() {
		List<ClientPatchDTO> clientPatchDTOs = modelConverter
				.mapList(clientPatchRepo.findAllByOrderByPatchAndroidVersionDesc(), ClientPatchDTO.class);
		return clientPatchDTOs;
	}

	public List<ClientPatchsVO> getPatchsAll() {
		// client, food, luncher, manager, conf
		List<ClientPatchsVO> clientPatchsVOs = new ArrayList<>();
		for (PatchType type : PatchType.values()) {
			ClientPatchsVO clientPatchsVO = new ClientPatchsVO();
			if (PatchType.client == type) {
				clientPatchsVO = new ClientPatchsVO(1, "클라이언트", 3, "lab.nicc.kiosk_hne_oc", null);
			}
			if (PatchType.luncher == type) {
				clientPatchsVO = new ClientPatchsVO(2, "런처", 0, "lab.nicc.hneluncher", null);
			}
			if (PatchType.manager == type) {
				clientPatchsVO = new ClientPatchsVO(3, "매니저", 1, "lab.nicc.hne_oc_manager", null);
			}
			if (PatchType.conf == type) {
				clientPatchsVO = new ClientPatchsVO(4, "회의실", 0, "lab.nicc.hneconf", null);
			}
			List<ClientPatchDTO> clientPatchDTOs = modelConverter
					.mapList(clientPatchRepo.findByPatchTypeOrderByPatchAndroidVersionDesc(type), ClientPatchDTO.class);
			List<ClientPatchVO> clientPatchVOs = new ArrayList<>();
			for (ClientPatchDTO clientPatchDTO : clientPatchDTOs) {
				clientPatchVOs.add(new ClientPatchVO(clientPatchDTO));
			}
			clientPatchsVO.setPatchFile(clientPatchVOs);
			clientPatchsVOs.add(clientPatchsVO);
		}
		return clientPatchsVOs;
	}

	public List<ClientPatchDTO> getPatchs(PatchType type) {
		List<ClientPatchDTO> clientPatchDTOs = modelConverter.mapList(clientPatchRepo.findByPatchType(type),
				ClientPatchDTO.class);
		return clientPatchDTOs;
	}

	public void insertPatch(ClientPatchDTO clientPatchDTO) {
		ClientPatch clientPatch = modelConverter.map(clientPatchDTO, ClientPatch.class);
		clientPatch.setPatchAndroidVersion(clientPatchDTO.getPatchName().split("_")[1].replace(".apk", ""));
		clientPatchRepo.save(clientPatch);

	}

	public String deletePatch(Integer patchId) {
		ClientPatch clientPatch = clientPatchRepo.findOne(patchId);
		clientPatchRepo.delete(patchId);
		return clientPatch.getPatchName();
	}

	public String getLatestVersion(PatchType type) {
		return clientPatchRepo.findByPatchType(type).get(0).getPatchAndroidVersion();
	}
}
