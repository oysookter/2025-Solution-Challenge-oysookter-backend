package com.oysookter.forestFire.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/forestfire")
public class ForestFireController {


    @GetMapping("/coordinates")
    public ResponseEntity<Map<String, Object>> receiveCoordinatesGet(
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam(required = false, defaultValue = "25.0") double radiusKm) {


        // 간단한 응답 맵 생성
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Coordinates received");
        response.put("coordinates", Map.of(
                "longitude", longitude,
                "latitude", latitude,
                "radiusKm", radiusKm
        ));

        return ResponseEntity.ok(response);
    }
}