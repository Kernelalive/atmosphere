package eubrazil.atmosphere.service.impl;

import eubrazil.atmosphere.model.Processingtypecategory;
import eubrazil.atmosphere.repository.ProcessingtypecategoryRepository;
import eubrazil.atmosphere.service.ProcessingtypeService;
import eubrazil.atmosphere.service.exception.ProcessingTypeCategoryDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProcessingtypeServiceImpl implements ProcessingtypeService<Processingtypecategory> {

  @Autowired private ProcessingtypecategoryRepository processingtypecategoryRepository;

  @Override
  public void saveProcessingTypeCategory(Processingtypecategory processingtypecategory) {
    processingtypecategoryRepository.save(processingtypecategory);
  }

  @Override
  public void editProcessingTypeCategory(Processingtypecategory processingtypecategory)
      throws ProcessingTypeCategoryDoesNotExistException {

    Optional<Processingtypecategory> ref =
        findProcessingTypeCategory(processingtypecategory.getId());

    if (!ref.isPresent()) {
      throw new ProcessingTypeCategoryDoesNotExistException();
    }

    Processingtypecategory refValue = ref.get();
    refValue.setName(processingtypecategory.getName());
    refValue.setDescription(processingtypecategory.getDescription());

    processingtypecategoryRepository.save(refValue);
  }

  @Override
  public void deleteProcessingTypeCategory(long id) {
    processingtypecategoryRepository.delete(id);
  }

  @Override
  public List<Processingtypecategory> findProcessingTypeCategories() {
    return processingtypecategoryRepository.findAll();
  }

  @Override
  public Page<Processingtypecategory> findProcessingTypeCategories(Pageable pageable) {
    return processingtypecategoryRepository.findAll(pageable);
  }

  @Override
  public Optional<Processingtypecategory> findProcessingTypeCategory(long id) {
    return Optional.ofNullable(processingtypecategoryRepository.findOne(id));
  }
}
