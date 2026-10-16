package egovframework.nicc.hne.com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.nicc.hne.com.model.entity.PotOrgSection;

public interface SyncTeamRepo extends JpaRepository<PotOrgSection, String>{

}
