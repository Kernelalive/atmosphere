package eubrazil.atmosphere.controller;

import eubrazil.atmosphere.model.*;
import eubrazil.atmosphere.service.*;
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

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProcessingactivityController {

  @Autowired private ProcessingactivityService<Processingactivity> processingactivityService;

  @Autowired private LegalentityService<Legalentity, Legalentitycategory> legalentityService;

  @Autowired private ProcessingtypeService<Processingtypecategory> processingtypeService;

  @Autowired private LegalgroundService<Legalground, Legalgroundtype> legalgroundService;

  @Autowired private DatasubjectService<Datasubject> datasubjectService;

  @RequestMapping(value = "/processingactivity", method = RequestMethod.GET)
  public String processingactivityListView(
      HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/processingactivity", method = RequestMethod.POST)
  public String legalgroundList(Model model, @PageableDefault(sort = "name") Pageable pageable) {
    model.addAttribute("legalentitiesmap", legalentityService.legalEntitiesGroupedByCategory());
    model.addAttribute("processingtypes", processingtypeService.findProcessingTypeCategories());
    model.addAttribute("legalgrounds", legalgroundService.findLegalgrounds());
    model.addAttribute("page", new PageWrapper<>(processingactivityService.findAll(pageable), "/processingactivity"));
    return "processingactivity::list";
  }

  @RequestMapping(value = "/processingactivity/add", method = RequestMethod.GET)
  public String processingactivityAddView(
      HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/processingactivity/add", method = RequestMethod.POST)
  public String legalgroundAdd(Model model) {
    model.addAttribute("processingactivity", Optional.<Processingactivity>empty());
    model.addAttribute("legalentitiesmap", legalentityService.legalEntitiesGroupedByCategory());
    model.addAttribute("processingtypes", processingtypeService.findProcessingTypeCategories());
    model.addAttribute("legalgrounds", legalgroundService.findLegalgrounds());
    model.addAttribute("datasubjects", datasubjectService.findAllByOrderByName());
    return "processingactivity::add";
  }

  @RequestMapping(value = "/processingactivity/{id}", method = RequestMethod.GET)
  public String processingactivityBrowseView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/processingactivity/{id}", method = RequestMethod.POST)
  public String legalground(Model model, @PathVariable(value = "id") long id) {
    model.addAttribute("processingactivity", processingactivityService.findOne(id));
    model.addAttribute("legalentitiesmap", legalentityService.legalEntitiesGroupedByCategory());
    model.addAttribute("processingtypes", processingtypeService.findProcessingTypeCategories());
    model.addAttribute("legalgrounds", legalgroundService.findLegalgrounds());
    model.addAttribute("datasubjects", datasubjectService.findAllByOrderByName());
    return "processingactivity::edit";
  }

}
