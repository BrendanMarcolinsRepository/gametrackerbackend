package com.api.gamerating.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
public class GameDto {
    private String title;
    private String publisher;
    private String description;
    private Timestamp releaseDate;

    public GameDto() {
    }

    public GameDto(String title, String publisher, String description, Timestamp releaseDate) {
        this.title = title;
        this.publisher = publisher;
        this.description = description;
        this.releaseDate = releaseDate;
    }
}
