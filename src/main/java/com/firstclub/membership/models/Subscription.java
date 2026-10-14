package com.firstclub.membership.models;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Subscription {
    Long subsriptionId;
    Long userId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    MembershipPlan plan;
    MembershipTier tier;

    public Subscription upgrade(MembershipPlan p, LocalDateTime endDate, MembershipTier tier){
        setEndDate(endDate);
        setPlan(p);
        setTier(tier);
        return this;
    }

    void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }

    void setPlan(MembershipPlan plan){
        this.plan = plan;
    }

    void setTier(MembershipTier tier){
        this.tier = tier;
    }

    public void setSubsriptionId(long id){
        this.subsriptionId = id;
    }
}
