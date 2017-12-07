package uutiset.wepauutiset.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uutiset.wepauutiset.domain.Newswriter;
import uutiset.wepauutiset.service.UserService;


@Controller
public class NewswriterController {

    @Autowired
    private UserService userService;


    @GetMapping("/registration")
    public String registration() {

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String name, @RequestParam String password) {

        userService.createUser(name, password);

        return "redirect:/";
    }


}
