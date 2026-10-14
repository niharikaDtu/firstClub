package com.firstclub.membership.service;

import com.firstclub.membership.exceptions.PlanNotFoundException;
import com.firstclub.membership.exceptions.SubscriptionNotFoundException;
import com.firstclub.membership.models.MembershipPlan;
import com.firstclub.membership.models.MembershipTier;
import com.firstclub.membership.models.Subscription;
import com.firstclub.membership.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final SubscriptionRepository store;
    private final TierEvaluationService evaluationService;

    public List<MembershipPlan> getAllPlans(){
        return store.findAllPlans();
    }

    public Subscription getMembershipDetails(Long userId) {
        return store.findMembershipByUserId(userId)
                .orElseThrow(() -> new SubscriptionNotFoundException( userId));
    }

    public Subscription subscribe(Long userId, Long planId) {
        MembershipPlan plan = store.findPlanById(planId)
                .orElseThrow(() -> new PlanNotFoundException(planId));

        MembershipTier evaluatedTier = evaluationService.evaluateTier(userId);

        Subscription membership = Subscription
                .builder()
                .userId(userId)
                .plan(plan)
                .tier(evaluatedTier)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(plan.getDays()))
                .build();

        return store.saveMembership(membership);
    }

    public Subscription modifySubscription(Long userId, Long newPlanId) {

        Subscription membership = getMembershipDetails(userId);
        MembershipPlan newPlan = store.findPlanById(newPlanId)
                .orElseThrow(() ->  new PlanNotFoundException(newPlanId));

        MembershipTier evaluatedTier = evaluationService.evaluateTier(userId);

        membership.upgrade(newPlan, LocalDateTime.now().plusDays(newPlan.getDays()), evaluatedTier);

        return store.saveMembership(membership);
    }

}
