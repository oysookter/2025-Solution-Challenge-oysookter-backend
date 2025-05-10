package com.oysookter.forestFire.controller;


import com.oysookter.forestFire.dto.CoordinateRequest;
import com.oysookter.forestFire.dto.PredictionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/api")
public class ForestFireController {

    @PostMapping("/coordinateit pull")
    public ResponseEntity<PredictionResponse> receiveCoordinate(@RequestBody CoordinateRequest request) {
        double latitude = request.getLatitude();
        double longitude = request.getLongitude();

        // 요청 시작 로그
        log.info("📍 좌표 요청 수신: lat={}, lon={}", latitude, longitude);

        // URL 확인
        String fastApiUrl = String.format("http://172.30.1.87:8000/recovery-summary?lat=%s&lon=%s", latitude, longitude);
        log.info("🌐 FastAPI 요청 URL: {}", fastApiUrl);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<PredictionResponse> response = restTemplate.getForEntity(fastApiUrl, PredictionResponse.class);

            // 응답 결과 확인
            PredictionResponse responseBody = response.getBody();
            log.info("✅ FastAPI 응답 수신: {}", responseBody);

            return ResponseEntity.ok(responseBody);

        } catch (Exception e) {
            // 예외 발생 시 로그
            log.error("❌ FastAPI 요청 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
