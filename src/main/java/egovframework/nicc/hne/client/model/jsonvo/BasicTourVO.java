package egovframework.nicc.hne.client.model.jsonvo;

import com.ibm.icu.text.SimpleDateFormat;

import egovframework.nicc.hne.basic.model.dto.BasicTourDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicTourVO {
  private String tour_name;
  private String tour_image;
  private String tour_ndt;
  private String tour_edt;
  private Integer tour_ver;
  private String section_cd;

  public BasicTourVO(BasicTourDTO basicTourDTO){
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");

    tour_name = basicTourDTO.getTourName();
    tour_image = basicTourDTO.getTourFilename();
    tour_ndt = format.format(basicTourDTO.getTourNdt());
    tour_edt = format.format(basicTourDTO.getTourEdt());
    tour_ver = 0;
    section_cd = basicTourDTO.getOrgCd()==null?"none":basicTourDTO.getOrgCd();
  }
}
