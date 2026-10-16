package egovframework.nicc.hne.guide.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import egovframework.nicc.hne.guide.model.entity.GuideText;

public interface GuideTextRepo extends JpaRepository<GuideText, Integer> {

	@Query(value = "SELECT max(gt) FROM GuideText gt WHERE gt.guideFloor.floorId = :floorId")
	Integer findMaxSort(@Param("floorId") Integer floorId);
	
	@Query(value = "SELECT gt FROM GuideText gt WHERE gt.guideFloor.floorId = :floorId")
	List<GuideText> findByFloorId(@Param("floorId") Integer floorId);
	
	@Modifying 
	@Transactional
	@Query(value = "DELETE FROM GuideText gt WHERE gt.guideFloor.floorId = :floorId")
	void deleteByFloorId(@Param("floorId") Integer floorId);
}
