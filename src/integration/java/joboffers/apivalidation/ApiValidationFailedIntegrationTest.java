package joboffers.apivalidation;

import com.example.joboffers.infrastructure.apivalidation.ApiValidationErrorResponseDto;
import joboffers.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



public class ApiValidationFailedIntegrationTest extends BaseIntegrationTest {

    @Test
    @WithMockUser
    public void should_return_400_bad_request_and_validation_message_when_offer_request_is_empty() throws Exception {

        ResultActions perform = mockMvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""            
                {
                }              
                """.trim()));

        MvcResult resultActions = perform.andExpect(status().isBadRequest()).andReturn();
        String json = resultActions.getResponse().getContentAsString();
        ApiValidationErrorResponseDto errorResponseDto = objectMapper.readValue(json, ApiValidationErrorResponseDto.class);
        assertThat(errorResponseDto.messages()).containsExactlyInAnyOrder(
                "offerUrl must not be null",
                "salary must not be empty",
                "position must not be null",
                "position must not be empty",
                "companyName must not be null",
                "companyName must not be empty",
                "salary must not be null",
                "offerUrl must not be empty"
        );
    }
}
