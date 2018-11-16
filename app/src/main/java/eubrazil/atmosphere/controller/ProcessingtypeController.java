package eubrazil.atmosphere.controller;

import eubrazil.atmosphere.model.Processingtypecategory;
import eubrazil.atmosphere.service.ProcessingtypeService;
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
public class ProcessingtypeController {

  @Autowired private ProcessingtypeService<Processingtypecategory> processingtypeService;

  @RequestMapping(value = "/processingtypecategory", method = RequestMethod.GET)
  public String processingtypecategoryListView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/processingtypecategory", method = RequestMethod.POST)
  public String processingtypecategoryList(Model model, @PageableDefault(sort = "name") Pageable pageable) {
    model.addAttribute("page", new PageWrapper<>(processingtypeService.findProcessingTypeCategories(pageable), "/processingtypecategory"));
    return "processingtype::list";
  }

  @RequestMapping(value = "/processingtypecategory/add", method = RequestMethod.GET)
  public String processingtypecategoryAddView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/processingtypecategory/add", method = RequestMethod.POST)
  public String processingtypecategoryAdd(Model model) {
    return "processingtype::add";
  }

  @RequestMapping(value = "/processingtypecategory/{id}", method = RequestMethod.GET)
  public String processingtypecategoryBrowseView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/processingtypecategory/{id}", method = RequestMethod.POST)
  public String processingtypecategory(Model model, @PathVariable(value = "id") long id) {
    model.addAttribute("processingtypecategory", processingtypeService.findProcessingTypeCategory(id));
    return "processingtype::edit";
  }

}
