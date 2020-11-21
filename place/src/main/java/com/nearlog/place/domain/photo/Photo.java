package com.nearlog.place.domain.photo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
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
    private Image image;

    public static enum Type {
        image,
    }

    @Getter
    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Location {
        private Double altitude;
        private Double longitude;
        private Double latitude;
        private Double heading;
        private Double speed;

        @Builder
        public Location(Double altitude, Double longitude, Double latitude, Double heading, Double speed) {
            this.altitude = altitude;
            this.longitude = longitude;
            this.latitude = latitude;
            this.heading = heading;
            this.speed = speed;
        }
    }

    @Getter
    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    private static class Image {
        private Integer width;
        private Integer height;
        private String filename;
        private Integer fileSize;
        private Double playableDuration;
        private String uri;

        @Builder
        public Image(Integer width, Integer height, String filename, Integer fileSize, Double playableDuration, String uri) {
            this.width = width;
            this.height = height;
            this.filename = filename;
            this.fileSize = fileSize;
            this.playableDuration = playableDuration;
            this.uri = uri;
        }
    }

    @Builder
    public Photo(Double timestamp, Type type, String groupName, Location location, Image image) {
        this.timestamp = timestamp;
        this.type = type;
        this.groupName = groupName;
        this.location = location;
        this.image = image;
    }
}
