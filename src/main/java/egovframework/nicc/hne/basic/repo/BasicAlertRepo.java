package egovframework.nicc.hne.basic.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import egovframework.nicc.hne.basic.model.entity.BasicAlert;

public interface BasicAlertRepo extends JpaRepository<BasicAlert, Integer> {

  public Page<BasicAlert> findByAlertContentContaining(String keyword, Pageable pageable);

  @Query("SELECT COUNT(ba) FROM BasicAlert ba WHERE ba.alertContent LIKE %:keyword%")
  int countAll(@Param("keyword") String keyword);
}
