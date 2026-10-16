package egovframework.nicc.hne.com.repo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import egovframework.nicc.hne.com.model.entity.ComStaff;

@Repository
public class ComStaffRepoImpl {

  @PersistenceContext
  EntityManager entityManager;
  
  public List<ComStaff> findBoss(String[] bossNames) {
    StringBuilder jpql = new StringBuilder("SELECT cs FROM ComStaff cs WHERE ");
    for (int i = 0; i < bossNames.length; i++) {
        jpql.append("cs.staffPosition LIKE :bossName").append(i);
        if (i < bossNames.length - 1) {
            jpql.append(" OR ");
        }
    }
    TypedQuery<ComStaff> query = entityManager.createQuery(jpql.toString(), ComStaff.class);
    for (int i = 0; i < bossNames.length; i++) {
        query.setParameter("bossName" + i, "%" + bossNames[i]);
    }

    return query.getResultList();
  }

  public List<ComStaff> findStaffsByOrgCdAndTeamCd(Pageable pageable, String orgCd, String teamCd, String keyword, String keywordType, String status) {
    return entityManager.createQuery(
        "SELECT cs FROM ComStaff cs " +
            "LEFT JOIN cs.comTeam ct " +
            "WHERE cs.comOrg.orgCd LIKE :orgCd " +
            "AND cs.comTeam.teamCd = :teamCd " +
            "AND cs.staffStatus LIKE CONCAT('%', :status, '%') " +
            getKeywordSql(keywordType, keyword)+
            getOrderSql(pageable),
        ComStaff.class)
        .setParameter("orgCd", orgCd)
        .setParameter("teamCd", teamCd)
        .setParameter("keyword", keyword)
        .setParameter("status", status)
        .setFirstResult((int) pageable.getOffset())
        .setMaxResults(pageable.getPageSize())
        .getResultList();
  }
  
  public Long countStaffsByOrgCdAndTeamCd(String orgCd, String teamCd, String keyword, String keywordType, String status) {
  	return entityManager.createQuery(
        "SELECT count(cs) FROM ComStaff cs " +
            "LEFT JOIN cs.comTeam ct " +
            "WHERE cs.comOrg.orgCd LIKE :orgCd " +
            "AND cs.comTeam.teamCd = :teamCd " +
            "AND cs.staffStatus LIKE CONCAT('%', :status, '%') " +
            getKeywordSql(keywordType, keyword),
        Long.class)
        .setParameter("orgCd", orgCd)
        .setParameter("teamCd", teamCd)
        .setParameter("keyword", keyword)
        .setParameter("status", status)
        .getSingleResult();
  }

  public List<ComStaff> findStaffsByOrgCd(Pageable pageable, String orgCd, String keyword, String keywordType, String status) {
    return entityManager.createQuery(
        "SELECT cs FROM ComStaff cs " +
            "LEFT JOIN cs.comTeam ct " +
            "WHERE cs.comOrg.orgCd = :orgCd " +
            "AND cs.staffStatus LIKE CONCAT('%', :status, '%') " +
            getKeywordSql(keywordType, keyword)+
            getOrderSql(pageable),
        ComStaff.class)
        .setParameter("orgCd", orgCd)
        .setParameter("keyword", keyword)
        .setParameter("status", status)
        .setFirstResult((int) pageable.getOffset())
        .setMaxResults(pageable.getPageSize())
        .getResultList();
  }
  
  public Long countStaffsByOrgCd(String orgCd, String keyword, String keywordType, String status) {
  	return entityManager.createQuery(
        "SELECT count(cs) FROM ComStaff cs " +
            "LEFT JOIN cs.comTeam ct " +
            "WHERE cs.comOrg.orgCd = :orgCd " +
            "AND cs.staffStatus LIKE CONCAT('%', :status, '%') " +
            getKeywordSql(keywordType, keyword),
        Long.class)
  			.setParameter("orgCd", orgCd)
        .setParameter("keyword", keyword)
        .setParameter("status", status)
        .getSingleResult();
  }

  public List<ComStaff> findStaffs(Pageable pageable, String keyword, String keywordType, String status) {
    return entityManager.createQuery(
        "SELECT cs FROM ComStaff cs " +
            "LEFT JOIN cs.comTeam ct " +
            "WHERE 1=1 " +
            "AND cs.staffStatus LIKE CONCAT('%', :status, '%') " +
            getKeywordSql(keywordType, keyword)+
            getOrderSql(pageable),
        ComStaff.class)
        .setParameter("keyword", keyword)
        .setParameter("status", status)
        .setFirstResult((int) pageable.getOffset())
        .setMaxResults(pageable.getPageSize())
        .getResultList();
  }
  
  public Long countStaffs(String keyword, String keywordType, String status) {
  	return entityManager.createQuery(
        "SELECT count(cs) FROM ComStaff cs " +
            "LEFT JOIN cs.comTeam ct " +
            "WHERE 1=1 " +
            "AND cs.staffStatus LIKE CONCAT('%', :status, '%') " +
            getKeywordSql(keywordType, keyword),
        Long.class)
  			.setParameter("keyword", keyword)
        .setParameter("status", status)
        .getSingleResult();
  }
  
  private String getKeywordSql(String keywordType, String keyword) {
  	String keywordSql = "";
  	if(keywordType.equals("staffName"))
  		keywordSql =  "AND cs.staffName LIKE CONCAT('%', :keyword, '%') ";
		if(keywordType.equals("staffDuty"))
  		keywordSql =  "AND cs.staffDuty LIKE CONCAT('%', :keyword, '%') ";
		return keywordSql;
  }
  
  
  // order 은 항상 마지막에
  private String getOrderSql(Pageable pageable) {
  	Sort sort = pageable.getSort();
  	String orderSql = "";
  	for (Order order : sort) {
  		if(order.getProperty().equals("none"))
  			orderSql = "ORDER BY CASE WHEN ct.teamCd IS NULL THEN 0 ELSE 1 END, ct.teamSort, cs.staffSort, cs.staffCd";
  		if(order.getProperty().equals("staffName"))
  			orderSql = "ORDER BY cs.staffName";
  		if(order.getProperty().equals("staffEdt"))
  			orderSql = "ORDER BY cs.staffEdt DESC";
		}
  	return orderSql;
  }

  
}
