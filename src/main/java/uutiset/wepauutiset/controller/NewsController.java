package uutiset.wepauutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uutiset.wepauutiset.domain.*;
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

    @Autowired
    private NewsFinderService newsFinderService;




    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("news", newsFinderService.findNewest());
        addAsideListNews(model);
        return "index";

    }

    @GetMapping("/news/{newsId}")
    public String showOne(Model model, @PathVariable Long newsId) throws Throwable {
        model.addAttribute("n", newsRepository.getOne(newsId));
        model.addAttribute("writers", newsWriterRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());

        addAsideListNews(model);

        return "singleNewsPage";
    }


    @GetMapping("/add")
    public String addNew(Model model) {
        addAsideListNews(model);
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
        newsObjectRepository.save(no);


        News news = new News();
        news.setHeader(header);
        news.setIngress(ingress);
        news.setContent(content);

        String[] wr = writers.split(",");

        for (String w : wr) {
            NewsWriter nw = newsWriterRepository.getOne(Long.parseLong((w)));
            news.addNewsWriter(nw);
        }

        String[] c = categories.split(",");

        for (String ct : c) {
            Category nc = categoryRepository.getOne(Long.parseLong((ct)));
            news.addCategory(nc);
        }

        news.setNewsObject(no);
        news.setPublishdate(LocalDate.now());
        newsRepository.save(news);

        return "redirect:/";
    }

    @Transactional
    @GetMapping(path = "/image/{id}/", produces = {"image/png", "image/jpg"})
    @ResponseBody
    public byte[] getContent(@PathVariable Long id) {
        News n = newsRepository.getOne(id);
        return n.getNewsObject().getContent();
    }


    private void addAsideListNews(Model model) {
        model.addAttribute("newest", newsFinderService.findNewest());
        model.addAttribute("mostPopular", newsFinderService.findMostPopular());
    }

}
