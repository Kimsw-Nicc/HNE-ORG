package egovframework.nicc.hne.basic.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import egovframework.nicc.hne.basic.model.entity.BasicMovie;

public interface BasicMovieRepo extends JpaRepository<BasicMovie, Integer> {
	@Query("SELECT bm FROM BasicMovie bm LEFT JOIN bm.comOrg co ORDER BY CASE WHEN co.orgCd IS NULL THEN 0 ELSE 1 END, co.orgSort, bm.movieSort")
	Page<BasicMovie> findAll(Pageable pageable);
	
	@Query("SELECT bm FROM BasicMovie bm WHERE bm.comOrg.orgCd LIKE :orgCd ORDER BY CASE WHEN bm.comOrg.orgCd IS NULL THEN 0 ELSE 1 END, bm.comOrg.orgSort, bm.movieSort")
	List<BasicMovie> findByOrgCd(Pageable pageable, @Param("orgCd") String orgCd);
	
	@Query("SELECT bm FROM BasicMovie bm LEFT JOIN bm.comOrg co WHERE bm.comOrg.orgCd LIKE :orgCd or bm.comOrg.orgCd IS NULL ORDER BY CASE WHEN bm.comOrg.orgCd IS NULL THEN 0 ELSE 1 END, bm.comOrg.orgSort, bm.movieSort")
	List<BasicMovie> findByOrgCd(@Param("orgCd") String orgCd);
  
  @Query("SELECT bm FROM BasicMovie bm WHERE bm.comOrg.orgCd IS NULL ORDER BY bm.movieEdt DESC ORDER BY bm.movieSort")
  List<BasicMovie> findByOrgCdIsNull();

  @Query("SELECT COUNT(bm) FROM BasicMovie bm WHERE bm.comOrg.orgCd like :orgCd")
  Long count(@Param("orgCd") String orgCd);
}
