package egovframework.nicc.hne.com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import egovframework.nicc.hne.com.model.entity.ComTeam;

public interface ComTeamRepo extends JpaRepository<ComTeam, String> {

	@Query(value = "SELECT ct FROM ComTeam ct ")
	List<ComTeam> findAll();

	@Query(value = "SELECT ct FROM ComTeam ct WHERE ct.comOrg.orgCd like :orgCd ORDER BY ct.teamSort")
	List<ComTeam> findAll(@Param("orgCd") String orgCd);

}