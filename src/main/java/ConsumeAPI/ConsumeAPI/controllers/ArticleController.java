package ConsumeAPI.ConsumeAPI.controllers;

import ConsumeAPI.ConsumeAPI.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("articleList", articleService.getMostPopular());
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model) {
        String url = new String();
        model.addAttribute("url", url);
        return "search";
    }

    @PostMapping("/search-results")
    public String searchResult(Model model, @RequestParam String url) {
        model.addAttribute(
                "articleList",
                articleService.getSearchResults(url)
        );
        model.addAttribute(
                "searchItem",
                url
        );
        return "search-results";
    }

}
