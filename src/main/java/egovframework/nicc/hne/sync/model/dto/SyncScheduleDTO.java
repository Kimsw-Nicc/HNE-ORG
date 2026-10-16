package egovframework.nicc.hne.sync.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SyncScheduleDTO {

	private Integer scheduleId;
	private String scheduleSecond;
	private String scheduleMinute;
	private String scheduleHour;
	private String scheduleDay;
	private String scheduleWeek;
	private String scheduleMonth;

	private Boolean scheduleOrg;
	private Boolean scheduleTeam;
	private Boolean scheduleStaff;

}
