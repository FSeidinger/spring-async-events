/*
 * Copyright 2018 Frank Seidinger
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package de.seidinger.frank.examples.spring.events.async.application;

import de.seidinger.frank.examples.spring.SpringWebTest;
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