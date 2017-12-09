package uutiset.wepauutiset.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uutiset.wepauutiset.domain.Newswriter;
import uutiset.wepauutiset.repository.NewsWriterRepository;
import uutiset.wepauutiset.service.NewsValidatorService;
import uutiset.wepauutiset.service.UserService;

import javax.naming.Binding;
import javax.validation.Valid;


@Controller
public class NewswriterController {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsValidatorService newsValidatorService;

    @Autowired
    private NewsWriterRepository newsWriterRepository;


    @GetMapping("/registration")
    public String getRegistration(@ModelAttribute Newswriter newswriter) {
        if (!newsWriterRepository.findAll().isEmpty()) {
            return "redirect:/";
        }
        return "registration";
    }

    @PostMapping("/registration")
    public String addRegistration(@Valid @ModelAttribute Newswriter newswriter, BindingResult bindingResult, Model model) {

        if (!newsWriterRepository.findAll().isEmpty()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", newsValidatorService.getErrorMessages(bindingResult));
            return "registration";
        }
        userService.createUser(newswriter.getName(), newswriter.getPassword());
        return "redirect:/";
    }


}
