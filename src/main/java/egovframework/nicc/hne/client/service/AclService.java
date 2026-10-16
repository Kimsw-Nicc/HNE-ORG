package egovframework.nicc.hne.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.client.model.dto.ClientAclDTO;
import egovframework.nicc.hne.client.model.entity.ClientAcl;
import egovframework.nicc.hne.client.repo.ClientAclRepo;
import egovframework.nicc.hne.client.service.AclService;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class AclService {

	@Autowired
	ClientAclRepo clientAclRepo;
	@Autowired
	ModelConverter modelConverter;

	public List<ClientAclDTO> getAcls(Pageable pageable) {
		return modelConverter.mapList(clientAclRepo.findAll(pageable).getContent(), ClientAclDTO.class);
	}

	public List<ClientAclDTO> getAcls() {
		return modelConverter.mapList(clientAclRepo.findAll(), ClientAclDTO.class);
	}

	public ClientAclDTO getAcl(Integer aclId) {
		return modelConverter.map(clientAclRepo.findOne(aclId), ClientAclDTO.class);
	}

	public void insertAcl(ClientAclDTO clientAclDTO) throws Exception {
		ClientAcl clientAcl = modelConverter.map(clientAclDTO, ClientAcl.class);
		clientAclRepo.save(clientAcl);
	}

	public void updateAcl(ClientAclDTO clientAclDTO) throws Exception {
		ClientAcl clientAcl = clientAclRepo.findOne(clientAclDTO.getAclId());
		clientAcl.setAclIp(clientAclDTO.getAclIp());
		clientAcl.setAclName(clientAclDTO.getAclName());
		clientAcl.setAclType(clientAclDTO.getAclType());
		clientAclRepo.save(clientAcl);
	}

	public void deleteAcl(Integer aclId) throws Exception {
		clientAclRepo.delete(aclId);
	}

}
