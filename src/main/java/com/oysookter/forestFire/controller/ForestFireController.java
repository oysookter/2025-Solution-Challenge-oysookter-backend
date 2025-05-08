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
        // FastAPI 주소
        String fastApiUrl = "http://<FASTAPI_IP>:8000/predict";

        // FastAPI에 요청 보낼 구성
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CoordinateRequest> entity = new HttpEntity<>(request, headers);

        // FastAPI로 POST 요청하고 JSON 결과를 PredictionResponse로 매핑
        ResponseEntity<PredictionResponse> response = restTemplate.postForEntity(
                fastApiUrl,
                entity,
                PredictionResponse.class
        );

        // 결과를 그대로 Flutter에 전달
        return ResponseEntity.ok(response.getBody());
    }
}