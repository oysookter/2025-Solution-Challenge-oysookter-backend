package com.oysookter.forestFire.dto;

import lombok.Data;

@Data
public class CoordinateRequest {
    private double longitude;
    private double latitude;
    private double radiusKm = 10.0; // 기본값 10km
}