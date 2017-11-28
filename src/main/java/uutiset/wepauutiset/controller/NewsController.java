package uutiset.wepauutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uutiset.wepauutiset.domain.Category;
import uutiset.wepauutiset.domain.News;
import uutiset.wepauutiset.domain.NewsObject;
import uutiset.wepauutiset.domain.NewsWriter;
import uutiset.wepauutiset.repository.CategoryRepository;
import uutiset.wepauutiset.repository.NewsObjectRepository;
import uutiset.wepauutiset.repository.NewsRepository;
import uutiset.wepauutiset.repository.NewsWriterRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsObjectRepository newsObjectRepository;

    @Autowired
    private NewsWriterRepository newsWriterRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/")
    public String showIndex(Model model) {
        return "index";

    }

    @GetMapping("/addWriterOrCategory")
    public String addWriterOrCategory() {
        return "addWriterOrCategory";
    }

    @PostMapping("/addWriter")
    public String addWriter(@RequestParam String name) {
        NewsWriter w = new NewsWriter();
        w.setName(name);
        newsWriterRepository.save(w);
        return "redirect:/";
    }

    @PostMapping("/addCategory")
    public String addCategory(@RequestParam String category) {
        Category c = new Category();
        c.setName(category);
        c.setPinnedToMenu(false);
        categoryRepository.save(c);

        return "redirect:/";
    }

    @GetMapping("/add")
    public String addNew(Model model) {
        model.addAttribute("writers", newsWriterRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addNews";
    }

    @PostMapping("/addNews")
    public String addNews(@RequestParam("newsObject") MultipartFile newsObject,
                          @RequestParam String header,
                          @RequestParam String ingress,
                          @RequestParam String content,
                          @RequestParam String writers,
                          @RequestParam String categories) throws IOException {

        NewsObject no = new NewsObject();
        no.setContent(newsObject.getBytes());
        News news = new News();

        news.setHeader(header);
        news.setIngress(ingress);
        news.setContent(content);
        news.setNewsObject(no);
        news.setPublishdate(LocalDate.now());


        newsObjectRepository.save(no);


        return "redirect:/";
    }

}
