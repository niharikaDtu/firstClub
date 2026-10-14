package com.firstclub.membership.repository;

import com.firstclub.membership.models.BillingDuration;
import com.firstclub.membership.models.MembershipPlan;
import com.firstclub.membership.models.MembershipTier;
import com.firstclub.membership.models.Subscription;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SubscriptionRepository {
    private final Map<Long, MembershipPlan> plans = new ConcurrentHashMap<>();
    private final Map<Long, Subscription> subscriptions = new ConcurrentHashMap<>();
    private final Map<Long, UserStats> orderStats = new ConcurrentHashMap<>();

    private final AtomicLong membershipIdGen = new AtomicLong(1);
    private final AtomicLong planIdGen = new AtomicLong(1);

    @PostConstruct
    public void init() {
        // Seed default membership plans

        MembershipPlan monthly = MembershipPlan
                .builder()
                .id(planIdGen.getAndIncrement())
                .price(new BigDecimal("100.00"))
                .billingDuration(BillingDuration.Monthly)
                .days(30)
                .build();

        MembershipPlan quaterly = MembershipPlan
                .builder()
                .id(planIdGen.getAndIncrement())
                .price(new BigDecimal("250.00"))
                .billingDuration(BillingDuration.Quarterly)
                .days(90)
                .build();

        MembershipPlan yearly = MembershipPlan
                .builder()
                .id(planIdGen.getAndIncrement())
                .price(new BigDecimal("1000.00"))
                .billingDuration(BillingDuration.Monthly)
                .days(365)
                .build();

        savePlan(monthly);
        savePlan(quaterly);
        savePlan(yearly);

        orderStats.put(101L, new UserStats(101L, 6, new BigDecimal("25000.00"), false));

    }

    public List<MembershipPlan> findAllPlans() {
        return new ArrayList<>(plans.values());
    }

    public Optional<MembershipPlan> findPlanById(Long id) {
        return Optional.ofNullable(plans.get(id));
    }

    public void savePlan(MembershipPlan plan) {
        plans.put(plan.getId(), plan);
    }

    public Optional<Subscription> findMembershipByUserId(Long userId) {
        return subscriptions.values().stream()
                .filter(m -> m.getUserId().equals(userId))
                .findFirst();
    }

    public Subscription saveMembership(Subscription membership) {
        membership.setSubsriptionId(membershipIdGen.getAndIncrement());
        subscriptions.put(membership.getSubsriptionId(), membership);
        return membership;
    }

    public Optional<UserStats> findUserStats(Long userId) {
        return Optional.ofNullable(orderStats.get(userId));
    }

    // Helper Record for Stats
    public record UserStats(Long userId, int monthlyOrderCount, BigDecimal monthlySpend, boolean specialCohortMember) {}
}
