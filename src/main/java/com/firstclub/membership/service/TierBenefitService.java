package com.firstclub.membership.service;

import com.firstclub.membership.models.MembershipTier;
import com.firstclub.membership.models.TierBenefits;
import com.firstclub.membership.repository.TierBenefitConfig;
import org.springframework.stereotype.Service;

@Service
public class TierBenefitService {

    private final TierBenefitConfig tierBenefitConfig = new TierBenefitConfig();

    public TierBenefits getBenefitsForTier(String tierString) {
        MembershipTier tier;
        try {
            tier = MembershipTier.valueOf(tierString);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid tier name: " + tierString);
        }
        return tierBenefitConfig.getBenefits(tier);
    }
}
