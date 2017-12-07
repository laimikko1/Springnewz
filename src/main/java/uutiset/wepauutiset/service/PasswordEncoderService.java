package uutiset.wepauutiset.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
