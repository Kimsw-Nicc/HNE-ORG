package egovframework.nicc.hne.layout.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import egovframework.nicc.hne.layout.model.entity.LayoutBackground;

public interface LayoutBackgroundRepo extends JpaRepository<LayoutBackground, Integer> {

  @Query("SELECT lb FROM LayoutBackground lb WHERE lb.comOrg.orgCd LIKE :orgCd")
  LayoutBackground findByOrgCd(@Param("orgCd") String orgCd);

  @Transactional
  @Modifying
  @Query("DELETE FROM LayoutBackground lb WHERE lb.comOrg.orgCd LIKE :orgCd")
  void deleteByOrgCd(@Param("orgCd") String orgCd);
}
