package egovframework.nicc.hne.guide.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import egovframework.nicc.hne.guide.model.entity.GuideFloor;

public interface GuideFloorRepo extends JpaRepository<GuideFloor, Integer>{
  
	@Query("SELECT gf FROM GuideFloor gf WHERE gf.guideBuilding.buildingId = :buildingId ORDER BY gf.floorSort")
	List<GuideFloor> findByBuildingId(@Param("buildingId") Integer buildingId);
	
}
