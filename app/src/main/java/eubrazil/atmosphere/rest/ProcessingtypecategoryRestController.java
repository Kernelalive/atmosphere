package eubrazil.atmosphere.rest;

import eubrazil.atmosphere.model.Processingtypecategory;
import eubrazil.atmosphere.service.ProcessingtypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/processingtypecategory")
public class ProcessingtypecategoryRestController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessingtypecategoryRestController.class);

  @Autowired
  private ProcessingtypeService<Processingtypecategory> processingtypeService;

  @RequestMapping(method = RequestMethod.POST)
  public HttpEntity create(@RequestBody Processingtypecategory processingtypecategory) {
    try {
      processingtypeService.saveProcessingTypeCategory(processingtypecategory);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT)
  public HttpEntity edit(@RequestBody Processingtypecategory processingtypecategory) {
    try {
      processingtypeService.editProcessingTypeCategory(processingtypecategory);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public HttpEntity delete(@PathVariable("id") long id) {
    try {
      processingtypeService.deleteProcessingTypeCategory(id);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

}
