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
public class BasicTourDTO {
  private Integer tourCd;
  private String tourName;
  private String tourFilename;
  private String tourOriginFilename;
  private Date tourNdt;
  private Date tourEdt;
  
  private String orgCd;
  private String orgName;

}
