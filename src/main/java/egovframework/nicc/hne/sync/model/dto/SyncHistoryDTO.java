package egovframework.nicc.hne.sync.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SyncHistoryDTO {
  
  private Integer historyId;
  private Date historyNdt;
  private Boolean historySuccess;
}
