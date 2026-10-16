package egovframework.nicc.hne.basic.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import egovframework.nicc.hne.basic.model.entity.BasicNotice;


public interface BasicNoticeRepo extends JpaRepository<BasicNotice, Integer> {
	
	@Query("SELECT bn FROM BasicNotice bn LEFT JOIN bn.comOrg co WHERE bn.noticeName LIKE %:keyword% ORDER BY CASE WHEN bn.comOrg.orgCd IS NULL THEN 0 ELSE 1 END, co.orgSort, bn.noticeSort")
	List<BasicNotice> findAll(Pageable pageable, @Param("keyword") String keyword);
	
	@Query("SELECT bn FROM BasicNotice bn WHERE bn.comOrg.orgCd LIKE :orgCd AND bn.noticeName LIKE %:keyword% ORDER BY bn.noticeSort")
	List<BasicNotice> findByOrgCd(Pageable pageable, @Param("keyword") String keyword, @Param("orgCd") String orgCd);
	
  @Query("SELECT bn FROM BasicNotice bn WHERE bn.comOrg.orgCd LIKE :orgCd or bn.comOrg.orgCd IS NULL ORDER BY CASE WHEN bn.comOrg.orgCd IS NULL THEN 0 ELSE 1 END, bn.comOrg.orgSort, bn.noticeSort")
  List<BasicNotice> findByOrgCd(@Param("orgCd") String orgCd);
  
  @Query("SELECT bn FROM BasicNotice bn WHERE bn.comOrg.orgCd IS NULL")
  List<BasicNotice> findByOrgCdIsNull();
  
  @Query("SELECT COUNT(bn) FROM BasicNotice bn WHERE bn.noticeName LIKE %:keyword%")
  Long count(@Param("keyword") String keyword);

  @Query("SELECT COUNT(bn) FROM BasicNotice bn WHERE bn.comOrg.orgCd like :orgCd AND bn.noticeName LIKE %:keyword%")
  Long count(@Param("keyword") String keyword, @Param("orgCd") String orgCd);

}
