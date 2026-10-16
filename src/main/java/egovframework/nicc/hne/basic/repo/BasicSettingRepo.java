package egovframework.nicc.hne.basic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.nicc.hne.basic.model.entity.BasicSetting;

public interface BasicSettingRepo extends JpaRepository<BasicSetting, Integer> {

	List<BasicSetting> findAllBySettingType(BasicSetting.Type type);
}