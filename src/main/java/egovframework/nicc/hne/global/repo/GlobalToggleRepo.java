package egovframework.nicc.hne.global.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.nicc.hne.global.model.entity.GlobalToggle;
import egovframework.nicc.hne.global.model.enumeration.GlobalType;

public interface GlobalToggleRepo extends JpaRepository<GlobalToggle, GlobalType>{

}
