package eubrazil.atmosphere.controller;

import eubrazil.atmosphere.model.Legalground;
import eubrazil.atmosphere.model.Legalgroundtype;
import eubrazil.atmosphere.service.LegalgroundService;
import eubrazil.atmosphere.util.PageWrapper;
import eubrazil.atmosphere.util.Util;
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
public class LegalgroundController {

  @Autowired
  private LegalgroundService<Legalground, Legalgroundtype> legalgroundService;

  @RequestMapping(value = "/legalground", method = RequestMethod.GET)
  public String legalgroundListView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/legalground", method = RequestMethod.POST)
  public String legalgroundList(Model model, @PageableDefault(sort = "name") Pageable pageable) {
    model.addAttribute("page", new PageWrapper<>(legalgroundService.findAll(pageable), "/legalground"));
    model.addAttribute("legalgroundtypes", legalgroundService.findLegalgroundtypes());
    return "legalground::list";
  }

  @RequestMapping(value = "/legalground/add", method = RequestMethod.GET)
  public String legalgroundAddView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
      redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
      return "redirect:/";
  }

  @RequestMapping(value = "/legalground/add", method = RequestMethod.POST)
  public String legalgroundAdd(Model model) {
    model.addAttribute("legalgroundtypes", legalgroundService.findLegalgroundtypes());
    return "legalground::add";
  }

  @RequestMapping(value = "/legalground/{id}", method = RequestMethod.GET)
  public String legalgroundBrowseView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/legalground/{id}", method = RequestMethod.POST)
  public String legalground(Model model, @PathVariable(value = "id") long id) {
    model.addAttribute("legalground", legalgroundService.findLegalGround(id));
    model.addAttribute("legalgroundtypes", legalgroundService.findLegalgroundtypes());
    return "legalground::edit";
  }

}
