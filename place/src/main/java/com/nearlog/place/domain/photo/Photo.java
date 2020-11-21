package com.nearlog.place.domain.photo;

import javax.persistence.*;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double timestamp;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String groupName;
    @Embedded
    private Location location;
    @Embedded
    private Images images;

    public static enum Type {
        image,
    }

    @Embeddable
    public static class Location {
        private Double altitude;
        private Double longitude;
        private Double latitude;
        private Double heading;
        private Double speed;
    }

    @Embeddable
    private static class Images {
        private Integer width;
        private Integer height;
        private String filename;
        private Integer fileSize;
        private Double playableDuration;
        private String uri;
    }
}
