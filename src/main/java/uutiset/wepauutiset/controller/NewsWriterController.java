package uutiset.wepauutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uutiset.wepauutiset.domain.NewsWriter;
import uutiset.wepauutiset.repository.NewsWriterRepository;

public class NewsWriterController {


    @Autowired
    private NewsWriterRepository newsWriterRepository;


    @PostMapping("/addCategory")
    public String addCategory(@RequestParam String name) {
        NewsWriter nw = new NewsWriter();
        nw.setName(name);
        this.newsWriterRepository.save(nw);
        return "redirect:/";
    }

}
