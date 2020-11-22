package com.nearlog.place.domain.photo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhotoController.class)
class PhotoControllerTest {

    @MockBean
    private PhotoService photoService;

    @Autowired
    private MockMvc mvc;

    @Test
    void save_테스트() throws Exception {
        mvc.perform(post("/api/v1/photo")
            .content(mockPhoto)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated());
    }

    private static String mockPhoto = "{" +
            "\"timestamp\": 1605702437.7242212, " +
            "\"type\": \"image\", " +
            "\"group_name\": \"All Photos\", " +
            "\"location\": { " +
            "\"altitude\": 34.2, " +
            "\"longitude\": 127.02040833000001, " +
            "\"latitude\": 37.50705000000001, " +
            "\"heading\": 216.75024414," +
            "\"speed\": 0 " +
            "}," +
            "\"image\": { " +
            "\"width\": 4032," +
            "\"height\": 3024," +
            "\"filename\": \"IMG_6374.JPG\"," +
            "\"fileSize\": 2111944," +
            "\"playableDuration\": null," +
            "\"uri\": \"ph://E39298AA-E106-4755-9222-942A7C317BB7/L0/001\"" +
            "}" +
            "}";
}

