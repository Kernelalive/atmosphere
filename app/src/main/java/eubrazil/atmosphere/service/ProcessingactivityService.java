package eubrazil.atmosphere.service;

import eubrazil.atmosphere.rest.transfer.ProcessingActivityDTO;
import eubrazil.atmosphere.service.transfer.Graphable;
import eubrazil.atmosphere.service.transfer.GraphableEdge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

@Service
public interface ProcessingactivityService<PA> {

  void save(ProcessingActivityDTO processingActivityDTO) throws Exception;

  void edit(ProcessingActivityDTO processingActivityDTO) throws Exception;

  void delete(long id);

  List<PA> findAll();

  Page<PA> findAll(Pageable page);

  Optional<PA> findOne(long id);

  List<String> listCountryCodesForProcessingActivities();

  SortedMap<String, Long> legalGroundsUsage();

  SortedMap<GraphableEdge<Graphable>, Long> extractWeightedflows();

}
