package eubrazil.atmosphere.controller;

import eubrazil.atmosphere.model.*;
import eubrazil.atmosphere.service.*;
import eubrazil.atmosphere.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GdprController {

  @Autowired
  private ProcessingactivityService<Processingactivity> processingactivityService;

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

  @RequestMapping(value = "/gdpr-dataflow", method = RequestMethod.GET)
  public String dataflowView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/gdpr-dataflow", method = RequestMethod.POST)
  public String dataflowView(Model model, @PageableDefault(sort = "name") Pageable pageable) {
    model.addAttribute("legalentitiesmap", legalentityService.legalEntitiesGroupedByCategory());
    model.addAttribute("processingtypes", processingtypeService.findProcessingTypeCategories());
    model.addAttribute("legalgrounds", legalgroundService.findLegalgrounds());
    model.addAttribute("datasubjects", datasubjectService.findAllByOrderByName());
    model.addAttribute("piis", piiService.piisGroupedByCategory());
    return "gdpr::dataflow";
  }

}
