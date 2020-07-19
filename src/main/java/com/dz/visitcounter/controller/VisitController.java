package com.dz.visitcounter.controller;

import com.dz.visitcounter.service.VisitService;
import com.dz.visitcounter.view.PeriodStats;
import com.dz.visitcounter.view.PeriodView;
import com.dz.visitcounter.view.SuccessView;
import com.dz.visitcounter.view.VisitView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/visit")

public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController (VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public @ResponseBody
    SuccessView addNewVisit(@RequestBody VisitView visit, HttpServletRequest request) {
        String ip=request.getRemoteAddr();
        return visitService.add(visit,ip);
    }

    @PostMapping(value = "/stats", consumes = "application/json")
    public @ResponseBody
    PeriodStats getStatsForPeriod(@RequestBody PeriodView period) {
       return visitService.getStatsForPeriod(period);
    }


}
