package eubrazil.atmosphere.rest;

import eubrazil.atmosphere.model.Processingactivity;
import eubrazil.atmosphere.rest.transfer.ProcessingActivityDTO;
import eubrazil.atmosphere.rest.transfer.SankeyDataSeries;
import eubrazil.atmosphere.service.ProcessingactivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gdpr")
public class GdprRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(GdprRestController.class);

  @Autowired
  private ProcessingactivityService<Processingactivity> processingactivityService;

  @RequestMapping(value = "/data-flow", method = RequestMethod.POST)
  public HttpEntity dataFlowsMap() {
    try {
      return new ResponseEntity(new SankeyDataSeries(processingactivityService.extractWeightedflows()), HttpStatus.OK);

    } catch (Exception e) {
      LOGGER.error("Failed to generate dashboard stats.", e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}
