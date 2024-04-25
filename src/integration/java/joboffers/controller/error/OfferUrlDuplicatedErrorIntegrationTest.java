package joboffers.controller.error;

import joboffers.BaseIntegrationTest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@Log4j2
public class OfferUrlDuplicatedErrorIntegrationTest extends BaseIntegrationTest {

    @Test
    public void should_throw_duplicated_key_exception_when_post_offers_with_same_url() throws Exception {

        ResultActions perform = mockMvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""            
                                {
                                  "companyName": "string",
                                  "position": "string",
                                  "salary": "string",
                                  "offerUrl": "testURL"
                                }            
                        """.trim()));

        perform.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status",is("CREATED")));
        ResultActions performedPostOfferRequest = mockMvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""            
                                {
                                  "companyName": "string",
                                  "position": "string",
                                  "salary": "string",
                                  "offerUrl": "testURL"
                                }            
                        """.trim()));

        performedPostOfferRequest.andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status",is("CONFLICT")));

    }

}
