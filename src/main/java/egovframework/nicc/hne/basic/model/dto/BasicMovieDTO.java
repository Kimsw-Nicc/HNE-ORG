package egovframework.nicc.hne.basic.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicMovieDTO {

  private Integer movieCd;
  private String movieName;
  private Integer movieSort;
  private String movieFilename;
  private String movieOriginFilename;
  private String movieThumbnail;
  private Date movieNdt;
  private Date movieEdt;

  private String orgCd;
  private String orgName;
  private String orgFullname;
  
}
