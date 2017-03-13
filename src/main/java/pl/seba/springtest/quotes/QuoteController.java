package pl.seba.springtest.quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @RequestMapping("/quotes/random")
    public Quote randomQuote() {
        return quoteService.randomQuote();
    }
}
