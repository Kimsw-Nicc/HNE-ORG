package egovframework.nicc.hne.sync.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.nicc.hne.sync.model.entity.SyncSchedule;

public interface SyncScheduleRepo extends JpaRepository<SyncSchedule, Integer> {
  
}
