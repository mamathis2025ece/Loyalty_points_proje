package com.Loyalty.points.controller;

import com.Loyalty.points.entity.PointsLedger;
import com.Loyalty.points.service.PointsLedgerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/points-ledger")
public class PointsLedgerController
{
    private final PointsLedgerService pointsLedgerService;

    public PointsLedgerController(PointsLedgerService pointsLedgerService)
    {
        this.pointsLedgerService = pointsLedgerService;
    }

    @PostMapping
    public PointsLedger savePointsLedger(@RequestBody PointsLedger pointsLedger)
    {
        return pointsLedgerService.savePointsLedger(pointsLedger);
    }

    @GetMapping
    public List<PointsLedger> getAllPointsLedger()
    {
        return pointsLedgerService.getAllPointsLedger();
    }

    @GetMapping("/{id}")
    public PointsLedger getPointsLedgerById(@PathVariable Long id)
    {
        return pointsLedgerService.getPointsLedgerById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePointsLedger(@PathVariable Long id)
    {
        pointsLedgerService.deletePointsLedger(id);
    }

    @PostMapping("/redeem/{customerId}/{points}")
    public PointsLedger redeemPoints(
        @PathVariable Long customerId,
        @PathVariable Integer points)
    {
     return pointsLedgerService.redeemPoints(customerId, points);
    }
}