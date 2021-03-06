package eubrazil.atmosphere.repository;

import eubrazil.atmosphere.model.Processingtypecategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProcessingtypecategoryRepository extends JpaRepository<Processingtypecategory, Long> {}
