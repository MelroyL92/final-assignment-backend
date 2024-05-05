package nl.novi.finalAssignmentBackend.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class MoviesReturnedIntegrationTest {

        @Autowired
        MockMvc mockMvc;


        @Test
        void MoviesReturnedIntegrationTest() throws Exception{

            String requestJson = """
                           {
                           "sellingPrice": 60.0,
                           "originalStock": 1200,
                           "description": "Bilbo and company are forced to engage in a war against an array of combatants and keep the Lonely Mountain from falling into the hands of a rising darkness",
                           "name": "The Hobbit: The Battle of the Five Armies",
                           "currentStock": 700,
                           "amountSold": 500,
                           "yearOfRelease": 2014,
                           "id": 6,
                           "genre": "Fantasy",
                           "type": "blu ray",
                           "director": "Peter Jackson",
                           "watchTimeInMin": 144,
                           "purchasePrice": 15.0
                           }
                    """;

            this.mockMvc
                    .perform(MockMvcRequestBuilders.get("/movies")
                    .contentType(APPLICATION_JSON)
                    .content(requestJson))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


        }
    }

