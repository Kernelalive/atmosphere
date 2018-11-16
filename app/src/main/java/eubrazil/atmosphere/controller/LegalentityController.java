package eubrazil.atmosphere.controller;

import eubrazil.atmosphere.model.Legalentity;
import eubrazil.atmosphere.model.Legalentitycategory;
import eubrazil.atmosphere.service.LegalentityService;
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
public class LegalentityController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LegalentityController.class);

  @Autowired private LegalentityService<Legalentity, Legalentitycategory> legalentityService;

  @RequestMapping(value = "/legalentity", method = RequestMethod.GET)
  public String legalentityListView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/legalentity", method = RequestMethod.POST)
  public String legalentityListView(Model model, @PageableDefault(sort = "name") Pageable pageable) {
    model.addAttribute("page", new PageWrapper<>(legalentityService.findAll(pageable), "/legalentity"));
    model.addAttribute("legalentitycategories", legalentityService.findLegalEntityCategories());
    return "legalentity::list";
  }

  @RequestMapping(value = "/legalentity/add", method = RequestMethod.GET)
  public String legalentityAdd(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/legalentity/add", method = RequestMethod.POST)
  public String legalentity(Model model, final RedirectAttributes redirectAttributes) {
    model.addAttribute("legalentitycategories", legalentityService.findLegalEntityCategories());
    return "legalentity::add";
  }

  @RequestMapping(value = "/legalentity/{id}", method = RequestMethod.GET)
  public String legalentityEditView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/legalentity/{id}", method = RequestMethod.POST)
  public String legalentityEditView(Model model, @PathVariable(value = "id") long id) {
    try {
      model.addAttribute("legalentity", legalentityService.findOne(id));
      model.addAttribute("legalentitycategories", legalentityService.findLegalEntityCategories());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    return "legalentity::edit";
  }

}
