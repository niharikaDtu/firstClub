package com.firstclub.membership.service;

import com.firstclub.membership.models.MembershipTier;
import com.firstclub.membership.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TierEvaluationService {

    private final SubscriptionRepository store;

    public MembershipTier evaluateTier(Long userId) {
        SubscriptionRepository.UserStats stats = store.findUserStats(userId)
                .orElse(new SubscriptionRepository.UserStats(userId, 0, BigDecimal.ZERO, false));

        // Tier evaluation rules
        if (stats.specialCohortMember() || stats.monthlyOrderCount() >= 10 || stats.monthlySpend().compareTo(new BigDecimal("50000")) >= 0) {
            return MembershipTier.PLATINUM;
        } else if (stats.monthlyOrderCount() >= 5 || stats.monthlySpend().compareTo(new BigDecimal("20000")) >= 0) {
            return MembershipTier.GOLD;
        }
        return MembershipTier.SILVER;
    }
}
