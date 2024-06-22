package com.ashbyp.scratch.bbg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Overrides2 {

    static class BloombergSecurity
    {
        String bloombergTicker;
        public BloombergSecurity(String id) { this.bloombergTicker = id; }
    }

    static class BloombergAttribute
    {
        BloombergSecurity security;
        String bloombergField;
        String fieldValue;

        public BloombergAttribute(BloombergSecurity t, String a, String b) { this.security = t; this.bloombergField = a; this.fieldValue = b;}

        public String toString() {
            return String.format("bloombergTicker=%s, a=%s, b=%s", security.bloombergTicker, bloombergField, fieldValue);
        }
    }

    record BloombergFieldOverride(String bloombergTicker, String bloombergField, String fieldValue) {}
    static List<BloombergFieldOverride> OVERRIDES = List.of(
            new BloombergFieldOverride("3", "first", "3-first-override"),
            new BloombergFieldOverride("7", "second", "7-second-override"),
            new BloombergFieldOverride("1", "first", "1-first-override"),
            new BloombergFieldOverride("1", "second", "1-second-override")
            );

    static List<BloombergAttribute> generate()
    {
        List<BloombergAttribute> atts = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            var t = new BloombergSecurity(String.valueOf(i));
            atts.add(new BloombergAttribute(t, "first", String.valueOf(i)));
            atts.add(new BloombergAttribute(t, "second", String.valueOf(i+1)));
        }
        return atts;
    }

    public static void applyOverrides(List<BloombergAttribute> attributes, List<BloombergFieldOverride> overrides) {
        // Create a map of attributes for quick lookup
        Map<String, Map<String, BloombergAttribute>> attributeMap = new HashMap<>();
        for (BloombergAttribute attribute : attributes) {
            attributeMap.computeIfAbsent(attribute.security.bloombergTicker, k -> new HashMap<>())
                    .put(attribute.bloombergField, attribute);
        }

        // Apply overrides using the map
        for (BloombergFieldOverride override : overrides) {
            Map<String, BloombergAttribute> fieldMap = attributeMap.get(override.bloombergTicker);
            if (fieldMap != null) {
                BloombergAttribute attribute = fieldMap.get(override.bloombergField);
                if (attribute != null) {
                    attribute.fieldValue = override.fieldValue;
                }
            }
        }
    }

    public static void main(String[] args) {
        List<BloombergAttribute> atts = generate();
        atts.forEach(System.out::println);
        applyOverrides(atts, OVERRIDES);
        atts.forEach(System.out::println);
    }
}
