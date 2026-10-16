package egovframework.nicc.hne.sync.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sync_schedule")
@EntityListeners(AuditingEntityListener.class)
public class SyncSchedule {

  @Id
  @Column(name="schedule_id")
  private Integer scheduleId;
  @Column(name = "schedule_second")
  private String scheduleSecond;
  @Column(name = "schedule_minute")
  private String scheduleMinute;
  @Column(name = "schedule_hour")
  private String scheduleHour;
  @Column(name = "schedule_day")
  private String scheduleDay;
  @Column(name = "schedule_week")
  private String scheduleWeek;
  @Column(name = "schedule_month")
  private String scheduleMonth;
  
  @Column(name = "schedule_org")
  private Boolean scheduleOrg;
  @Column(name = "schedule_team")
  private Boolean scheduleTeam;
  @Column(name = "schedule_staff")
  private Boolean scheduleStaff;

}
