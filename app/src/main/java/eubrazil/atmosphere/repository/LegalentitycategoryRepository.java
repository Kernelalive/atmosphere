package eubrazil.atmosphere.repository;

import eubrazil.atmosphere.model.Legalentitycategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LegalentitycategoryRepository extends JpaRepository<Legalentitycategory, Long> {}
