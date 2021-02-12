package model;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BuddyInfoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BuddyInfoRepository repository;

    @Before
    public void setUp() {
        BuddyInfo buddyInfo = new BuddyInfo("Ralph", "1800-267-2001 ALAAARM FORCE", "Some place in ottawa");
        repository.save(buddyInfo);
    }

    @Test
    public void buddiesIndexShouldReturnBuddies() throws Exception {
        this.mockMvc.perform(get("/buddies")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"_embedded\" : {\n" +
                        "    \"buddies\" : [ {\n" +
                        "      \"name\" : \"Ralph\",\n" +
                        "      \"phoneNumber\" : \"1800-267-2001 ALAAARM FORCE\",\n" +
                        "      \"address\" : \"Some place in ottawa\",\n" +
                        "      \"_links\" : {\n" +
                        "        \"self\" : {\n" +
                        "          \"href\" : \"http://localhost/buddies/1\"\n" +
                        "        },\n" +
                        "        \"buddyInfo\" : {\n" +
                        "          \"href\" : \"http://localhost/buddies/1\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    } ]\n" +
                        "  },\n" +
                        "  \"_links\" : {\n" +
                        "    \"self\" : {\n" +
                        "      \"href\" : \"http://localhost/buddies\"\n" +
                        "    },\n" +
                        "    \"profile\" : {\n" +
                        "      \"href\" : \"http://localhost/profile/buddies\"\n" +
                        "    },\n" +
                        "    \"search\" : {\n" +
                        "      \"href\" : \"http://localhost/buddies/search\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"));
    }
}