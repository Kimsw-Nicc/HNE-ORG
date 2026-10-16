package egovframework.nicc.hne.global.model.dto;

import egovframework.nicc.hne.global.model.enumeration.GlobalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalToggleDTO {
  
  private GlobalType toggleType;
  private Boolean toggleUse;
}
