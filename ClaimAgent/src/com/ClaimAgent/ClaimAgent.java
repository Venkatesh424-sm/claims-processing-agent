package com.ClaimAgent;

import java.util.Map;

public class ClaimAgent {

    public static void main(String[] args) {

        // Simulated extracted data from FNOL document
        ClaimData claim = new ClaimData();
        claim.put("policyNumber", "POL12345");
        claim.put("policyholderName", "John Doe");
        claim.put("date", "2026-02-01");
        claim.put("location", "New York");
        claim.put("description", "Minor accident at traffic signal");
        claim.put("claimType", "vehicle");
        claim.put("estimatedDamage", "12000");

        Map<String, Object> result = ClaimProcessor.process(claim);

        // Print JSON-like output
        System.out.println("{");
        System.out.println("\"extractedFields\": " + result.get("extractedFields") + ",");
        System.out.println("\"missingFields\": " + result.get("missingFields") + ",");
        System.out.println("\"recommendedRoute\": \"" + result.get("recommendedRoute") + "\",");
        System.out.println("\"reasoning\": \"" + result.get("reasoning") + "\"");
        System.out.println("}");
    }
}

