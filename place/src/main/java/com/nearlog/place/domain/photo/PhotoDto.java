package com.nearlog.place.domain.photo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

public class PhotoDto {

    @ToString
    @Getter
    public static class Save {

        private Double timestamp;

        private Type type;
        @JsonProperty("group_name")
        private String groupName;

        private Location location;

        private Image image;
    }

    public static enum Type {
        image
    }

    @ToString
    @Getter
    public static class Location {
        private Double altitude;
        private Double longitude;
        private Double latitude;
        private Double heading;
        private Double speed;
    }

    @ToString
    @Getter
    public static class Image {
        private Integer width;
        private Integer height;
        private String filename;
        private Integer fileSize;
        private Double playableDuration;
        private String uri;
    }
}
