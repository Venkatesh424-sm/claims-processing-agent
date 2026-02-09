package com.ClaimAgent;

import java.util.*;

public class ClaimProcessor {

    private static final List<String> mandatoryFields = Arrays.asList(
            "policyNumber",
            "policyholderName",
            "date",
            "location",
            "description",
            "claimType",
            "estimatedDamage"
    );

    public static Map<String, Object> process(ClaimData claim) {

        Map<String, Object> result = new HashMap<>();
        List<String> missing = new ArrayList<>();

        // Checking missing fields
        for (String field : mandatoryFields) {
            if (claim.get(field) == null || claim.get(field).isEmpty()) {
                missing.add(field);
            }
        }

        String route = "";
        String reasoning = "";

        // Rule 1: Finding missing fields
        if (!missing.isEmpty()) {
            route = "Manual Review";
            reasoning = "Mandatory fields missing: " + missing;
        } else {
            String description = claim.get("description").toLowerCase();
            double damage = Double.parseDouble(claim.get("estimatedDamage"));
            String claimType = claim.get("claimType").toLowerCase();

            // Rule 2: Find Fraud keywords
            if (description.contains("fraud")
                    || description.contains("staged")
                    || description.contains("inconsistent")) {
                route = "Investigation";
                reasoning = "Suspicious keywords found in description.";
            }
            // Rule 3: Find Injury claims
            else if (claimType.equals("injury")) {
                route = "Specialist Queue";
                reasoning = "Claim type is injury.";
            }
            // Rule 4: Find Fast track
            else if (damage < 25000) {
                route = "Fast-track";
                reasoning = "Damage below 25,000.";
            } else {
                route = "Manual Review";
                reasoning = "High damage amount.";
            }
        }

        result.put("extractedFields", claim.getAll());
        result.put("missingFields", missing);
        result.put("recommendedRoute", route);
        result.put("reasoning", reasoning);

        return result;
    }
}
