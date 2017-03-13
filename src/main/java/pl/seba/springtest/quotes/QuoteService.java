package pl.seba.springtest.quotes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class QuoteService {

    @Value("${quotes.feeds.random}")
    private String randomQuoteFeed;

    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }

    public Quote randomQuote() {
        Optional<Quote[]> quotes = Optional.ofNullable(restTemplate.getForObject(randomQuoteFeed, Quote[].class));
        return firstQuoteOrDefault(quotes);
    }

    private Quote firstQuoteOrDefault(Optional<Quote[]> quotes) {
        return quotes
                .filter(q -> q.length > 0)
                .map(q -> q[0])
                .orElse(new Quote());
    }
}