package egovframework.nicc.hne.client.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "client_acl")
@EntityListeners(AuditingEntityListener.class)
public class ClientAcl {
  @Id
  @Column(name = "acl_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer aclId;
  @Column(name = "acl_ip")
  private String aclIp;
  @Column(name = "acl_name")
  private String aclName;
	@Column(name = "acl_type")
	@Enumerated(EnumType.STRING)
  private Type aclType;

  public enum Type {
    admin, manager, equipment
  }
}
