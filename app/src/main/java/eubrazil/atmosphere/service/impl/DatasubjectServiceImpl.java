
package eubrazil.atmosphere.service.impl;

import eubrazil.atmosphere.model.Datasubject;
import eubrazil.atmosphere.model.Pii;
import eubrazil.atmosphere.repository.DatasubjectRepository;
import eubrazil.atmosphere.service.DatasubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Transactional
public class DatasubjectServiceImpl implements DatasubjectService<Datasubject> {

  @Autowired
  private DatasubjectRepository datasubjectRepository;

  @Override
  public void create(Datasubject subject) throws Exception {
    datasubjectRepository.save(subject);
  }

  @Override
  public List<Datasubject> findAllByOrderById() {
    return datasubjectRepository.findAllByOrderById();
  }

  @Override
  public void delete(long id) throws Exception {
    datasubjectRepository.delete(id);
  }

  @Override
  public Optional<Datasubject> findOne(long id) throws Exception {
    return Optional.ofNullable(datasubjectRepository.findOne(id));
  }

  @Override
  public void edit(Datasubject subject) throws Exception {
    datasubjectRepository.save(subject);
  }

  @Override
  public long count() {
    return datasubjectRepository.count();
  }

  @Override
  public Page<Datasubject> findAll(Pageable pageable) {
    return datasubjectRepository.findAll(pageable);
  }

  @Override
  public List<Datasubject> findAllByOrderByName() {
    return datasubjectRepository.findAllByOrderByName();
  }

  @Override
  public Datasubject findByName(String name) throws Exception {
    return datasubjectRepository.findByName(name);
  }

  @Override
  public SortedMap<String, Long> piiOccurences() {

    return datasubjectRepository
        .findPiisInUse()
        .stream()
        .collect(
            Collectors.groupingBy(
                Pii::getName,
                TreeMap::new,
                Collectors.mapping(Function.identity(), Collectors.counting())));
  }

}
