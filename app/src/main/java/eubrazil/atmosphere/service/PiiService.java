
package eubrazil.atmosphere.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.SortedSet;

@Service
public interface PiiService<PII, PIIC, PIIT> {

  void create(PII pii);

  void edit(PII pii);

  void delete(long id);

  Page<PII> findAll(Pageable page);

  List<PII> findAll();

  List<PII> findByType(PIIT piit);

  SortedMap<PIIC, SortedSet<PII>> piisGroupedByCategory();

  SortedMap<PIIC, SortedSet<PII>> piisByTypeGroupedByCategory(PIIT piit);

  Optional<PII> findOne(long id);

  List<PIIC> findCategories();

}
