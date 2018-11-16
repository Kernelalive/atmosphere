package eubrazil.atmosphere.rest;

import eubrazil.atmosphere.model.Datasubject;
import eubrazil.atmosphere.service.DatasubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/datasubject")
public class DatasubjectRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(DatasubjectRestController.class);
  @Autowired private DatasubjectService<Datasubject> datasubjectService;

  @RequestMapping(value = "/{id}/piis", method = RequestMethod.GET)
  public HttpEntity piis(@PathVariable("id") long id) {
    try {
      Optional<Datasubject> datasubject = datasubjectService.findOne(id);
      if (datasubject.isPresent()) {
        return new ResponseEntity(datasubject.get().getPiis(), HttpStatus.OK);
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST)
  public HttpEntity create(@RequestBody Datasubject datasubject) {
    try {
      datasubjectService.create(datasubject);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT)
  public HttpEntity edit(@RequestBody Datasubject datasubject) {
    try {
      datasubjectService.edit(datasubject);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public HttpEntity delete(@PathVariable("id") long id) {
    try {
      datasubjectService.delete(id);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

}
