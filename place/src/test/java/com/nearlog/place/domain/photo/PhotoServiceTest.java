package com.nearlog.place.domain.photo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class PhotoServiceTest {

    private PhotoService photoService;

    @Mock
    private PhotoRepository photoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        photoService = new PhotoService(photoRepository);
    }

    @Test
    void save_테스트() {
        // given
        Photo photo = Photo.builder()
                .timestamp(1234.0)
                .type(Photo.Type.image)
                .build();

        // when
        Long id = photoService.save(photo);

        // then
        verify(photoRepository).save(any());
    }
}