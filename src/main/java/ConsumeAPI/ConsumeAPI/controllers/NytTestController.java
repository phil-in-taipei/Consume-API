package ConsumeAPI.ConsumeAPI.controllers;

import ConsumeAPI.ConsumeAPI.models.Article;
import ConsumeAPI.ConsumeAPI.models.Doc;
import ConsumeAPI.ConsumeAPI.models.NytSearchResponse;
import ConsumeAPI.ConsumeAPI.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NytTestController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/nyt/test")
    public List<Article> getMostPopular() {
        return articleService.getMostPopular();
    }

    @GetMapping("/nyt/search/test")
    public List<Doc> getSearchResults() {
        return articleService.getSearchResults("covid");
    }



}
