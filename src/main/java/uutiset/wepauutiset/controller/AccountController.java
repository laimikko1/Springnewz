package uutiset.wepauutiset.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uutiset.wepauutiset.domain.Account;
import uutiset.wepauutiset.repository.AccountRepository;
import uutiset.wepauutiset.service.ValidatorService;
import uutiset.wepauutiset.service.UserService;

import javax.validation.Valid;


@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidatorService validatorService;

    @Autowired
    private AccountRepository accountRepository;


    @GetMapping("/registration")
    public String getRegistration(@ModelAttribute Account account) {
        if (!accountRepository.findAll().isEmpty()) {
            return "redirect:/";
        }
        return "registration";
    }

    @PostMapping("/registration")
    public String addRegistration(@Valid @ModelAttribute Account account, BindingResult bindingResult, Model model) {

        //Okei tein tämmösen purkkavirityksen ku en keksinyt että miten saan luotua yhden kryptatulla salasanalla
        // varustetun käyttäjä käynnistyksen yhteydessä, niin ettei salasana näy mihinkään, eikä käytössä ole
        // rekisteröintilomaketta. Toki olisi voinut käyttää suoraa esim Postmania, mutta
        // sillo en oikein pysty rajoittaa että onnistuu vain kerran. Siispä tein näin.

        if (!accountRepository.findAll().isEmpty()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", validatorService.getErrorMessages(bindingResult));
            return "registration";
        }
        userService.createUser(account.getName(), account.getPassword());
        return "redirect:/";
    }


}
