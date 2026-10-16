package egovframework.nicc.hne.auth.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import egovframework.nicc.hne.auth.model.entity.AuthUser;
import egovframework.nicc.hne.com.model.entity.ComOrg;


public interface AuthUserRepo extends JpaRepository<AuthUser, Integer> {

  Page<AuthUser> findByUserNameContaining(String user_name, Pageable pageable);

  AuthUser findByUserId(String userId);

  List<AuthUser> findByComOrg(ComOrg comOrg);

  @Transactional
  void deleteByUserId(String userId);


  @Query("SELECT COUNT(au) FROM AuthUser au WHERE au.userName LIKE %:keyword%")
  int countAll(@Param("keyword") String keyword);
}
