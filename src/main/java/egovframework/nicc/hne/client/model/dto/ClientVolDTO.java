package egovframework.nicc.hne.client.model.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClientVolDTO {

  private Integer volId;
  private Date volBoard;
  private Date volBureau;
  private Date volImage;
  private Date volSection;
  private Date volStaff;
  private Date volTeam;
  private Date volBuilding;
  private Date volAbout;
  private Date volAlert;
  private Date volTour;
  private Date volFood;
  private Date volVideo;
  private Date volBgImg;
  private Date volConf;
  private Date volPatch;
  private Date volCalendar;
  private Date volBasic;
  private Date volLayout;
}
