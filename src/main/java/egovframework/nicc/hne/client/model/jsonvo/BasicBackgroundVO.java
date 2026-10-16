package egovframework.nicc.hne.client.model.jsonvo;

import java.text.SimpleDateFormat;

import egovframework.nicc.hne.basic.model.dto.BasicBackgroundDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicBackgroundVO {

  private Integer bi_no;
  private String bi_name;
  private String bi_img;
  private boolean bi_able;
  private String bi_ndt;
  private String bi_edt;
  private String section_cd;
  private String section_name;

  public BasicBackgroundVO(BasicBackgroundDTO backgroundDTO) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
    bi_no = backgroundDTO.getBackgroundCd();
    bi_name = backgroundDTO.getBackgroundName();
    bi_img = backgroundDTO.getBackgroundFilename();
    // bi_able = backgroundDTO.getable
    bi_ndt = format.format(backgroundDTO.getBackgroundNdt());
    bi_edt = format.format(backgroundDTO.getBackgroundEdt());
    section_cd = "";
    section_name = "";
  }
}
