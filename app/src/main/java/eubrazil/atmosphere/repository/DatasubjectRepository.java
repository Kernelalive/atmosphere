package eubrazil.atmosphere.repository;

import eubrazil.atmosphere.model.Datasubject;
import eubrazil.atmosphere.model.Pii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DatasubjectRepository extends JpaRepository<Datasubject, Long> {

  List<Datasubject> findAllByOrderById();

  List<Datasubject> findAllByOrderByName();

  Datasubject findByName(String name);

  @Query("select d.piis from Datasubject d order by d.id")
  List<Pii> findPiisInUse();
}
