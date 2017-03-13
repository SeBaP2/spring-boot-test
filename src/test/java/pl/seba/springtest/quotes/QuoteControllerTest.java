package pl.seba.springtest.quotes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QuoteControllerTest {

    @MockBean
    private QuoteService quoteServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamRandomQuoteShouldReturnQuote() throws Exception {
        given(quoteServiceMock.randomQuote()).willReturn(Fixtures.QUOTE);

        this.mockMvc.perform(get("/quotes/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ID").value("1"))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.content").value("content"))
                .andExpect(jsonPath("$.link").value("link"));
    }

    @Test
    public void noParamRandomQuoteWithDefaultResponseShouldReturnEmptyQuote() throws Exception {
        given(quoteServiceMock.randomQuote()).willReturn(Fixtures.DEFAULT_QUOTE);

        this.mockMvc.perform(get("/quotes/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(is(nullValue())))
                .andExpect(jsonPath("$.link").value(is(nullValue())))
                .andExpect(jsonPath("$.content").value(is(nullValue())))
                .andExpect(jsonPath("$.ID").value(is(nullValue())));
    }

    private static final class Fixtures {
        private static final Quote QUOTE = new Quote(1L, "title", "content", "link");
        private static final pl.seba.springtest.quotes.Quote DEFAULT_QUOTE = new Quote();
    }
}
