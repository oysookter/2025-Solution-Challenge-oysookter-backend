package com.oysookter.forestFire.dto;

import lombok.Data;

@Data
public class SummaryResponse {
    private double lat;
    private double lon;
    private double damage;
    private RecoveryInfo recoveryInfo;
    private VegetationInfo vegetationInfo;

    @Data
    public static class RecoveryInfo {
        private Recovery recovery;

        @Data
        public static class Recovery {
            private double ndvi_pre;
            private double ndvi_min;
            private double ndvi_now;
            private double recovery_rate;
            private String status;
        }

    }

    @Data
    public static class VegetationInfo {
        private double ndvi;
        private Vegetation vegetation;

        @Data
        public static class Vegetation {
            private String explanation;
            private Plant veg1;
            private Plant veg2;
            private Plant veg3;
        }

        @Data
        public static class Plant {
            private String name;
            private String text;
            private String image;
        }
    }
}

