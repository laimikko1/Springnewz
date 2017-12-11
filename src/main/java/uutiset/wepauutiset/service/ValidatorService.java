package uutiset.wepauutiset.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import uutiset.wepauutiset.domain.News;

import java.util.ArrayList;
import java.util.List;
//En keksinyt miten saan nätisit BindingErrorit tekstimuotoon, joten tein sille tällaisen yhden metodin palvelun
@Service
public class ValidatorService {

    public List<String> getErrorMessages(BindingResult bindingResult) {
        List<String> e = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            e.add(error.getDefaultMessage());
        }
        return e;
    }


}
