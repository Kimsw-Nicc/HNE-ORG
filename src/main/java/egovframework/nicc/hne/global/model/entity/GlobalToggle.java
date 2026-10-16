package egovframework.nicc.hne.global.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import egovframework.nicc.hne.global.model.enumeration.GlobalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "global_toggle")
@EntityListeners(AuditingEntityListener.class)
public class GlobalToggle {
  
  @Id
  @Column(name = "toggle_type")
  @Enumerated(EnumType.STRING)
  private GlobalType toggleType;
  @Column(name = "toggle_use")
  private Boolean toggleUse;

  
}
