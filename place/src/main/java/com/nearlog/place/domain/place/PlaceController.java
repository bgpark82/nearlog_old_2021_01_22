package com.nearlog.place.domain.place;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/places")
public class PlaceController {

    private final PlaceDocumentRepository placeDocumentRepository;

    @PostMapping
    public ResponseEntity<PlaceDocument> save() {
        PlaceDocument place = PlaceDocument.builder()
                .id("1")
                .name("강남")
                .build();
        PlaceDocument save = placeDocumentRepository.save(place);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/all")
    public Iterable<PlaceDocument> findAll() {
        return placeDocumentRepository.findAll();
    }

}
