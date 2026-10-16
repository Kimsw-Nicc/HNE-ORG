package egovframework.nicc.hne.layout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.layout.model.dto.LayoutBackgroundDTO;
import egovframework.nicc.hne.layout.model.entity.LayoutBackground;
import egovframework.nicc.hne.layout.repo.LayoutBackgroundRepo;
import egovframework.nicc.hne.layout.service.LayoutBackgroundService;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class LayoutBackgroundService {

	@Autowired
	LayoutBackgroundRepo backgroundRepo;
	@Autowired
	ComOrgRepo orgRepo;

	@Autowired
	ModelConverter converter;

	public LayoutBackgroundDTO getBackgroundByOrgCd(String orgCd) throws Exception {
		LayoutBackground layoutBackground = backgroundRepo.findByOrgCd(orgCd);
		LayoutBackgroundDTO layoutBackgroundDTO;
		try {
			layoutBackgroundDTO = converter.map(layoutBackground, LayoutBackgroundDTO.class);
		} catch (Exception e) {
			layoutBackgroundDTO = new LayoutBackgroundDTO();
		}
		return layoutBackgroundDTO;
	}

	public void deleteBackgroundByOrgCd(String orgCd) {
		backgroundRepo.deleteByOrgCd(orgCd);
	}

	public void insertBackground(LayoutBackgroundDTO backgroundDTO) {
		LayoutBackground layoutBackground = converter.map(backgroundDTO, LayoutBackground.class);
		layoutBackground.setComOrg(orgRepo.findOne(backgroundDTO.getOrgCd()));
		backgroundRepo.save(layoutBackground);
	}

}
