package egovframework.nicc.hne.client.model.jsonvo;

import egovframework.nicc.hne.basic.model.dto.BasicSettingDTO;
import egovframework.nicc.hne.basic.model.entity.BasicSetting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicSettingVO {
  private Integer image_number;
	private Integer image_type;
	private String image_name;
	private Long image_insert_date;
	private Long image_edited_date;

  public BasicSettingVO(BasicSettingDTO basicSettingDTO){
    image_number = basicSettingDTO.getSettingCd();
    if(BasicSetting.Type.LOGO == basicSettingDTO.getSettingType()) image_type = 1;
    else if(BasicSetting.Type.SLOGAN == basicSettingDTO.getSettingType()) image_type = 2;
    image_name = basicSettingDTO.getSettingFilename();
    image_insert_date = basicSettingDTO.getSettingNdt().getTime();
    image_edited_date = basicSettingDTO.getSettingEdt().getTime();
  }
}
