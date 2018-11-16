package eubrazil.atmosphere.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LegalgroundService<LG, LGT> {
  void save(LG legalground);

  void edit(LG legalground) throws Exception;

  void delete(long id);

  Page<LG> findAll(Pageable page);

  List<LG> findLegalgrounds();

  List<LGT> findLegalgroundtypes();

  Optional<LG> findLegalGround(long id);
}
