package ConsumeAPI.ConsumeAPI.services;

import ConsumeAPI.ConsumeAPI.models.*;
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


    public NytSearchResponse getSearchResultsResponse(String url) {
        System.out.println("Starting the api call in getSearchResults service method");
        ResponseEntity<NytSearchResponse> responseEntity =
                restTemplate.getForEntity(
                        "https://api.nytimes.com/svc/search/v2/articlesearch.json?q={url}&api-key=BkJ6qLP6ZPE8meeXpqlFU1Q3uIXRPSy1",
                        NytSearchResponse.class,
                        1
                );
        if (responseEntity.getStatusCode().equals(HttpStatus.OK) && responseEntity.getBody() != null) {
            NytSearchResponse nytSearchResponse = responseEntity.getBody();
            return nytSearchResponse;
        } else {
            System.out.println("Something went wrong! The response was not marked with status code 200");
            System.out.println(responseEntity.getStatusCode());
            return new NytSearchResponse();
        }

    }

    public List<Doc> getSearchResults(String url) {
        System.out.println("Starting the api call in getSearchResults service method: " + url);
        ResponseEntity<NytSearchResponse> responseEntity =
                restTemplate.getForEntity(
                        "https://api.nytimes.com/svc/search/v2/articlesearch.json?q="
                                + url+ "&api-key=BkJ6qLP6ZPE8meeXpqlFU1Q3uIXRPSy1",
                        NytSearchResponse.class,
                        1
                );
        if (responseEntity.getStatusCode().equals(HttpStatus.OK) && responseEntity.getBody() != null) {
            NytSearchResponse nytSearchResponse = responseEntity.getBody();
            List<Doc> docsInResponse = nytSearchResponse.getResponse().getDocs();
            for (Doc doc : docsInResponse) {
                List<Multimedia> media = doc.getMultimedia();
                for (Multimedia m : media) {
                    doc.setImageUrl("https://static01.nyt.com/" + m.getUrl());
                }
            }
            return docsInResponse;
        } else {
            System.out.println("Something went wrong! The response was not marked with status code 200");
            System.out.println(responseEntity.getStatusCode());
            return new NytSearchResponse().getResponse().getDocs();
        }

    }
}
