package uutiset.wepauutiset.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//paketoin tuon BCrpytencoderin omaan luokkaansa, kun sitä ei jostain syystä saanut @autowired annotaatiolla merkattua,
// Heroku sano riks raks ja poks, herjaten ettei oliota ole omassa
@Service
public class PasswordEncoderService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordEncoderService() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }


    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

}
