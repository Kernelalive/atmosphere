package eubrazil.atmosphere.service.impl;

import eubrazil.atmosphere.model.Legalground;
import eubrazil.atmosphere.model.Legalgroundtype;
import eubrazil.atmosphere.repository.LegalgroundRepository;
import eubrazil.atmosphere.repository.LegalgroundtypeRepository;
import eubrazil.atmosphere.service.LegalgroundService;
import eubrazil.atmosphere.service.exception.LegalgroundDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LegalgroundServiceImpl implements LegalgroundService<Legalground, Legalgroundtype> {

  @Autowired
  private LegalgroundRepository legalgroundRepository;
  @Autowired
  private LegalgroundtypeRepository legalgroundtypeRepository;

  @Override
  public void save(Legalground legalground) {
    legalgroundRepository.save(legalground);
  }

  @Override
  public void edit(Legalground legalground) throws Exception {
    Optional<Legalground> ref = findLegalGround(legalground.getId());
    if (!ref.isPresent()) {
      throw new LegalgroundDoesNotExistException();
    }
    Legalground refValue = ref.get();
    refValue.setName(legalground.getName());
    refValue.setComments(legalground.getComments());
    refValue.setLegalgroundtype(legalground.getLegalgroundtype());
    legalgroundRepository.save(legalground);
  }

  @Override
  public void delete(long id) {
    legalgroundRepository.delete(id);
  }

  @Override
  public Page<Legalground> findAll(Pageable page) {
    return legalgroundRepository.findAll(page);
  }

  @Override
  public List<Legalground> findLegalgrounds() {
    return legalgroundRepository.findAll();
  }

  @Override
  public List<Legalgroundtype> findLegalgroundtypes() {
    return legalgroundtypeRepository.findAll();
  }

  @Override
  public Optional<Legalground> findLegalGround(long id) {
    return Optional.ofNullable(legalgroundRepository.findOne(id));
  }
}
