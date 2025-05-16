package com.oysookter.forestFire.controller;


import com.oysookter.forestFire.dto.CoordinateRequest;
import com.oysookter.forestFire.dto.SummaryResponse;
import com.oysookter.forestFire.service.SummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    @PostMapping("/summary")
    public ResponseEntity<SummaryResponse> getSummary(@RequestBody CoordinateRequest request)
            throws ExecutionException, InterruptedException {

        CompletableFuture<SummaryResponse.RecoveryInfo> recoveryFuture = summaryService.callFastApiForRecovery(request);
        CompletableFuture<Double> damageFuture = summaryService.callFastApiForDamage(request);
        CompletableFuture<SummaryResponse.VegetationInfo> vegetationFuture = summaryService.callFastApiForVegetation(request);


        CompletableFuture.allOf(recoveryFuture, damageFuture, vegetationFuture).join();

        SummaryResponse response = new SummaryResponse();
        response.setLat(request.getLat());
        response.setLon(request.getLon());
        response.setRecoveryInfo(recoveryFuture.get());
        response.setDamage(damageFuture.get());
        response.setVegetationInfo(vegetationFuture.get());

        return ResponseEntity.ok(response);
    }
}
