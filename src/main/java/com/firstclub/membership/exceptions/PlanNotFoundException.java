package com.firstclub.membership.exceptions;
public class PlanNotFoundException extends RuntimeException {
    public PlanNotFoundException(Long planId) {
        super("Membership plan not found with ID: " + planId);
    }
}