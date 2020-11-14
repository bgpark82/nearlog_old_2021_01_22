package com.nearlog.place.domain.place;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlaceDocumentRepository extends ElasticsearchRepository<PlaceDocument, String> {
}
