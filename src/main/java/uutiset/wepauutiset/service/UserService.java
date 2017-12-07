package uutiset.wepauutiset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uutiset.wepauutiset.domain.Newswriter;
import uutiset.wepauutiset.repository.NewsWriterRepository;

@Service
public class UserService {


    @Autowired
    private NewsWriterRepository newsWriterRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public Newswriter createUser(String name, String password) {
        Newswriter n = new Newswriter();
        n.setName(name);
        n.setPassword(bCryptPasswordEncoder.encode(password));

        return newsWriterRepository.save(n);

    }
}
