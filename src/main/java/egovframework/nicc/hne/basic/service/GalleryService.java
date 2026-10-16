package egovframework.nicc.hne.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.basic.model.dto.BasicGalleryDTO;
import egovframework.nicc.hne.basic.model.entity.BasicGallery;
import egovframework.nicc.hne.basic.repo.BasicGalleryRepo;
import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class GalleryService {

  @Autowired
  BasicGalleryRepo galleryRepo;
  @Autowired
  ComOrgRepo orgRepo;

  @Autowired
  ModelConverter modelConverter;

  // 페이징 전부 가져오기
  public List<BasicGalleryDTO> getGalleries(Pageable pageable) {
    List<BasicGallery> galleries = galleryRepo.findAll(pageable).getContent();
    return modelConverter.mapList(galleries, BasicGalleryDTO.class);
  }

  // 페이징 부서코드로 가져오기
  public List<BasicGalleryDTO> getGalleries(Pageable pageable, String orgCd) {
    List<BasicGallery> galleries = galleryRepo.findByOrgCd(pageable, orgCd);
    return modelConverter.mapList(galleries, BasicGalleryDTO.class);
  }

  // 일반 전부 가져오기
  public List<BasicGalleryDTO> getGalleriesAll() {
    return modelConverter.mapList(galleryRepo.findAll(), BasicGalleryDTO.class);
  }

  // 일반 부서코드로 가져오기
  public List<BasicGalleryDTO> getGalleriesAll(String orgCd) {
    return modelConverter.mapList(galleryRepo.findByOrgCd(orgCd), BasicGalleryDTO.class);
  }

  // NULL 가져오기
  public List<BasicGalleryDTO> getGalleriesNull() {
    return modelConverter.mapList(galleryRepo.findByOrgCdIsNull(), BasicGalleryDTO.class);
  }

  // 하나 가져오기
  public BasicGalleryDTO getGallery(Integer galleryCd) {
    return modelConverter.map(galleryRepo.findOne(galleryCd), BasicGalleryDTO.class);
  }

  // 개수 가져오기
  public Long getCount(String orgCd) {
    if (orgCd.equals("none"))
      return galleryRepo.count();
    else
      return galleryRepo.countByOrgCd(orgCd);
  }

  // INSERT
  public void insertGallery(BasicGalleryDTO galleryDTO) {
    BasicGallery gallery = modelConverter.map(galleryDTO, BasicGallery.class);
    gallery.setComOrg(orgRepo.findOne(galleryDTO.getOrgCd()));
    galleryRepo.save(gallery);
  }

  // UPDATE
  public String updateGalleryFilename(BasicGalleryDTO galleryDTO) {
    BasicGallery gallery = galleryRepo.findOne(galleryDTO.getGalleryCd());
    String filename = gallery.getGalleryFilename();
    gallery.setGalleryFilename(galleryDTO.getGalleryFilename());
    galleryRepo.save(gallery);
    if (filename.equals(galleryDTO.getGalleryFilename())) {
      return null;
    }
    return filename;
  }

  public void updateGalleryOriginFilename(BasicGalleryDTO galleryDTO) {
    BasicGallery gallery = galleryRepo.findOne(galleryDTO.getGalleryCd());
    gallery.setGalleryOriginFilename(galleryDTO.getGalleryOriginFilename());
    galleryRepo.save(gallery);
  }

  public void updateGalleryName(BasicGalleryDTO galleryDTO) {
    BasicGallery gallery = galleryRepo.findOne(galleryDTO.getGalleryCd());
    gallery.setGalleryName(galleryDTO.getGalleryName());
    galleryRepo.save(gallery);
  }

  public void updateGalleryOrg(BasicGalleryDTO galleryDTO) {
    BasicGallery gallery = galleryRepo.findOne(galleryDTO.getGalleryCd());
    gallery.setComOrg(orgRepo.findOne(galleryDTO.getOrgCd()));
    galleryRepo.save(gallery);
  }

  public void updateGallerySort(BasicGalleryDTO galleryDTO) {
    BasicGallery gallery = galleryRepo.findOne(galleryDTO.getGalleryCd());
    gallery.setGallerySort(galleryDTO.getGallerySort());
    galleryRepo.save(gallery);
  }

  // DELETE
  public String deleteGallery(Integer galleryCd) {
    BasicGallery gallery = galleryRepo.findOne(galleryCd);
    galleryRepo.delete(galleryCd);
    return gallery.getGalleryFilename();
  }

}
