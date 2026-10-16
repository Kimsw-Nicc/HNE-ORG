package egovframework.nicc.hne.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.basic.model.dto.BasicBackgroundDTO;
import egovframework.nicc.hne.basic.model.entity.BasicBackground;
import egovframework.nicc.hne.basic.repo.BasicBackgroundRepo;
import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class BackgroundService {

	@Autowired
	BasicBackgroundRepo backgroundRepo;
	@Autowired
	ComOrgRepo comOrgRepo;

	@Autowired
	ModelConverter modelConverter;

	// 페이징 전부 가져오기
	public List<BasicBackgroundDTO> getBackgrounds(Pageable pageable) {
		Page<BasicBackground> backgroundPages = backgroundRepo.findAll(pageable);
		return modelConverter.mapList(backgroundPages.getContent(), BasicBackgroundDTO.class);
	}

	// 일반 전부 가져오기
	public List<BasicBackgroundDTO> getBackgrounds() {
		List<BasicBackground> backgroundPages = backgroundRepo.findAll();
		return modelConverter.mapList(backgroundPages, BasicBackgroundDTO.class);
	}

	// 하나 가져오기
	public BasicBackgroundDTO getBackground(Integer bgiId) {
		return modelConverter.map(backgroundRepo.findOne(bgiId), BasicBackgroundDTO.class);
	}

	// 개수 가져오기
	public Long getCount() {
		return backgroundRepo.count();
	}

	// INSERT
	public void insertBackground(BasicBackgroundDTO backgroundDTO) {
		BasicBackground background = modelConverter.map(backgroundDTO, BasicBackground.class);
		backgroundRepo.save(background);
	}

	// UPDATE
	public String updateBackgroundFilename(BasicBackgroundDTO backgroundDTO) {
		BasicBackground background = backgroundRepo.findOne(backgroundDTO.getBackgroundCd());
		String filename = background.getBackgroundFilename();
		background.setBackgroundFilename(backgroundDTO.getBackgroundFilename());
		backgroundRepo.save(background);
		if (filename.equals(backgroundDTO.getBackgroundFilename())) {
			return null;
		}
		return filename;
	}

	public void updateBackgroundOriginFilename(BasicBackgroundDTO backgroundDTO) {
		BasicBackground background = backgroundRepo.findOne(backgroundDTO.getBackgroundCd());
		background.setBackgroundOriginFilename(backgroundDTO.getBackgroundOriginFilename());
		backgroundRepo.save(background);
	}

	public void updateBackgroundName(BasicBackgroundDTO backgroundDTO) {
		BasicBackground background = backgroundRepo.findOne(backgroundDTO.getBackgroundCd());
		background.setBackgroundName(backgroundDTO.getBackgroundName());
		backgroundRepo.save(background);
	}

	public void updateBackgroundSort(BasicBackgroundDTO backgroundDTO) {
		BasicBackground background = backgroundRepo.findOne(backgroundDTO.getBackgroundCd());
		background.setBackgroundSort(backgroundDTO.getBackgroundSort());
		backgroundRepo.save(background);
	}

	// DELETE
	public String deleteBackground(Integer backgroundCd) {
		BasicBackground background = backgroundRepo.findOne(backgroundCd);
		backgroundRepo.delete(backgroundCd);
		return background.getBackgroundFilename();
	}

}
