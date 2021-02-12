package model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressBookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressBookRepository repository;

    @Before
    public void setUp() {
        AddressBook addressBook = new AddressBook();
        repository.save(addressBook);
    }

    @Test
    public void booksIndexShouldReturnAddressBooks() throws Exception {
        this.mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"_embedded\" : {\n" +
                        "    \"books\" : [ {\n" +
                        "      \"_links\" : {\n" +
                        "        \"self\" : {\n" +
                        "          \"href\" : \"http://localhost/books/1\"\n" +
                        "        },\n" +
                        "        \"addressBook\" : {\n" +
                        "          \"href\" : \"http://localhost/books/1\"\n" +
                        "        },\n" +
                        "        \"buddies\" : {\n" +
                        "          \"href\" : \"http://localhost/books/1/buddies\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    } ]\n" +
                        "  },\n" +
                        "  \"_links\" : {\n" +
                        "    \"self\" : {\n" +
                        "      \"href\" : \"http://localhost/books\"\n" +
                        "    },\n" +
                        "    \"profile\" : {\n" +
                        "      \"href\" : \"http://localhost/profile/books\"\n" +
                        "    },\n" +
                        "    \"search\" : {\n" +
                        "      \"href\" : \"http://localhost/books/search\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"));


    }
}