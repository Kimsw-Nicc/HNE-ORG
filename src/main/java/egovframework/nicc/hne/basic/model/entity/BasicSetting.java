package egovframework.nicc.hne.basic.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
@Table(name = "basic_setting")
@EntityListeners(AuditingEntityListener.class)
public class BasicSetting {

  public BasicSetting(Type settingType){
    this.settingType = settingType;
  }
  
  @Id
  @Column(name = "setting_cd")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer settingCd;
  @Column(name = "setting_filename")
  private String settingFilename;
  @Column(name = "setting_origin_filename")
  private String settingOriginFilename;
  @Column(name = "setting_ndt")
  @CreatedDate
  private Date settingNdt;
  @Column(name = "setting_edt")
  @LastModifiedDate
  private Date settingEdt;
  @Column(name = "setting_type")
  @Enumerated(EnumType.STRING)
  private Type settingType;

  public enum Type{
    LOGO, SLOGAN
  }
}
