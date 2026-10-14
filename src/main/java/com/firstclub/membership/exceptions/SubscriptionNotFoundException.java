package com.firstclub.membership.exceptions;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(Long userId) {
        super("No active subscription found for: " + userId);
    }
}
