package egovframework.nicc.hne.guide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.guide.model.dto.GuideFloorDTO;
import egovframework.nicc.hne.guide.model.entity.GuideFloor;
import egovframework.nicc.hne.guide.repo.GuideFloorRepo;
import egovframework.nicc.hne.guide.service.FloorService;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class FloorService {

	@Autowired
	GuideFloorRepo guideFloorRepo;

	@Autowired
	ModelConverter modelConverter;

	public List<GuideFloorDTO> getFloors(Integer buildingId) {
		return modelConverter.mapList(guideFloorRepo.findByBuildingId(buildingId), GuideFloorDTO.class);
	}

	public GuideFloorDTO getFloor(Integer floorId) {
		return modelConverter.map(guideFloorRepo.findOne(floorId), GuideFloorDTO.class);
	}

	public void insertFloor(GuideFloorDTO guideFloorDTO) {
		guideFloorRepo.save(modelConverter.map(guideFloorDTO, GuideFloor.class));
	}

	public void updateFloor(GuideFloorDTO guideFloorDTO) {
		GuideFloor guideFloor = guideFloorRepo.findOne(guideFloorDTO.getFloorId());
		guideFloor.setFloorFilename(guideFloorDTO.getFloorFilename());
		guideFloor.setFloorOriginFilename(guideFloorDTO.getFloorOriginFilename());
		guideFloor.setFloorSort(guideFloorDTO.getFloorSort());
		guideFloorRepo.save(guideFloor);
	}

	public void deleteFloor(Integer floorId) {
		guideFloorRepo.findOne(floorId).getFloorFilename();
		guideFloorRepo.delete(floorId);
	}

}
