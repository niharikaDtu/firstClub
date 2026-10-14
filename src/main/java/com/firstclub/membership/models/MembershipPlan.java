package com.firstclub.membership.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class MembershipPlan {

    Long id;
    BillingDuration billingDuration;
    BigDecimal price;
    Integer days;
}
