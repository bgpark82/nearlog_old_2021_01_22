package com.nearlog.place.domain.photo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.json.JacksonTester;

import javax.persistence.EntityManager;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PhotoRepositoryTest {

    @Autowired
    private EntityManager em;

    private JacksonTester<PhotoDto.Save> jackson;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void photoDto_Save_deserialize_테스트() throws IOException {
        PhotoDto.Save photo = jackson.parseObject(mockPhoto);

        assertThat(photo.getTimestamp()).isEqualTo(1605702437.7242212);
        assertThat(photo.getGroupName()).isEqualTo("All Photos");
        assertThat(photo.getType()).isEqualTo("image");
        assertThat(photo.getAltitude()).isEqualTo(34.2);
        assertThat(photo.getHeading()).isEqualTo(216.75024414);
        assertThat(photo.getLongitude()).isEqualTo(127.02040833000001);
        assertThat(photo.getLatitude()).isEqualTo(37.50705000000001);
        assertThat(photo.getSpeed()).isEqualTo(0);
        assertThat(photo.getHeight()).isEqualTo(3024);
        assertThat(photo.getWidth()).isEqualTo(4032);
        assertThat(photo.getFilename()).isEqualTo("IMG_6374.JPG");
        assertThat(photo.getFileSize()).isEqualTo(2111944);
        assertThat(photo.getPlayableDuration()).isEqualTo(null);
    }

    @Test
    void save_테스트() throws IOException {
        // given
        PhotoDto.Save request = jackson.parseObject(mockPhoto);
        Photo photo = request.toEntity();

        // when
        em.persist(photo);

        // then
        assertThat(photo.getTimestamp()).isEqualTo(1605702437.7242212);
        assertThat(photo.getGroupName()).isEqualTo("All Photos");
        assertThat(photo.getType()).isEqualTo(Photo.Type.image);
        assertThat(photo.getLocation().getAltitude()).isEqualTo(34.2);
        assertThat(photo.getLocation().getHeading()).isEqualTo(216.75024414);
        assertThat(photo.getLocation().getLongitude()).isEqualTo(127.02040833000001);
        assertThat(photo.getLocation().getLatitude()).isEqualTo(37.50705000000001);
        assertThat(photo.getLocation().getSpeed()).isEqualTo(0);
        assertThat(photo.getImage().getHeight()).isEqualTo(3024);
        assertThat(photo.getImage().getWidth()).isEqualTo(4032);
        assertThat(photo.getImage().getFilename()).isEqualTo("IMG_6374.JPG");
        assertThat(photo.getImage().getFileSize()).isEqualTo(2111944);
        assertThat(photo.getImage().getPlayableDuration()).isEqualTo(null);
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
