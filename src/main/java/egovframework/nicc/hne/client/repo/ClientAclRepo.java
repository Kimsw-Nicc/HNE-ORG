package egovframework.nicc.hne.client.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.nicc.hne.client.model.entity.ClientAcl;

public interface ClientAclRepo extends JpaRepository<ClientAcl, Integer>{
  
}
