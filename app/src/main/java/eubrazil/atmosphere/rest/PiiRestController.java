package eubrazil.atmosphere.rest;

import eubrazil.atmosphere.model.Pii;
import eubrazil.atmosphere.model.PiiType;
import eubrazil.atmosphere.model.Piicategory;
import eubrazil.atmosphere.service.PiiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pii")
public class PiiRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PiiRestController.class);
  @Autowired private PiiService<Pii, Piicategory, PiiType> piiService;


  @RequestMapping(method = RequestMethod.POST)
  public HttpEntity create(@RequestBody Pii pii) {
    piiService.create(pii);
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT)
  public HttpEntity edit(@RequestBody Pii pii) {
    piiService.edit(pii);
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public HttpEntity delete(@PathVariable("id") long id) {
    piiService.delete(id);
    return new ResponseEntity(HttpStatus.OK);
  }

}
