package egovframework.nicc.hne.sync.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
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
@Table(name = "sync_history")
@EntityListeners(AuditingEntityListener.class)
public class SyncHistory {
  @Id
  @Column(name = "history_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer historyId;
  @Column(name="history_ndt")
  @CreatedDate
  private Date historyNdt;
  @Column(name="history_success")
  private Boolean historySuccess;
}
