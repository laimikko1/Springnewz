package uutiset.wepauutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uutiset.wepauutiset.repository.NewsRepository;

@Controller
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

}
