package com.oysookter.forestFire.controller;


import com.oysookter.forestFire.dto.CoordinateRequest;
import com.oysookter.forestFire.dto.SummaryResponse;
import com.oysookter.forestFire.service.SummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    @PostMapping("/summary")
    public ResponseEntity<SummaryResponse> getSummary(@RequestBody CoordinateRequest request) {
        SummaryResponse.RecoveryInfo recovery = summaryService.callFastApiForRecovery(request);
        double damage = summaryService.callFastApiForDamage(request);
        SummaryResponse.VegetationInfo vegetation = summaryService.callFastApiForVegetation(request);

        SummaryResponse response = new SummaryResponse();
        response.setLat(request.getLat());
        response.setLon(request.getLon());
        response.setRecoveryInfo(recovery);
        response.setDamage(damage);
        response.setVegetationInfo(vegetation);

        return ResponseEntity.ok(response);
    }
}
