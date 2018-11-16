package eubrazil.atmosphere.service.impl;

import eubrazil.atmosphere.model.*;
import eubrazil.atmosphere.repository.ProcessingactivityRepository;
import eubrazil.atmosphere.repository.ProcessingactivityinformationitemRepository;
import eubrazil.atmosphere.rest.transfer.ProcessingActivityDTO;
import eubrazil.atmosphere.service.*;
import eubrazil.atmosphere.service.exception.ProcessingActivityDoesNotExistException;
import eubrazil.atmosphere.service.transfer.Graphable;
import eubrazil.atmosphere.service.transfer.GraphableEdge;
import eubrazil.atmosphere.service.transfer.OrderedGraphableEdge;
import eubrazil.atmosphere.util.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ProcessingactivityServiceImpl
    implements ProcessingactivityService<Processingactivity> {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessingactivityServiceImpl.class);

  @Autowired
  private ProcessingactivityRepository processingactivityRepository;
  @Autowired
  private ProcessingactivityinformationitemRepository informationitemRepository;

  @Autowired
  private LegalentityService<Legalentity, Legalentitycategory> legalentityService;

  @Autowired
  private ProcessingtypeService<Processingtypecategory> processingtypeService;

  @Autowired
  private LegalgroundService<Legalground, Legalgroundtype> legalgroundService;

  @Autowired
  private DatasubjectService<Datasubject> datasubjectService;
  @Autowired
  private PiiService<Pii, Piicategory, PiiType> piiService;

  @Override
  public void save(ProcessingActivityDTO processingActivityDTO)
      throws Exception {

    Processingactivity pa = new Processingactivity();
    pa.setName(processingActivityDTO.getName());
    pa.setDescription(processingActivityDTO.getDescription());
    pa.setController(
        legalentityService.findOne(processingActivityDTO.getControllers().iterator().next()).get());
    pa.getProcessors()
        .addAll(
            processingActivityDTO
                .getProcessors()
                .stream()
                .map(v -> legalentityService.findOne(v).get())
                .collect(Collectors.toList()));
    pa.getRecipients()
        .addAll(
            processingActivityDTO
                .getRecipients()
                .stream()
                .map(v -> legalentityService.findOne(v).get())
                .collect(Collectors.toList()));
    pa.getLegalgrounds()
        .addAll(
            processingActivityDTO
                .getLegalgrounds()
                .stream()
                .map(v -> legalgroundService.findLegalGround(v).get())
                .collect(Collectors.toList()));
    pa.getProcessingtypes()
        .addAll(
            processingActivityDTO
                .getProcessingtypes()
                .stream()
                .map(v -> processingtypeService.findProcessingTypeCategory(v).get())
                .collect(Collectors.toList()));
    pa.getProcessingcountries()
        .addAll(
            processingActivityDTO
                .getProcessingcountries()
                .stream()
                .map(v -> Country.valueOf(v))
                .collect(Collectors.toList()));

    pa.getInformationitems()
        .addAll(
            processingActivityDTO
                .getInformationitems()
                .stream()
                .map(
                    item -> {
                      Processingactivityinformationitem var = null;
                      try {
                        var =
                            new Processingactivityinformationitem(
                                datasubjectService.findOne(item.getDatasubject()).get(),
                                piiService.findOne(item.getPii()).get());
                      } catch (Exception e) {
                        // TODO Auto-generated catch block
                        LOGGER.error(e.getMessage(), e);
                      }
                      return var;
                    })
                .collect(Collectors.toSet()));

    processingactivityRepository.save(pa);
  }

  @Override
  public void edit(ProcessingActivityDTO processingActivityDTO) throws Exception {
    Optional<Processingactivity> ref = findOne(processingActivityDTO.getId());
    if (!ref.isPresent()) {
      throw new ProcessingActivityDoesNotExistException();
    }

    Processingactivity refValue = ref.get();
    refValue.setName(processingActivityDTO.getName());
    refValue.setDescription(processingActivityDTO.getDescription());
    refValue.setController(
        legalentityService.findOne(processingActivityDTO.getControllers().iterator().next()).get());
    refValue.setProcessors(new TreeSet<Legalentity>());
    refValue
        .getProcessors()
        .addAll(
            processingActivityDTO
                .getProcessors()
                .stream()
                .map(v -> legalentityService.findOne(v).get())
                .collect(Collectors.toList()));
    refValue.setRecipients(new TreeSet<Legalentity>());
    refValue
        .getRecipients()
        .addAll(
            processingActivityDTO
                .getRecipients()
                .stream()
                .map(v -> legalentityService.findOne(v).get())
                .collect(Collectors.toList()));
    refValue.setLegalgrounds(new TreeSet<Legalground>());
    refValue
        .getLegalgrounds()
        .addAll(
            processingActivityDTO
                .getLegalgrounds()
                .stream()
                .map(v -> legalgroundService.findLegalGround(v).get())
                .collect(Collectors.toList()));
    refValue.setProcessingtypes(new TreeSet<Processingtypecategory>());
    refValue
        .getProcessingtypes()
        .addAll(
            processingActivityDTO
                .getProcessingtypes()
                .stream()
                .map(v -> processingtypeService.findProcessingTypeCategory(v).get())
                .collect(Collectors.toList()));
    refValue.setProcessingcountries(new TreeSet<Country>());
    refValue
        .getProcessingcountries()
        .addAll(
            processingActivityDTO
                .getProcessingcountries()
                .stream()
                .map(v -> Country.valueOf(v))
                .collect(Collectors.toList()));

    Set<Processingactivityinformationitem> copies =
        refValue.getInformationitems().stream().collect(Collectors.toSet());

    refValue.setInformationitems(new TreeSet<Processingactivityinformationitem>());
    refValue
        .getInformationitems()
        .addAll(
            processingActivityDTO
                .getInformationitems()
                .stream()
                .map(
                    item -> {
                      Processingactivityinformationitem var = null;
                      try {
                        var =
                            new Processingactivityinformationitem(
                                datasubjectService.findOne(item.getDatasubject()).get(),
                                piiService.findOne(item.getPii()).get());
                      } catch (Exception e) {
                        // TODO Auto-generated catch block
                        LOGGER.error(e.getMessage(), e);
                      }
                      return var;
                    })
                .collect(Collectors.toSet()));

    processingactivityRepository.save(refValue);

    if (!copies.isEmpty()) {
      informationitemRepository.delete(copies);
    }
  }

  @Override
  public void delete(long id) {
    processingactivityRepository.delete(id);
  }

  @Override
  public Page<Processingactivity> findAll(Pageable page) {
    return processingactivityRepository.findAll(page);
  }

  @Override
  public Optional<Processingactivity> findOne(long id) {
    return Optional.<Processingactivity>ofNullable(processingactivityRepository.findOne(id));
  }

  @Override
  public List<String> listCountryCodesForProcessingActivities() {
    return processingactivityRepository
        .listCountriesWithProcessingActivities()
        .stream()
        .map(c -> c.getAlpha2code().toLowerCase())
        .collect(Collectors.toList());
  }

  @Override
  public SortedMap<String, Long> legalGroundsUsage() {

    return processingactivityRepository
        .listLegalgroundsUsed()
        .stream()
        .map(lg -> lg.getLegalgroundtype())
        .collect(
            Collectors.groupingBy(
                Legalgroundtype::getName,
                TreeMap::new,
                Collectors.mapping(Function.identity(), Collectors.counting())));
  }


  @Override
  public List<Processingactivity> findAll() {
    return processingactivityRepository.findAll();
  }

  @Override
  @Transactional
  public SortedMap<GraphableEdge<Graphable>, Long> extractWeightedflows() {

    return processingactivityRepository
        .findAll()
        .stream()
        .flatMap(
            activity -> {
              List<GraphableEdge<Graphable>> lst = new ArrayList<GraphableEdge<Graphable>>();

              SortedMap<Datasubject, List<Pii>> informationItemMap =
                  activity
                      .getInformationitems()
                      .stream()
                      .collect(
                          Collectors.groupingBy(
                              Processingactivityinformationitem::getDatasubject,
                              TreeMap::new,
                              Collectors.mapping(
                                  Processingactivityinformationitem::getPii, Collectors.toList())));

              List<Legalgroundtype> legalgroundtypes =
                  activity
                      .getLegalgrounds()
                      .stream()
                      .map(Legalground::getLegalgroundtype)
                      .distinct()
                      .collect(Collectors.toList());

              List<Processingtypecategory> processingtypecategories =
                  activity
                      .getProcessingtypes()
                      .stream()
                      .distinct()
                      .collect(Collectors.toList());

              if (informationItemMap.isEmpty()
                  || legalgroundtypes.isEmpty()
                  || processingtypecategories.isEmpty()) {
                return Stream.<GraphableEdge<Graphable>>empty();
              }

              for (Datasubject ds : informationItemMap.keySet()) {
                for (Processingtypecategory ptc : processingtypecategories) {
                  lst.add(new OrderedGraphableEdge<Graphable>(ds, ptc));
                }
                for (Pii pii : informationItemMap.get(ds)) {
                  lst.add(new OrderedGraphableEdge<Graphable>(pii, ds));
                }
              }

              for (Processingtypecategory ptc : processingtypecategories) {
                for (Legalgroundtype lgt : legalgroundtypes) {
                  lst.add(new OrderedGraphableEdge<Graphable>(ptc, lgt));
                }
              }

              return lst.stream();
            })
        .collect(
            Collectors.groupingBy(
                Function.identity(),
                TreeMap::new,
                Collectors.mapping(GraphableEdge::id, Collectors.counting())));
  }


}
