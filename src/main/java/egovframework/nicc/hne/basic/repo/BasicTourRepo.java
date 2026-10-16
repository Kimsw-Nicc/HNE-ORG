package egovframework.nicc.hne.basic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.nicc.hne.basic.model.entity.BasicTour;

public interface BasicTourRepo extends JpaRepository<BasicTour, Integer> {
}