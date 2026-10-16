package egovframework.nicc.hne.com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import egovframework.nicc.hne.com.model.entity.ComOrg;

public interface ComOrgRepo extends JpaRepository<ComOrg, String> {

  @Query(value="SELECT co FROM ComOrg co WHERE co.orgUprCd like :orgUprCd order by co.orgSort")
  List<ComOrg> findByOrgUprCd(@Param("orgUprCd") String orgUprCd);
  
  @Query(value="SELECT co FROM ComOrg co WHERE co.orgUprCd not like :orgUprCd order by co.orgSort")
  List<ComOrg> findByNotOrgUprCd(@Param("orgUprCd") String orgUprCd);

  // 
  @Transactional
  @Query(value = "UPDATE com_org as t1 " + //
      "JOIN ( " + //
      "  SELECT  " + //
      "    t2.org_cd AS child_cd, " + //
      "    t2.org_name AS child_name, " + //
      "    t1.org_name AS parent_name, " + //
      "    CONCAT(t1.org_fullname, ' ', t2.org_name) AS new_fullname " + //
      "  FROM com_org AS t1 " + //
      "  JOIN com_org AS t2 ON t1.org_cd = t2.org_upr_cd " + //
      ") AS temp ON t1.org_cd = temp.child_cd " + //
      "SET t1.org_fullname = temp.new_fullname;", nativeQuery = true)
  void makeOrgFullname();

}
