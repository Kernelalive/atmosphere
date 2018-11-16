package eubrazil.atmosphere.service.impl;

import eubrazil.atmosphere.model.Pii;
import eubrazil.atmosphere.model.PiiType;
import eubrazil.atmosphere.model.Piicategory;
import eubrazil.atmosphere.repository.PiiCategoryRepository;
import eubrazil.atmosphere.repository.PiiRepository;
import eubrazil.atmosphere.service.PiiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Transactional
public class PiiServiceImpl implements PiiService<Pii, Piicategory, PiiType> {

  @Autowired
  PiiRepository piiRepository;

  @Autowired
  PiiCategoryRepository categoryRepository;

  @Override
  public void create(Pii pii) {
    piiRepository.save(pii);
  }

  @Override
  public void edit(Pii pii) {
    piiRepository.save(pii);
  }

  @Override
  public void delete(long id) {
    piiRepository.delete(id);
  }

  @Override
  public Page<Pii> findAll(Pageable page) {
    return piiRepository.findAll(page);
  }

  @Override
  public List<Pii> findAll() {
    return piiRepository.findAll();
  }

  @Override
  public List<Pii> findByType(PiiType type) {
    return piiRepository.findByType(type);
  }

  @Override
  public SortedMap<Piicategory, SortedSet<Pii>> piisGroupedByCategory() {
    return findAll()
        .stream()
        .collect(
            Collectors.groupingBy(
                Pii::getCategory,
                TreeMap::new,
                Collectors.mapping(Function.identity(), Collectors.toCollection(TreeSet::new))));
  }

  @Override
  public SortedMap<Piicategory, SortedSet<Pii>> piisByTypeGroupedByCategory(PiiType type) {
    return findByType(type)
        .stream()
        .collect(
            Collectors.groupingBy(
                Pii::getCategory,
                TreeMap::new,
                Collectors.mapping(Function.identity(), Collectors.toCollection(TreeSet::new))));
  }

  @Override
  public Optional<Pii> findOne(long id) {
    return Optional.ofNullable(piiRepository.findOne(id));
  }

  @Override
  public List<Piicategory> findCategories() {
    return categoryRepository.findAllByOrderByName();
  }

}
