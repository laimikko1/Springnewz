package uutiset.wepauutiset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

public class post {

    @Autowired
    private PasswordEncoderService passwordEncoderService;

    @Service
    public class psot  {


        @PostConstruct
        public void postConstruct() {
            String moi = passwordEncoderService.encode("zorroispink");

        }

    }
}
