package de.seidinger.frank.examples.spring.events.async.application;

import de.seidinger.frank.examples.spring.events.async.infrastructure.SpringWebTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringWebTest
class MainEndpointTest {
    @Test
    @DisplayName("Reading the main resource returns \"Hello, world!\"")
    void m1(@Autowired final MockMvc mvc) throws Exception {
        mvc
                .perform(
                        get("/")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().contentType(MediaType.parseMediaType("text/plain;charset=UTF-8"))
                )
                .andExpect(
                        content().string("Hello, world!")
                );
    }
}