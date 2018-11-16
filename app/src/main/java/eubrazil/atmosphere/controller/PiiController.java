package eubrazil.atmosphere.controller;

import eubrazil.atmosphere.model.Pii;
import eubrazil.atmosphere.model.PiiIdentification;
import eubrazil.atmosphere.model.PiiType;
import eubrazil.atmosphere.model.Piicategory;
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

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PiiController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PiiController.class);

  @Autowired private PiiService<Pii, Piicategory, PiiType> piiService;

  @RequestMapping(value = "/pii", method = RequestMethod.GET)
  public String piiListView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/pii", method = RequestMethod.POST)
  public String piiListView(Model model, @PageableDefault(sort = "name") Pageable pageable) {
    model.addAttribute("page", new PageWrapper<>(piiService.findAll(pageable), "/pii"));
    return "pii::list";
  }

  @RequestMapping(value = "/pii/add", method = RequestMethod.GET)
  public String piiAdd(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/pii/add", method = RequestMethod.POST)
  public String pii(Model model) {
    model.addAttribute("piiTypes", Arrays.asList(PiiType.values()));
    model.addAttribute("piiIdentifications", Arrays.asList(PiiIdentification.values()));
    model.addAttribute("piiCategories", piiService.findCategories());
    return "pii::add";
  }

  @RequestMapping(value = "/pii/{id}", method = RequestMethod.GET)
  public String piiEditView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/pii/{id}", method = RequestMethod.POST)
  public String piiEditView(Model model, @PathVariable(value = "id") long id) {
    model.addAttribute("pii", piiService.findOne(id));
    model.addAttribute("piiTypes", Arrays.asList(PiiType.values()));
    model.addAttribute("piiIdentifications", Arrays.asList(PiiIdentification.values()));
    model.addAttribute("piiCategories", piiService.findCategories());
    return "pii::edit";
  }

}
