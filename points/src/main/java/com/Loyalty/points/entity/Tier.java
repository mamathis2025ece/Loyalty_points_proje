package com.Loyalty.points.entity;
import jakarta.persistence.*;

@Entity 
@Table (name = "tier")
public class Tier 
{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long TierId;
    private String TierName;
    private Integer threshold;
    private Integer discountPercentage;

    public Tier()
    {
    }
    public Long getTierId()
    {
        return TierId;
    }

    public void setTierId(Long TierId)
    {
        this.TierId = TierId;
    }

    public String getTierName()
    {
        return TierName;
    }

    public void setTierName(String TierName)
    {
        this.TierName = TierName;
    }

    public Integer getThreshold()
    {
        return threshold;
    }

    public void setThreshold(Integer threshold)
    {
        this.threshold = threshold;
    }

    public Integer getDiscountPercentage()
    {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage)
    {
        this.discountPercentage = discountPercentage;
    }
}
