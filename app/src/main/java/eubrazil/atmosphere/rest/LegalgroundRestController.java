
package eubrazil.atmosphere.rest;

import eubrazil.atmosphere.model.Legalground;
import eubrazil.atmosphere.model.Legalgroundtype;
import eubrazil.atmosphere.service.LegalgroundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/legalground")
public class LegalgroundRestController {
  private static final Logger LOGGER = LoggerFactory.getLogger(LegalgroundRestController.class);

  @Autowired
  private LegalgroundService<Legalground, Legalgroundtype> legalgroundService;

  @RequestMapping(method = RequestMethod.POST)
  public HttpEntity create(@RequestBody Legalground legalground) {
    try {
      legalgroundService.save(legalground);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT)
  public HttpEntity edit(@RequestBody Legalground legalground) {
    try {
      legalgroundService.edit(legalground);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public HttpEntity delete(@PathVariable("id") long id) {
    try {
      legalgroundService.delete(id);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

}
