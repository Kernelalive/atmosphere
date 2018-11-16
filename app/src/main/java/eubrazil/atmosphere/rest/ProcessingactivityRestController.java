package eubrazil.atmosphere.rest;

import eubrazil.atmosphere.model.Processingactivity;
import eubrazil.atmosphere.rest.transfer.ProcessingActivityDTO;
import eubrazil.atmosphere.service.ProcessingactivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/processingactivity")
public class ProcessingactivityRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessingactivityRestController.class);

  @Autowired private ProcessingactivityService<Processingactivity> processingactivityService;

  @RequestMapping(method = RequestMethod.POST)
  public HttpEntity create(@RequestBody ProcessingActivityDTO processingActivityDTO) {
    try {
      processingactivityService.save(processingActivityDTO);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT)
  public HttpEntity edit(@RequestBody ProcessingActivityDTO processingActivityDTO) {
    try {
      processingactivityService.edit(processingActivityDTO);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public HttpEntity delete(@PathVariable("id") long id) {
    try {
      processingactivityService.delete(id);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

}
