package com.oysookter.forestFire.service;


import com.oysookter.forestFire.dto.CoordinateRequest;
import com.oysookter.forestFire.dto.SummaryResponse;
import com.oysookter.forestFire.dto.SummaryResponse.RecoveryInfo;
import com.oysookter.forestFire.dto.SummaryResponse.VegetationInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class SummaryService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String BASE_URL = "http://172.30.1.87:8000";

    public double callFastApiForDamage(CoordinateRequest request) {
        try {
            String url = String.format("%s/fire-damage?lat=%s&lon=%s", BASE_URL, request.getLat(), request.getLon());
            log.info("🔥 호출: {}", url);
            ResponseEntity<DamageResponse> response = restTemplate.getForEntity(url, DamageResponse.class);
            return response.getBody() != null ? response.getBody().getDamage() : -1;
        } catch (Exception e) {
            log.error("❌ /damage 호출 실패", e);
            return -1;
        }
    }

    public RecoveryInfo callFastApiForRecovery(CoordinateRequest request) {
        try {
            String url = String.format("%s/ndvi-recovery?lat=%s&lon=%s", BASE_URL, request.getLat(), request.getLon());
            log.info("🔥 호출: {}", url);
            ResponseEntity<RecoveryInfo> response = restTemplate.getForEntity(url, RecoveryInfo.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("❌ /recovery 호출 실패", e);
            return null;
        }
    }

    public VegetationInfo callFastApiForVegetation(CoordinateRequest request) {
        try {
            String url = String.format("%s/vegetation?lat=%s&lon=%s", BASE_URL, request.getLat(), request.getLon());
            log.info("🔥 호출: {}", url);
            ResponseEntity<VegetationInfo> response = restTemplate.getForEntity(url, VegetationInfo.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("❌ /vegetation 호출 실패", e);
            return null;
        }
    }

    // 내부 클래스: FastAPI의 /damage 응답을 받기 위한 DTO
    private static class DamageResponse {
        private double lat;
        private double lon;
        private double damage;

        public double getDamage() {
            return damage;
        }

        public void setDamage(double damage) {
            this.damage = damage;
        }
    }
}
