package egovframework.nicc.hne.guide.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.guide.model.dto.GuideBuildingDTO;
import egovframework.nicc.hne.guide.model.entity.GuideBuilding;
import egovframework.nicc.hne.guide.repo.GuideBuildingRepo;
import egovframework.nicc.hne.guide.service.BuildingService;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class BuildingService {
	@Autowired
	GuideBuildingRepo guideBuildingRepo;

	@Autowired
	ModelConverter modelConverter;

	public List<GuideBuildingDTO> getBuildings() {
		return modelConverter.mapList(guideBuildingRepo.findAllByOrderByBuildingSort(), GuideBuildingDTO.class);
	}

	public GuideBuildingDTO getBuilding(Integer buildingId) {
		return modelConverter.map(guideBuildingRepo.findOne(buildingId), GuideBuildingDTO.class);
	}

	public void insertBuilding(GuideBuildingDTO guideBuildingDTO) {
		guideBuildingRepo.save(modelConverter.map(guideBuildingDTO, GuideBuilding.class));
	}

	public void updateBuilding(GuideBuildingDTO guideBuildingDTO) {
		GuideBuilding guideBuilding = guideBuildingRepo.findOne(guideBuildingDTO.getBuildingId());
		guideBuilding.setBuildingName(guideBuildingDTO.getBuildingName());
		guideBuilding.setBuildingSort(guideBuildingDTO.getBuildingSort());
		guideBuildingRepo.save(guideBuilding);
	}

	public void updateBuildings(List<GuideBuildingDTO> guideBuildingDTOs) {
		for (GuideBuildingDTO guideBuildingDTO : guideBuildingDTOs) {
			GuideBuilding guideBuilding = guideBuildingRepo.findOne(guideBuildingDTO.getBuildingId());
			guideBuilding.setBuildingName(guideBuildingDTO.getBuildingName());
			guideBuilding.setBuildingSort(guideBuildingDTO.getBuildingSort());
			guideBuildingRepo.save(guideBuilding);
		}
	}

	public String deleteBuilding(Integer buildingId) {
		guideBuildingRepo.delete(buildingId);
		return null;
	}

	public void sortBuildingSort() {
		List<GuideBuilding> guideBuildings = guideBuildingRepo.findAll();
		List<GuideBuilding> guideBuildings2 = new ArrayList<GuideBuilding>();
		int sort = 0;
		for (GuideBuilding guideBuilding : guideBuildings) {
			guideBuilding.setBuildingSort(sort++);
			guideBuildings2.add(guideBuilding);
		}
		guideBuildingRepo.save(guideBuildings2);
	}

  public void updateBuildingImage(Integer buildingId, String buldingFilename, String buldingOriginFilename) {
      GuideBuildingDTO dto = new GuideBuildingDTO();
      dto.setBuildingId(buildingId);
      dto.setBuildingFilename(buldingFilename);
      dto.setBuildingOriginFilename(buldingOriginFilename);
      guideBuildingRepo.updateBuildingImage(buildingId, buldingFilename, buldingOriginFilename);
  }
}
