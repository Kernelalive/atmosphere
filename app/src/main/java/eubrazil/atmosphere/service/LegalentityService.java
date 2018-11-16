package eubrazil.atmosphere.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.SortedSet;

@Service
public interface LegalentityService<LE, LEC> {

  Page<LE> findAll(Pageable page);

  List<LE> findLegalEntities();

  List<LEC> findLegalEntityCategories();

  Optional<LE> findOne(long id);

  void save(LE legalEntity) throws Exception;

  void edit(LE legalEntity) throws Exception;

  void delete(long id) throws Exception;

  SortedMap<LEC, SortedSet<LE>> legalEntitiesGroupedByCategory();

}
