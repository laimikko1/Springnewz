package uutiset.wepauutiset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uutiset.wepauutiset.domain.Account;
import uutiset.wepauutiset.repository.AccountRepository;
import uutiset.wepauutiset.repository.NewsWriterRepository;

@Service
public class UserService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoderService bCryptPasswordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public void createUser(String name, String password) {
        Account a = new Account();
        a.setName(name);
        a.setPassword(bCryptPasswordEncoder.encode(password));

         accountRepository.save(a);

         userDetailsService.loadUserByUsername(a.getName());

    }
}
