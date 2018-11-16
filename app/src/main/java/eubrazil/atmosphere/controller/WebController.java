package eubrazil.atmosphere.controller;

import eubrazil.atmosphere.model.User;
import eubrazil.atmosphere.security.authentication.AuthenticationService;
import eubrazil.atmosphere.security.exception.UserNotFoundException;
import eubrazil.atmosphere.service.UserService;
import eubrazil.atmosphere.util.PageWrapper;
import eubrazil.atmosphere.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * The WebController class is responsible for UI rendering.
 *
 * @author Chris Paraskeva
 * @author George Tsiolis
 */
@Controller
public class WebController {

  private static final Logger LOGGER = LogManager.getLogger(WebController.class);

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private UserService<User> userService;

  /*
   * Index, header, footer, content
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String indexView() {
    return "index";
  }

  @RequestMapping(value = "/header", method = RequestMethod.POST)
  public String header() {
    return "inc::header";
  }

  @RequestMapping(value = "/home", method = RequestMethod.POST)
  public String content(Model model, HttpServletRequest request) {
    model.addAttribute("lang", RequestContextUtils.getLocale(request).toString());
    return "inc::content";
  }

  /*
   * Errors
   */
  @RequestMapping(value = "/error/404", method = RequestMethod.GET)
  public String error404View(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/error/404", method = RequestMethod.POST)
  public String error404() {
    return "error/404::page";
  }

  @RequestMapping(value = "/error/403", method = RequestMethod.GET)
  public String error403View(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/error/403", method = RequestMethod.POST)
  public String error403() {
    return "error/403::page";
  }

  @RequestMapping(value = "/error/500", method = RequestMethod.GET)
  public String error500View(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/error/500", method = RequestMethod.POST)
  public String error500() {
    return "error/500::page";
  }

  /*
   * Dashboard
   */
  @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
  public String dashboardView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
  public String dashboard(Model model) {
    return "dashboard::home";
  }

  /*
   * Registration
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String loginView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login() {
    return "auth::login";
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String registerView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String register() {
    return "auth::register";
  }

  /*
   * Account
   */
  @RequestMapping(value = "/account/profile/edit", method = RequestMethod.GET)
  public String profileEditView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/account/profile/edit", method = RequestMethod.POST)
  public String profileEdit(Model model) throws UserNotFoundException {
    User user;
    user = authenticationService.getCurrentUserFromDb();
    model.addAttribute("user", user);
    return "account::profile-edit-info";
  }

  @RequestMapping(value = "/account/password/edit", method = RequestMethod.GET)
  public String profileChangePasswordView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/account/password/edit", method = RequestMethod.POST)
  public String profileChangePassword(Model model) throws UserNotFoundException {
    User user;
    user = authenticationService.getCurrentUserFromDb();
    model.addAttribute("user", user);
    return "account::profile-edit-password";
  }

  /*
   * Users
   */
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public String userView(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("redirectToPage", Util.getFullUri(request));
    return "redirect:/";
  }

  @RequestMapping(value = "/users", method = RequestMethod.POST)
  public String user(Model model, @PageableDefault(size = PageWrapper.MAX_PAGE_ITEM_DISPLAY) Pageable pageable) throws UserNotFoundException {
    User user;
    user = authenticationService.getCurrentUserFromDb();
    model.addAttribute("authUser", user);
    model.addAttribute("page", new PageWrapper<>(userService.findAll(pageable), "/users"));
    return "user::list";
  }


  /*
   * Data Subjects
   */


}
