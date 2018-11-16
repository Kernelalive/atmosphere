package eubrazil.atmosphere.controller;

import eubrazil.atmosphere.model.Datasubject;
import eubrazil.atmosphere.model.Pii;
import eubrazil.atmosphere.model.PiiType;
import eubrazil.atmosphere.model.Piicategory;
import eubrazil.atmosphere.service.DatasubjectService;
import eubrazil.atmosphere.service.PiiService;
import eubrazil.atmosphere.util.PageWrapper;
import eubrazil.atmosphere.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DatasubjectController {

  private static final Logger LOGGER = LoggerFactory.getLogger(DatasubjectController.class);

  @Autowired private DatasubjectService<Datasubject> datasubjectService;
  @Autowired private PiiService<Pii, Piicategory, PiiType> piiService;

  @RequestMapping(value = "/datasubject", method = RequestMethod.GET)
  public String subjectListView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/datasubject", method = RequestMethod.POST)
  public String subjectListView(Model model, @PageableDefault(size = PageWrapper.MAX_PAGE_ITEM_DISPLAY, sort = "name") Pageable pageable) {
    model.addAttribute("page", new PageWrapper<>(datasubjectService.findAll(pageable), "/datasubject"));
    return "datasubject::list";
  }

  @RequestMapping(value = "/datasubject/add", method = RequestMethod.GET)
  public String subjectAdd(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/datasubject/add", method = RequestMethod.POST)
  public String subject(Model model, final RedirectAttributes redirectAttributes) {
    model.addAttribute("piis", piiService.piisByTypeGroupedByCategory(PiiType.N));
    model.addAttribute("piisSpecial", piiService.piisByTypeGroupedByCategory(PiiType.S));
    return "datasubject::add";
  }

  @RequestMapping(value = "/datasubject/{id}", method = RequestMethod.GET)
  public String subjectEditView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/datasubject/{id}", method = RequestMethod.POST)
  public String subjectEditView(Model model, @PathVariable(value = "id") long id) {
    try {
      model.addAttribute("datasubject", datasubjectService.findOne(id));
      model.addAttribute("piis", piiService.piisByTypeGroupedByCategory(PiiType.N));
      model.addAttribute("piisSpecial", piiService.piisByTypeGroupedByCategory(PiiType.S));
    } catch (Exception exc) {
      LOGGER.error(exc.getMessage(), exc);
    }
    return "datasubject::edit";
  }

}
