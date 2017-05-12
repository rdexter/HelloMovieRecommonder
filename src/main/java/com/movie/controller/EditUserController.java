package com.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movie.entity.UserEntity;
import com.movie.service.SecurityService;
import com.movie.service.UserManager;
import com.movie.validator.UserValidator;

@Controller
public class EditUserController {
	@Autowired
	UserManager userService;
	
	@Autowired
   private UserValidator userValidator;
	
	@Autowired
   private SecurityService securityService;
	
   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public String login(ModelMap model) {
       return "login";
   }
	
   @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
   public String loginerror(ModelMap model) {
       model.addAttribute("error", "true");
       return "denied";
   }

   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String logout(ModelMap model) {
       return "logout";
   }
   @RequestMapping(value = "/registration", method = RequestMethod.GET)
   public String registration(Model model) {
       model.addAttribute("userForm", new UserEntity());

       return "registration";
   }

   @RequestMapping(value = "/registration", method = RequestMethod.POST)
   public String registration(@ModelAttribute("userForm") UserEntity userForm, BindingResult bindingResult, Model model) {
       userValidator.validate(userForm, bindingResult);

       if (bindingResult.hasErrors()) {
           return "registration";
       }

       userService.addUser(userForm);

       securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

       return "redirect:/home2";
   }
}
