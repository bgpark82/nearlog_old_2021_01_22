package com.nearlog.place.domain.place;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "test", type = "place")
public class PlaceDocument {

    @Id
    private String id;
    private String name;
    private Double lat;
    private Double lng;
    private String image;
}
