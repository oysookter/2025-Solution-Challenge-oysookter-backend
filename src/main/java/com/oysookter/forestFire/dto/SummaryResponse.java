package com.oysookter.forestFire.dto;

import lombok.Data;

@Data
public class SummaryResponse {
    private double lat;
    private double lon;
    private double damage;
    private RecoveryInfo recovery;
    private VegetationInfo vegetation;

    @Data
    public static class RecoveryInfo {
        private double ndvi_pre;
        private double ndvi_min;
        private double ndvi_now;
        private double recovery_rate;
        private String status;
    }

    @Data
    public static class VegetationInfo {
        private double ndvi;
        private String explanation;
    }
}

