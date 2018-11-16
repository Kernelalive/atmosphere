package eubrazil.atmosphere.repository;

import eubrazil.atmosphere.model.Piicategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PiiCategoryRepository extends JpaRepository<Piicategory, Long> {

  List<Piicategory> findAllByOrderByName();

}
