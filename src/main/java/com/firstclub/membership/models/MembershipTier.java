package com.firstclub.membership.models;

public enum MembershipTier {
    SILVER(1),
    GOLD(2),
    PLATINUM(3);

    private int level;

    MembershipTier(int level) {
        this.level = level;
    }
}
