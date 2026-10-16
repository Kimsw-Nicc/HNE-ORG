package egovframework.nicc.hne.com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import egovframework.nicc.hne.com.model.entity.ComStaff;

public interface ComStaffRepo extends JpaRepository<ComStaff, Integer> {

  @Query(value = "SELECT cs from ComStaff cs WHERE cs.staffLogonId = :staffLogonId")
  ComStaff findByStaffLogonId(@Param("staffLogonId") String staffLogonId);

  @Query(value = "SELECT cs from ComStaff cs WHERE cs.comOrg.orgCd like :orgCd")
  List<ComStaff> findByOrgCd(@Param("orgCd") String orgCd);

  @Query(value = "SELECT cs from ComStaff cs WHERE cs.comTeam.teamCd like :teamCd ORDER BY cs.staffStatus desc,cs.staffSort")
  List<ComStaff> findByTeamCd(@Param("teamCd") String teamCd);

  List<ComStaff> findTop8ByOrderByStaffEdtDesc();

  @Query("SELECT cs FROM ComStaff cs WHERE cs.staffName like :staffName")
  List<ComStaff> findByStaffName(@Param("staffName") String staffName);

  @Transactional
  @Query(value = "UPDATE ComStaff cs SET cs.staffSort = 99 WHERE cs.comTeam.teamCd is null")
  void updateNoTeamStaffSort();

  @Query(value = "SELECT cs FROM ComStaff cs WHERE cs.staffSort LIKE 0")
  List<ComStaff> findZeroSort();

  @Transactional
  @Modifying
  @Query("DELETE FROM ComStaff cs WHERE cs.staffStatus NOT LIKE 'AAA' AND cs.staffStatus NOT LIKE 'AAB'")
  void deleteByStaffStatusNotTenure();

  @Transactional
  @Modifying
  @Query("DELETE FROM ComStaff cs WHERE cs.staffLogonId LIKE :staffLogonId")
  void deleteByStaffLogonId(@Param("staffLogonId") String staffLogonId);
  
  @Transactional
  @Modifying(clearAutomatically = true)
  @Query("UPDATE ComStaff s SET s.comTeam.teamCd = NULL " +
         "WHERE s.comTeam.teamCd IS NOT NULL " +
         "AND s.comTeam.teamCd NOT IN (" +
         "  SELECT t.teamCd FROM ComTeam t WHERE t.comOrg.orgCd = s.comOrg.orgCd" +
         ")")
  void clearInvalidTeamCd();
}
