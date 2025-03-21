package com.example.finalhotel.dto;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BreandDTO {

    private Long brandNum;

    private String brandTitle;

    private String brandContent;
}
