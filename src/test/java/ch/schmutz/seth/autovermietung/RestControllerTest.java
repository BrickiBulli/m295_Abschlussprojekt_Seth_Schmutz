package ch.schmutz.seth.autovermietung;

import ch.schmutz.seth.autovermietung.fahrzeug.Fahrzeug;
import ch.schmutz.seth.autovermietung.fahrzeug.FahrzeugRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.sql.Delete;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestControllerTest {

    private Fahrzeug fahrzeug;

    @Autowired
    private MockMvc api;

    @Autowired
    private FahrzeugRepository fahrzeugRepository;

    @BeforeAll
     void setup(){
        this.fahrzeug = this.fahrzeugRepository.save(new Fahrzeug("Ford", "Kuga", "BL 123 456", "123456789"));
    }

    @Test
    @Order(1)
    void testGetFahrzeug() throws Exception {

        String accessToken = obtainAccessToken();

        api.perform(get("/api/AlleFahrzeuge").header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("BL 123 456")));
    }

    @Test
    @Order(2)
    void testSaveFahrzeug() throws Exception {

        Fahrzeug fahrzeug = new Fahrzeug();

        fahrzeug.setMarke("Ford");
        fahrzeug.setModel("Kuga");
        fahrzeug.setAutoNr("BL 123 456");
        fahrzeug.setRahmenNr("654321");

        String accessToken = obtainAccessToken();
        String body = new ObjectMapper().writeValueAsString(fahrzeug);

        api.perform(post("/api/InsertFahrzeug")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("654321")));
    }

    @Test
    @Order(3)
    void testEditFahrzeug() throws Exception {

        this.fahrzeug.setMarke("Mazda");
        this.fahrzeug.setModel("Miata");
        this.fahrzeug.setRahmenNr("654456321");

        String accessToken = obtainAccessToken();
        String body = new ObjectMapper().writeValueAsString(this.fahrzeug);

        api.perform(put("/api/FahrzeugUpdaten")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("654456321")));
    }


    @Test
    @Order(4)
    void testDeleteFahrzeug() throws Exception {

        String accessToken = obtainAccessToken();
        String body = new ObjectMapper().writeValueAsString(this.fahrzeug.getId());

        api.perform(delete("/api/DeleteFahrzeug")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("654456321")));
    }

    private String obtainAccessToken() {

        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "client_id=AbsschlussaufgabeClient&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=admin&" +
                "password=2108";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/ILV/protocol/openid-connect/token", entity, String.class);

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
    }
}
