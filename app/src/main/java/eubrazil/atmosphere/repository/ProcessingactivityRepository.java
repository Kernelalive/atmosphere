package eubrazil.atmosphere.repository;

import eubrazil.atmosphere.model.Legalground;
import eubrazil.atmosphere.model.Processingactivity;
import eubrazil.atmosphere.util.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ProcessingactivityRepository extends JpaRepository<Processingactivity, Long> {

  @Query("select countries from Processingactivity p join p.processingcountries as countries")
  List<Country> listCountriesWithProcessingActivities();

  @Query("select p.legalgrounds from Processingactivity p")
  List<Legalground> listLegalgroundsUsed();

/*  @Query("select p.processingtypecategories from Processingactivity p")
  List<Processingtypecategory> listProcessingTypesUsed();*/
}
