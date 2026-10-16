package egovframework.nicc.hne.client.model.jsonvo;

import java.text.SimpleDateFormat;

import egovframework.nicc.hne.basic.model.dto.BasicAlertDTO;
import egovframework.nicc.hne.basic.model.entity.BasicAlert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicAlertVO {
  private Integer alert_id;
  private String section_cd;
  private String category;
  private String type;
  private String content;
  private String visible;
  private String regdate;
  private String sttime;
  private String edtime;
  private String stdate;
  private String eddate;

  public BasicAlertVO(BasicAlertDTO basicAlertDTO){
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
    alert_id = basicAlertDTO.getAlertCd();
    section_cd = null;
    if(basicAlertDTO.getAlertCategory() == BasicAlert.Category.alert) category = "알리미";
    else if(basicAlertDTO.getAlertCategory() == BasicAlert.Category.lunch) category = "점심";
    else if(basicAlertDTO.getAlertCategory() == BasicAlert.Category.message) category = "메시지";
    if(basicAlertDTO.getAlertType() == BasicAlert.Type.info) type = "낮음";
    else if(basicAlertDTO.getAlertType() == BasicAlert.Type.warning) type = "중간";
    else if(basicAlertDTO.getAlertType() == BasicAlert.Type.critical) type = "높음";
    content = basicAlertDTO.getAlertContent();
    visible = basicAlertDTO.getAlertVisible()?"TRUE":"FALSE";
    regdate = format.format(basicAlertDTO.getAlertNdt());
    format = new SimpleDateFormat("HH:mm:ss");
    sttime = format.format(basicAlertDTO.getAlertStartTime());
    edtime = format.format(basicAlertDTO.getAlertEndTime());
    format = new SimpleDateFormat("yyyy-MM-dd");
    stdate = format.format(basicAlertDTO.getAlertStartDate());
    eddate = format.format(basicAlertDTO.getAlertEndDate());
  }
}
