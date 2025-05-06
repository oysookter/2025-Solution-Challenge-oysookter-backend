package com.oysookter.forestFire.model;

import lombok.Data;

@Data
public class CoordinateRequest {
    private double longitude;
    private double latitude;
    private double radiusKm = 25.0; // 기본값 25km
}