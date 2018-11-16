package eubrazil.atmosphere.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProcessingtypeService<PTC> {

  void saveProcessingTypeCategory(PTC processingtypecategory);

  void editProcessingTypeCategory(PTC processingtypecategory) throws Exception;

  void deleteProcessingTypeCategory(long id);

  List<PTC> findProcessingTypeCategories();

  Page<PTC> findProcessingTypeCategories(Pageable pageable);

  Optional<PTC> findProcessingTypeCategory(long id);

}
