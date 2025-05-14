package com.oysookter.forestFire.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.oysookter.forestFire.dto.CoordinateRequest;
import com.oysookter.forestFire.dto.SummaryResponse;
import com.oysookter.forestFire.dto.SummaryResponse.RecoveryInfo;
import com.oysookter.forestFire.dto.SummaryResponse.VegetationInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class SummaryService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String BASE_URL = "https://oysooktergemini-491188530288.asia-northeast3.run.app";

    @Async
    public CompletableFuture<Double> callFastApiForDamage(CoordinateRequest request) {
        try {
            String url = String.format("%s/fire-damage?lat=%s&lon=%s", BASE_URL, request.getLat(), request.getLon());
            log.info("🔥 호출: {}", url);
            ResponseEntity<DamageResponse> response = restTemplate.getForEntity(url, DamageResponse.class);
            double damage = response.getBody() != null ? response.getBody().getDamage() : -1;
            return CompletableFuture.completedFuture(damage);
        } catch (Exception e) {
            log.error("❌ /damage 호출 실패", e);
            return CompletableFuture.completedFuture(-1.0);
        }
    }

    @Async
    public CompletableFuture<RecoveryInfo> callFastApiForRecovery(CoordinateRequest request) {
        try {
            String url = String.format("%s/ndvi-recovery?lat=%s&lon=%s", BASE_URL, request.getLat(), request.getLon());
            log.info("🔥 호출: {}", url);
            ResponseEntity<RecoveryInfo> response = restTemplate.getForEntity(url, RecoveryInfo.class);
            return CompletableFuture.completedFuture(response.getBody());
        } catch (Exception e) {
            log.error("❌ /recovery 호출 실패", e);
            return CompletableFuture.completedFuture(null);
        }
    }

    @Async
    public CompletableFuture<VegetationInfo> callFastApiForVegetation(CoordinateRequest request) {
        try {
            String url = String.format("%s/vegetation?lat=%s&lon=%s", BASE_URL, request.getLat(), request.getLon());
            log.info("🔥 호출: {}", url);
            ResponseEntity<VegetationInfo> response = restTemplate.getForEntity(url, VegetationInfo.class);

            if (response.getBody() != null) {
                log.info("✅ /vegetation 응답: {}", objectMapper.writeValueAsString(response.getBody()));
            } else {
                log.warn("⚠️ /vegetation 응답 body가 null입니다.");
            }

            return CompletableFuture.completedFuture(response.getBody());
        } catch (Exception e) {
            log.error("❌ /vegetation 호출 실패", e);
            return CompletableFuture.completedFuture(null);
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
