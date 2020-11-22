package com.nearlog.place.domain.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    @Transactional
    public Long save(PhotoDto.Save photo) {
        return photoRepository.save(photo.toEntity());
    }
}
