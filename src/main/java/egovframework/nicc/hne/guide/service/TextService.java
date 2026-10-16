package egovframework.nicc.hne.guide.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.guide.model.dto.GuideTextDTO;
import egovframework.nicc.hne.guide.model.entity.GuideText;
import egovframework.nicc.hne.guide.repo.GuideTextRepo;
import egovframework.nicc.hne.guide.service.TextService;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class TextService {

	@Autowired
	GuideTextRepo guideTextRepo;

	@Autowired
	ComOrgRepo comOrgRepo;

	@Autowired
	ModelConverter modelConverter;

	public Integer getNextSort(Integer floorId) {
		try {
			return guideTextRepo.findMaxSort(floorId) + 1;
		} catch (Exception e) {
			return 1;
		}
	}

	public List<GuideTextDTO> getTexts(Integer floorId) {
		return modelConverter.mapList(guideTextRepo.findByFloorId(floorId), GuideTextDTO.class);
	}

	public GuideTextDTO getText(Integer textId) {
		return modelConverter.map(guideTextRepo.findOne(textId), GuideTextDTO.class);
	}

	public void insertTexts(List<GuideTextDTO> guideTextDTOs) {
		// 아마 fk 가 정상적으로 저장되지 않을것임
		// maplist가 아니라 수동으로 변환후 ~~
		List<GuideText> guideTexts = new ArrayList<GuideText>();
		for (GuideTextDTO guideTextDTO : guideTextDTOs) {
			GuideText guideText = modelConverter.map(guideTextDTO, GuideText.class);
			if (guideTextDTO.getOrgCd() != null) {
				guideText.setComOrg(comOrgRepo.findOne(guideTextDTO.getOrgCd()));
			}
			guideTexts.add(guideText);
		}
		guideTextRepo.save(guideTexts);

	}

	public void deleteText(Integer textId) {
		guideTextRepo.delete(textId);
	}

	public void deleteTextByFloor(Integer floorId) {
		guideTextRepo.deleteByFloorId(floorId);
	}

	public void sortTexts(Integer floorId) {
		List<GuideText> guideTexts = guideTextRepo.findByFloorId(floorId);
		List<GuideText> guideTexts2 = new ArrayList<GuideText>();
		int sort = 0;
		for (GuideText guideText : guideTexts) {
			guideText.setTextSort(sort++);
			guideTexts2.add(guideText);
		}
		guideTextRepo.save(guideTexts2);
	}

}
