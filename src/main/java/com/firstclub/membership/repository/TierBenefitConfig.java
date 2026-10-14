package com.firstclub.membership.repository;

import com.firstclub.membership.models.MembershipTier;
import com.firstclub.membership.models.TierBenefits;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TierBenefitConfig {

    private final Map<MembershipTier, TierBenefits> tierBenefitConfig = new ConcurrentHashMap<>();
    public TierBenefitConfig() {

        tierBenefitConfig.put(MembershipTier.SILVER, TierBenefits.builder()
                .tier(MembershipTier.SILVER)
                .freeDelivery(false)
                .extraDiscountPercentage(BigDecimal.valueOf(2.0))
                .exclusiveDealsAccess(true)
                .earlyAccessToSales(false)
                .prioritySupport(false)
                .build());


        tierBenefitConfig.put(MembershipTier.GOLD, TierBenefits.builder()
                .tier(MembershipTier.GOLD)
                .freeDelivery(true)
                .extraDiscountPercentage(BigDecimal.valueOf(5.0))
                .exclusiveDealsAccess(true)
                .earlyAccessToSales(true)
                .prioritySupport(false)
                .build());


        tierBenefitConfig.put(MembershipTier.PLATINUM, TierBenefits.builder()
                .tier(MembershipTier.PLATINUM)
                .freeDelivery(true)
                .extraDiscountPercentage(BigDecimal.valueOf(15.0))
                .exclusiveDealsAccess(true)
                .earlyAccessToSales(true)
                .prioritySupport(true)
                .build());
    }

    public TierBenefits getBenefits(MembershipTier tier) {
        return tierBenefitConfig.getOrDefault(tier, tierBenefitConfig.get(MembershipTier.SILVER));
    }
}
