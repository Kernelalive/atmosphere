
package eubrazil.atmosphere.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

@Service
public interface DatasubjectService<S> {

  void create(S subject) throws Exception;

  void edit(S subject) throws Exception;

  void delete(long id) throws Exception;

  List<S> findAllByOrderById();

  List<S> findAllByOrderByName();

  Optional<S> findOne(long id) throws Exception;

  long count();

  Page<S> findAll(Pageable page);

  S findByName(String name) throws Exception;

  SortedMap<String, Long> piiOccurences();

}
