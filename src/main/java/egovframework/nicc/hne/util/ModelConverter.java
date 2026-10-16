package egovframework.nicc.hne.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import egovframework.nicc.hne.basic.repo.BasicGalleryRepo;
import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.guide.repo.GuideBuildingRepo;
import egovframework.nicc.hne.guide.repo.GuideFloorRepo;

@Component
public class ModelConverter {

  ModelMapper modelMapper = new ModelMapper();

  @Autowired
  BasicGalleryRepo galleryRepo;
  @Autowired
  ComOrgRepo comOrgRepo;
  @Autowired
  GuideBuildingRepo guideBuildingRepo;
  @Autowired
  GuideFloorRepo guideFloorRepo;

  public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
    List<T> result = new ArrayList<>();
    for (S element : source) {
      T mappedElement = modelMapper.map(element, targetClass);
      result.add(mappedElement);
    }
    return result;
  }

  public <S, T> T map(S source, Class<T> targetClass) {
    return modelMapper.map(source, targetClass);
  }
}
