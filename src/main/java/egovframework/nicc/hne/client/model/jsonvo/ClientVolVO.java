package egovframework.nicc.hne.client.model.jsonvo;

import com.fasterxml.jackson.annotation.JsonProperty;

import egovframework.nicc.hne.client.model.dto.ClientVolDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientVolVO {
  private Integer vol_no;
  private String board;
  private String bureau;
  private String image;
  private String section;
  private String staff;
  private String team;
  private String building;
  private String about;
  private String alert;
  private String tour;
  private String food;
  private String video;
  @JsonProperty("bgImg")
  private String bgImg;
  private String conf;
  private String basic;
  private String patch;
  private String layout;

  public ClientVolVO(ClientVolDTO clientVolDTO) {
    vol_no = clientVolDTO.getVolId();
    board = VOUtil.dateToString(clientVolDTO.getVolBoard());
    staff = VOUtil.dateToString(clientVolDTO.getVolStaff());
    team = VOUtil.dateToString(clientVolDTO.getVolTeam());
    bureau = VOUtil.dateToString(clientVolDTO.getVolBureau());
    section = VOUtil.dateToString(clientVolDTO.getVolSection());
    image = VOUtil.dateToString(clientVolDTO.getVolImage());
    building = VOUtil.dateToString(clientVolDTO.getVolBuilding());
    about = VOUtil.dateToString(clientVolDTO.getVolAbout());
    alert = VOUtil.dateToString(clientVolDTO.getVolAlert());
    tour = VOUtil.dateToString(clientVolDTO.getVolTour());
    food = VOUtil.dateToString(clientVolDTO.getVolFood());
    video = VOUtil.dateToString(clientVolDTO.getVolVideo());
    bgImg = VOUtil.dateToString(clientVolDTO.getVolBgImg());
    conf = VOUtil.dateToString(clientVolDTO.getVolConf());
    basic = VOUtil.dateToString(clientVolDTO.getVolBasic());
    patch = VOUtil.dateToString(clientVolDTO.getVolPatch());
    layout = VOUtil.dateToString(clientVolDTO.getVolLayout());
  }
}
