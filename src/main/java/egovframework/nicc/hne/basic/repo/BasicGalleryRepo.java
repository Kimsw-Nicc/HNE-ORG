package egovframework.nicc.hne.basic.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import egovframework.nicc.hne.basic.model.entity.BasicGallery;

public interface BasicGalleryRepo extends JpaRepository<BasicGallery, Integer> {
	
	@Query("SELECT bg FROM BasicGallery bg LEFT JOIN bg.comOrg co ORDER BY CASE WHEN co.orgCd IS NULL THEN 0 ELSE 1 END, co.orgSort, bg.gallerySort")
	Page<BasicGallery> findAll(Pageable pageable);

	@Query("SELECT bg FROM BasicGallery bg WHERE bg.comOrg.orgCd LIKE :orgCd ORDER BY CASE WHEN bg.comOrg.orgCd IS NULL THEN 0 ELSE 1 END, bg.comOrg.orgSort, bg.gallerySort")
	List<BasicGallery> findByOrgCd(Pageable pageable, @Param("orgCd") String orgCd);
	
	@Query("SELECT bg FROM BasicGallery bg LEFT JOIN bg.comOrg co WHERE co.orgCd LIKE :orgCd OR co.orgCd IS NULL ORDER BY CASE WHEN co.orgCd IS NULL THEN 0 ELSE 1 END, co.orgSort, bg.gallerySort")
  List<BasicGallery> findByOrgCdAndNull(Pageable pageable, @Param("orgCd") String orgCd);

	@Query("SELECT bg FROM BasicGallery bg WHERE bg.comOrg.orgCd LIKE :orgCd or bg.comOrg.orgCd IS NULL ORDER BY CASE WHEN bg.comOrg.orgCd IS NULL THEN 0 ELSE 1 END, bg.comOrg.orgSort, bg.gallerySort")
	List<BasicGallery> findByOrgCd(@Param("orgCd") String orgCd);

	@Query("SELECT bg FROM BasicGallery bg WHERE bg.comOrg.orgCd IS NULL ORDER BY bg.gallerySort")
	List<BasicGallery> findByOrgCdIsNull();

	@Query("SELECT COUNT(bg) FROM BasicGallery bg WHERE bg.comOrg.orgCd like :orgCd")
	Long countByOrgCd(@Param("orgCd") String orgCd);
	
	@Query("SELECT COUNT(bg) FROM BasicGallery bg LEFT JOIN bg.comOrg co WHERE co.orgCd LIKE :orgCd OR co.orgCd IS NULL")
  Long countByOrgAndNull(@Param("orgCd") String orgCd);
}
