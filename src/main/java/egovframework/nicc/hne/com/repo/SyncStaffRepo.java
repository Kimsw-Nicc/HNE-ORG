package egovframework.nicc.hne.com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.nicc.hne.com.model.entity.PotOrgUser;

public interface SyncStaffRepo extends JpaRepository<PotOrgUser, String>{

}
