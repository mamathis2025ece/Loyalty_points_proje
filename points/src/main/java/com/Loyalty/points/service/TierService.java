package com.Loyalty.points.service;

import com.Loyalty.points.entity.Tier;
import com.Loyalty.points.repository.TierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TierService
{
    private final TierRepository tierRepository;

    public TierService(TierRepository tierRepository)
    {
        this.tierRepository = tierRepository;
    }

    public Tier saveTier(Tier tier)
    {
        return tierRepository.save(tier);
    }

    public List<Tier> getAllTiers()
    {
        return tierRepository.findAll();
    }

    public Tier getTierById(Long id)
    {
        return tierRepository.findById(id).orElse(null);
    }

    public void deleteTier(Long id)
    {
        tierRepository.deleteById(id);
    }
}