package egovframework.nicc.hne.guide.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import egovframework.nicc.hne.guide.model.entity.GuideBuilding;

public interface GuideBuildingRepo extends JpaRepository<GuideBuilding, Integer> {

  List<GuideBuilding> findAllByOrderByBuildingSort();
  
  @Modifying
  @Query("UPDATE GuideBuilding b " +
         "SET b.buildingFilename = :buildingFilename, " +
         "    b.buildingOriginFilename = :buildingOriginFilename " +
         "WHERE b.buildingId = :buildingId")
  @Transactional
  void updateBuildingImage(@Param("buildingId") Integer buildingId,
                           @Param("buildingFilename") String buildingFilename,
                           @Param("buildingOriginFilename") String buildingOriginFilename);
} 