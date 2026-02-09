package com.ClaimAgent;

import java.util.HashMap;
import java.util.Map;

public class ClaimData {
    Map<String, String> fields = new HashMap<>();

    public void put(String key, String value) {
        fields.put(key, value);
    }

    public String get(String key) {
        return fields.get(key);
    }

    public Map<String, String> getAll() {
        return fields;
    }
}
