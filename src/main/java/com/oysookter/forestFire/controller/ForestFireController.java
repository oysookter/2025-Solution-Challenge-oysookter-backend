package com.oysookter.forestFire.controller;


import com.oysookter.forestFire.dto.CoordinateRequest;
import com.oysookter.forestFire.dto.PredictionResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class ForestFireController {

    @PostMapping("/coordinate")
    public ResponseEntity<PredictionResponse> receiveCoordinate(@RequestBody CoordinateRequest request) {
        // 받은 좌표
        double latitude = request.getLatitude();
        double longitude = request.getLongitude();

        // FastAPI 주소: 쿼리 파라미터로 lat, lon 포함
        String fastApiUrl = String.format("http://172.30.1.87:8000/recovery-summary?lat=%s&lon=%s", latitude, longitude);

        // FastAPI에 GET 요청 (쿼리로 좌표 전달)
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PredictionResponse> response = restTemplate.getForEntity(fastApiUrl, PredictionResponse.class);

        // 결과를 Flutter에 전달
        return ResponseEntity.ok(response.getBody());
    }
}
