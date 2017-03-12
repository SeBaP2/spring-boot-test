package pl.seba.springtest.quotes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class QuoteService {

    @Value("${quotes.feeds.random}")
    private String randomQuoteFeed;

    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }

    public List<Quote> randomQuote() {
        return Arrays.asList(restTemplate.getForObject(randomQuoteFeed, Quote[].class));
    }
}
