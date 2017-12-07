package uutiset.wepauutiset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uutiset.wepauutiset.domain.News;
import uutiset.wepauutiset.domain.Newswriter;
import uutiset.wepauutiset.repository.NewsWriterRepository;

import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private NewsWriterRepository newsWriterRepository;

    @Autowired
    private PasswordEncoderService passwordEncoderService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Newswriter account = newsWriterRepository.findByName(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                account.getName(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }

    public Newswriter createUser(String name, String password) {
        Newswriter n = new Newswriter();
        n.setName(name);
        n.setPassword(passwordEncoderService.encode(password));

        return newsWriterRepository.save(n);

    }

}