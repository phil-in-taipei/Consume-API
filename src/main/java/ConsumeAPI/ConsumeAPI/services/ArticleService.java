package ConsumeAPI.ConsumeAPI.services;

import ConsumeAPI.ConsumeAPI.models.Article;
import ConsumeAPI.ConsumeAPI.models.Media;
import ConsumeAPI.ConsumeAPI.models.NytResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Value("${api_key}")
    private String apikey;

    @Value("${mostPopularUrl}")
    private String mostPopularUrl;

    @Autowired
    RestTemplate restTemplate;

    public List<Article> getMostPopular() {
        NytResponse response = restTemplate.getForObject(
                mostPopularUrl
                + "api-key=" + apikey,
                NytResponse.class
        );
        List<Article> results = new ArrayList<>();
        if (response != null && response.getStatus().equals("OK")) {
            results = response.getResults();
            for (Article result : results) {
                List<Media> media = result.getMedia();
                for (Media m : media) {
                    result.setImageUrl(m.getMediaMetadata().get(0).getUrl());
                }
            }
        }
        return results;
    }
}
