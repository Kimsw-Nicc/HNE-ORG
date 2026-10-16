package egovframework.nicc.hne.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.basic.model.dto.BasicTourDTO;
import egovframework.nicc.hne.basic.model.entity.BasicTour;
import egovframework.nicc.hne.basic.repo.BasicTourRepo;
import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class TourService {

	@Autowired
	BasicTourRepo tourRepo;
	@Autowired
	ComOrgRepo orgRepo;

	@Autowired
	ModelConverter modelConverter;

	public BasicTourDTO getTour(Integer tourCd) {
		return modelConverter.map(tourRepo.findOne(tourCd), BasicTourDTO.class);
	}

	public List<BasicTourDTO> getTours(Pageable pageable) {
		return modelConverter.mapList(tourRepo.findAll(pageable).getContent(), BasicTourDTO.class);
	}

	public List<BasicTourDTO> getTours() {
		return modelConverter.mapList(tourRepo.findAll(), BasicTourDTO.class);
	}

	public String updateTourFilename(BasicTourDTO tourDTO) {
		BasicTour tour = tourRepo.findOne(tourDTO.getTourCd());
		String filename = tour.getTourFilename();
		tour.setTourFilename(tourDTO.getTourFilename());
		tourRepo.save(tour);
		if (filename.equals(tourDTO.getTourFilename())) {
			return null;
		}
		return filename;
	}

	public void updateTourOriginFilename(BasicTourDTO tourDTO) {
		BasicTour tour = tourRepo.findOne(tourDTO.getTourCd());
		tour.setTourOriginFilename(tourDTO.getTourOriginFilename());
		tourRepo.save(tour);
	}

	public void updateTourName(BasicTourDTO tourDTO) {
		BasicTour tour = tourRepo.findOne(tourDTO.getTourCd());
		tour.setTourName(tourDTO.getTourName());
		tourRepo.save(tour);
	}

	public void updateTourOrg(BasicTourDTO tourDTO) {
		BasicTour tour = tourRepo.findOne(tourDTO.getTourCd());
		tourRepo.save(tour);
	}

	public void insertTour(BasicTourDTO tourDTO) {
		BasicTour tour = modelConverter.map(tourDTO, BasicTour.class);
		tourRepo.save(tour);
	}

	public String deleteTour(Integer tourCd) {
		BasicTour tour = tourRepo.findOne(tourCd);
		tourRepo.delete(tourCd);
		return tour.getTourFilename();
	}

	public Long countTour() {
		return tourRepo.count();
	}

}
