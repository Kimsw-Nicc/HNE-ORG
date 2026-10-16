package egovframework.nicc.hne.com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.nicc.hne.com.model.entity.PotOrgDept;

public interface SyncOrgRepo extends JpaRepository<PotOrgDept, String>{

}
