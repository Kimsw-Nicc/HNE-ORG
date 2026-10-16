package egovframework.nicc.hne.client.model.jsonvo;

import java.util.List;

import egovframework.nicc.hne.guide.model.dto.GuideBuildingDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuideBuildingVO {
  private Integer buildingCd;
  private String buildingName;
  private Integer buildingSort;
  private String buildingFilename;
  private String buildingOriginFilename;
  private List<GuideFloorVO> guideFloorVOs;

  public GuideBuildingVO(GuideBuildingDTO guideBuildingDTO, List<GuideFloorVO> guideFloorVOs){
    buildingCd = guideBuildingDTO.getBuildingId();
    buildingName = guideBuildingDTO.getBuildingName();
    buildingSort = guideBuildingDTO.getBuildingSort();
    buildingFilename = guideBuildingDTO.getBuildingFilename();
    buildingOriginFilename = guideBuildingDTO.getBuildingOriginFilename();
    this.guideFloorVOs = guideFloorVOs;
  }
}
