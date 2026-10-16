package egovframework.nicc.hne.sync.repo;

import java.util.List;

import egovframework.nicc.hne.com.model.entity.PotOrgDept;
import egovframework.nicc.hne.com.model.entity.PotOrgSection;
import egovframework.nicc.hne.com.model.entity.PotOrgUser;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("SyncUmsMapper")
public interface SyncUmsMapper {

	List<PotOrgDept> getSyncOrgs(String orgCd) throws Exception;

  List<PotOrgSection> getSyncTeams(String orgCd) throws Exception;
	
	List<PotOrgUser> getSyncStaffs(String orgCd) throws Exception;
}
