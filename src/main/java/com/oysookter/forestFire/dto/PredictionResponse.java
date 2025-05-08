package com.oysookter.forestFire.dto;

import lombok.Data;

@Data
public class PredictionResponse {
    private double latitude;
    private double longitude;
    private String gemini_result;
}
