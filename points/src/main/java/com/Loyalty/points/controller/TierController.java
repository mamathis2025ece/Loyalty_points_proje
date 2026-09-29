package com.Loyalty.points.controller;

import com.Loyalty.points.entity.Tier;
import com.Loyalty.points.service.TierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiers")
public class TierController
{
    private final TierService tierService;

    public TierController(TierService tierService)
    {
        this.tierService = tierService;
    }

    @PostMapping
    public Tier saveTier(@RequestBody Tier tier)
    {
        return tierService.saveTier(tier);
    }

    @GetMapping
    public List<Tier> getAllTiers()
    {
        return tierService.getAllTiers();
    }

    @GetMapping("/{id}")
    public Tier getTierById(@PathVariable Long id)
    {
        return tierService.getTierById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTier(@PathVariable Long id)
    {
        tierService.deleteTier(id);
    }
}