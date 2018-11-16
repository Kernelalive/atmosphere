package eubrazil.atmosphere.service.impl;

import eubrazil.atmosphere.model.Legalentity;
import eubrazil.atmosphere.model.Legalentitycategory;
import eubrazil.atmosphere.repository.LegalentityRepository;
import eubrazil.atmosphere.repository.LegalentitycategoryRepository;
import eubrazil.atmosphere.service.LegalentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LegalentityServiceImpl implements LegalentityService<Legalentity, Legalentitycategory> {

  @Autowired private LegalentityRepository legalentityRepository;
  @Autowired private LegalentitycategoryRepository legalentitycategoryRepository;

  @Override
  public Page<Legalentity> findAll(Pageable page) {
    return legalentityRepository.findAll(page);
  }

  @Override
  public List<Legalentity> findLegalEntities() {
    return legalentityRepository.findAll();
  }

  @Override
  public List<Legalentitycategory> findLegalEntityCategories() {
    return legalentitycategoryRepository.findAll();
  }

  @Override
  public void save(Legalentity legalEntity) throws Exception {
    legalentityRepository.save(legalEntity);
  }

  @Override
  public void edit(Legalentity legalEntity) throws Exception {

    Legalentity refLegalentity = legalentityRepository.findOne(legalEntity.getId());

    if (refLegalentity == null) {
      throw new Exception();
    }

    refLegalentity.setAddress(legalEntity.getAddress());
    refLegalentity.setName(legalEntity.getName());
    refLegalentity.setRegno(legalEntity.getRegno());
    refLegalentity.setVat(legalEntity.getVat());
    refLegalentity.setZip(legalEntity.getZip());
    refLegalentity.setCountry(legalEntity.getCountry());
    refLegalentity.setCategory(legalEntity.getCategory());

    legalentityRepository.save(refLegalentity);
  }

  @Override
  public void delete(long id) throws Exception {
    legalentityRepository.delete(id);
  }

  @Override
  public Optional<Legalentity> findOne(long id) {
    return Optional.ofNullable(legalentityRepository.findOne(id));
  }

  @Override
  public SortedMap<Legalentitycategory, SortedSet<Legalentity>> legalEntitiesGroupedByCategory() {
    return findLegalEntities()
        .stream()
        .collect(
            Collectors.groupingBy(
                Legalentity::getCategory,
                TreeMap::new,
                Collectors.mapping(Function.identity(), Collectors.toCollection(TreeSet::new))));
  }
}
