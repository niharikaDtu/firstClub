package com.firstclub.membership.controller;


import com.firstclub.membership.models.Subscription;
import com.firstclub.membership.models.TierBenefits;
import com.firstclub.membership.service.MembershipService;
import com.firstclub.membership.service.TierBenefitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/membership")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;
    private final TierBenefitService benefitService;

    @GetMapping("/plans")
    public ResponseEntity<?> getAllPlans() {
        try {
            return ResponseEntity.ok(membershipService.getAllPlans());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserMembership(@PathVariable Long userId) {
        try {
            Subscription subscription = membershipService.getMembershipDetails(userId);
            return ResponseEntity.ok(subscription);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestParam Long userId, @RequestParam Long planId) {
        try {
            Subscription subscription = membershipService.subscribe(userId, planId);
            return ResponseEntity.status(HttpStatus.CREATED).body(subscription);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifySubscription(
            @RequestParam Long userId,
            @RequestParam Long newPlanId) {
        try {
            Subscription subscription = membershipService.modifySubscription(userId, newPlanId);
            return ResponseEntity.ok(subscription);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/benefits/{tierString}")
    public ResponseEntity<?> getTierBenefits(@PathVariable String tierString) {
        try {
            TierBenefits benefits = benefitService.getBenefitsForTier(tierString);
            return ResponseEntity.ok(benefits);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
