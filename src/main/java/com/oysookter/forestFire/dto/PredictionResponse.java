package com.oysookter.forestFire.dto;

import lombok.Data;

@Data
public class PredictionResponse {
    private double lat;
    private double lon;
    private String summary;
}
