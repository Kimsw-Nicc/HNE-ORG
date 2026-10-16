package egovframework.nicc.hne.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.sync.model.dto.SyncHistoryDTO;
import egovframework.nicc.hne.sync.model.entity.SyncHistory;
import egovframework.nicc.hne.sync.repo.SyncHistoryRepo;
import egovframework.nicc.hne.sync.service.HistoryService;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class HistoryService {

	@Autowired
	SyncHistoryRepo syncHistoryRepo;

	@Autowired
	ModelConverter modelConverter;

	public SyncHistoryDTO getLastHistory() {
		return modelConverter.map(syncHistoryRepo.findFirstByOrderByHistoryIdDesc(), SyncHistoryDTO.class);
	}

	public void saveHistory(Boolean success) {
		syncHistoryRepo.save(new SyncHistory(null, null, success));
	}
}
