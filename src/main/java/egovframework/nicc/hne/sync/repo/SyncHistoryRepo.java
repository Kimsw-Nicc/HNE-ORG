package egovframework.nicc.hne.sync.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.nicc.hne.sync.model.entity.SyncHistory;

public interface SyncHistoryRepo extends JpaRepository<SyncHistory, Integer> {

  SyncHistory findFirstByOrderByHistoryIdDesc();
}
