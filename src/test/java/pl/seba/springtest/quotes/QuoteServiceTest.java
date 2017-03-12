package pl.seba.springtest.quotes;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuoteServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private RestTemplate restTemplateMock;

    @InjectMocks
    private QuoteService quoteService;

    @Test
    public void shouldThrowExceptionWhenNoConnection() throws RestClientException {
        when(restTemplateMock.getForObject(anyString(), eq(Quote[].class))).thenThrow(RestClientException.class);
        expectedException.expect(RestClientException.class);

        quoteService.randomQuote();
    }

    @Test
    public void shouldReturnQuote() throws Exception {
        when(restTemplateMock.getForObject(anyString(), eq(Quote[].class))).thenReturn(Fixtures.QUOTES_ARRAY);

        List<Quote> quotes = quoteService.randomQuote();

        assertThat(quotes, hasSize(Fixtures.QUOTES_ARRAY.length));
        assertThat(quotes, hasItem(Fixtures.QUOTE));
    }

    private static final class Fixtures {
        private static final Quote QUOTE = new Quote(1L, "title", "content", "link");
        private static final Quote[] QUOTES_ARRAY = { QUOTE };
    }

}