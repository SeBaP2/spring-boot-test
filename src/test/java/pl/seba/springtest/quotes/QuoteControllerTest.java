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
                .andExpect(jsonPath("$.rec_id").value("1"))
                .andExpect(jsonPath("$.category").value("category"))
                .andExpect(jsonPath("$.content").value("content"))
                .andExpect(jsonPath("$.author_name").value("authorName"));
    }

    @Test
    public void noParamRandomQuoteWithDefaultResponseShouldReturnEmptyQuote() throws Exception {
        given(quoteServiceMock.randomQuote()).willReturn(Fixtures.DEFAULT_QUOTE);

        this.mockMvc.perform(get("/quotes/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rec_id").value(is(nullValue())))
                .andExpect(jsonPath("$.category").value(is(nullValue())))
                .andExpect(jsonPath("$.content").value(is(nullValue())))
                .andExpect(jsonPath("$.author_name").value(is(nullValue())));
    }

    private static final class Fixtures {
        private static final Quote QUOTE = new Quote(1L, "category", "content", "authorName");
        private static final Quote DEFAULT_QUOTE = new Quote();
    }
}
