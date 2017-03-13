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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
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
    public void shouldReturnQuote() throws RestClientException {
        when(restTemplateMock.getForObject(anyString(), eq(Quote[].class))).thenReturn(Fixtures.QUOTES_ARRAY_1_ITEM);

        Quote quote = quoteService.randomQuote();

        assertThat(quote, is(Fixtures.QUOTE_1));
    }

    @Test
    public void shouldReturnFirstQuoteWhenMoreThanOneIsFound() throws RestClientException {
        when(restTemplateMock.getForObject(anyString(), eq(Quote[].class))).thenReturn(Fixtures.QUOTES_ARRAY_2_ITEMS);

        Quote quote = quoteService.randomQuote();

        assertThat(quote, is(Fixtures.QUOTE_1));
    }

    @Test
    public void shouldReturnEmptyQuoteWhenNoQuotesFound() throws RestClientException {
        when(restTemplateMock.getForObject(anyString(), eq(Quote[].class))).thenReturn(Fixtures.QUOTES_ARRAY_EMPTY);

        Quote quote = quoteService.randomQuote();

        assertThat(quote, is(Fixtures.QUOTE_0));
    }

    @Test
    public void shouldReturnEmptyQuoteWhenNullQuoteFound() throws RestClientException {
        when(restTemplateMock.getForObject(anyString(), eq(Quote[].class))).thenReturn(Fixtures.QUOTES_ARRAY_NULL_ITEM);

        Quote quote = quoteService.randomQuote();

        assertThat(quote, is(Fixtures.QUOTE_0));
    }

    @Test
    public void shouldReturnEmptyQuoteRestClientReturnsNull() throws RestClientException {
        when(restTemplateMock.getForObject(anyString(), eq(Quote[].class))).thenReturn(null);

        Quote quote = quoteService.randomQuote();

        assertThat(quote, is(Fixtures.QUOTE_0));
    }

    private static final class Fixtures {
        private static final Quote QUOTE_0 = new Quote();
        private static final Quote QUOTE_1 = new Quote(1L, "title1", "content1", "link1");
        private static final Quote QUOTE_2 = new Quote(2L, "title2", "content2", "link2");
        private static final Quote[] QUOTES_ARRAY_EMPTY = {};
        private static final Quote[] QUOTES_ARRAY_NULL_ITEM = { null };
        private static final Quote[] QUOTES_ARRAY_1_ITEM = { QUOTE_1 };
        private static final Quote[] QUOTES_ARRAY_2_ITEMS = { QUOTE_1, QUOTE_2 };
    }

}