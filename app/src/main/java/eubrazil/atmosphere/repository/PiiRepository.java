package eubrazil.atmosphere.repository;

import eubrazil.atmosphere.model.Pii;
import eubrazil.atmosphere.model.PiiType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PiiRepository extends JpaRepository<Pii, Long> {

  List<Pii> findAllByOrderByName();

  List<Pii> findByType(PiiType type);

}
