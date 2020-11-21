package com.nearlog.place.domain.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PhotoRepository {

    private final EntityManager em;

    public Long save(Photo photo) {
        em.persist(photo);
        return photo.getId();
    }
}
