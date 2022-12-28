package ConsumeAPI.ConsumeAPI.services;

import ConsumeAPI.ConsumeAPI.models.Article;
import ConsumeAPI.ConsumeAPI.models.Media;
import ConsumeAPI.ConsumeAPI.models.NytResponse;
import ConsumeAPI.ConsumeAPI.models.NytSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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


    public void getSearchResults(String url) {
        System.out.println("Starting the api call in getSearchResults service method");
        ResponseEntity<NytSearchResponse> responseEntity =
                restTemplate.getForEntity(
                        "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=covid&api-key=BkJ6qLP6ZPE8meeXpqlFU1Q3uIXRPSy1",
                        //"https://www.boredapi.com/api/activity?participants={participants}",
                        NytSearchResponse.class,
                        1
                );
        if (responseEntity.getStatusCode().equals(HttpStatus.OK) && responseEntity.getBody() != null) {
            System.out.println("The api request has gone through");

            NytSearchResponse nytSearchResponse = responseEntity.getBody();
            System.out.println("This is the nytSearchResponse");
            System.out.println(nytSearchResponse.toString());
        } else {
            System.out.println("Something went wrong! The response was not marked with status code 200");
        }
    }
}
