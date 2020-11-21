package com.nearlog.place.domain.photo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;

public class PhotoDto {

    @ToString
    @Getter
    @JsonDeserialize(using = PlaceDtoSaveDeserializer.class)
    public static class Save {

        private Double timestamp;

        private Type type;

        private String groupName;

        private Double altitude;
        private Double longitude;
        private Double latitude;
        private Double heading;
        private Double speed;

        private Integer width;
        private Integer height;
        private String filename;
        private Integer fileSize;
        private Double playableDuration;
        private String uri;

        @Builder
        public Save(Double timestamp, Type type, String groupName, Double altitude, Double longitude, Double latitude, Double heading, Double speed, Integer width, Integer height, String filename, Integer fileSize, Double playableDuration, String uri) {
            this.timestamp = timestamp;
            this.type = type;
            this.groupName = groupName;
            this.altitude = altitude;
            this.longitude = longitude;
            this.latitude = latitude;
            this.heading = heading;
            this.speed = speed;
            this.width = width;
            this.height = height;
            this.filename = filename;
            this.fileSize = fileSize;
            this.playableDuration = playableDuration;
            this.uri = uri;
        }
    }

    public static enum Type {
        image
    }

    @ToString
    @Getter
    public static class Location {

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

    public static class PlaceDtoSaveDeserializer extends JsonDeserializer<PhotoDto.Save> {

        @Override
        public Save deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {

            ObjectCodec codec = parser.getCodec();
            JsonNode node = codec.readTree(parser);

            return Save.builder()
                    .altitude(node.get("location").get("altitude").asDouble())
                    .longitude(node.get("location").get("longitude").asDouble())
                    .latitude(node.get("location").get("latitude").asDouble())
                    .heading(node.get("location").get("heading").asDouble())
                    .speed(node.get("location").get("speed").asDouble())
                    .timestamp(node.get("timestamp").asDouble())
                    .type(Type.valueOf(node.get("type").asText()))
                    .groupName(node.get("group_name").asText())
                    .width(node.get("image").get("width").asInt())
                    .height(node.get("image").get("height").asInt())
                    .filename(node.get("image").get("filename").asText())
                    .fileSize(node.get("image").get("fileSize").asInt())
                    .playableDuration(node.get("image").get("playableDuration").isNull() ? null : node.get("image").get("playableDuration").asDouble())
                    .uri(node.get("image").get("uri").asText())
                    .build();
        }
    }
}

