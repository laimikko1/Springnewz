package uutiset.wepauutiset.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uutiset.wepauutiset.repository.NewsRepository;


import java.util.List;

@Service
public class NewsFinderService {

    private Pageable pageable;

    @Autowired
    private NewsRepository newsRepository;


    public Page findNewest() {
        this.pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "publishdate");
        return newsRepository.findAll(pageable);
    }

    public Page findMostPopular() {
        this.pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "clicks");
        return newsRepository.findAll(pageable);

    }

    public Page findAllByCategoryAndTime() {
        return null;
    }
}
