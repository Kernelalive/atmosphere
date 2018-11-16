package eubrazil.atmosphere.rest;

import eubrazil.atmosphere.model.Legalentity;
import eubrazil.atmosphere.model.Legalentitycategory;
import eubrazil.atmosphere.service.LegalentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/legalentity")
public class LegalentityRestController {
  private static final Logger LOGGER = LoggerFactory.getLogger(LegalentityRestController.class);

  @Autowired private LegalentityService<Legalentity, Legalentitycategory> legalentityService;

  @RequestMapping(method = RequestMethod.POST)
  public HttpEntity create(@RequestBody Legalentity legalentity) {
    try {
      legalentityService.save(legalentity);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT)
  public HttpEntity edit(@RequestBody Legalentity legalentity) {
    try {
      legalentityService.edit(legalentity);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public HttpEntity delete(@PathVariable("id") long id) {
    try {
      legalentityService.delete(id);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

}
