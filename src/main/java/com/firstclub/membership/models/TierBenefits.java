package com.firstclub.membership.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class TierBenefits {
    private MembershipTier tier;
    private boolean freeDelivery;
    private BigDecimal extraDiscountPercentage;
    private boolean exclusiveDealsAccess;
    private boolean earlyAccessToSales;
    private boolean prioritySupport;
}