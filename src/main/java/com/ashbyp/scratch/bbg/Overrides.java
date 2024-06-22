package com.ashbyp.scratch.bbg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Overrides {

    static class BloombergSecurity
    {
        String bloombergTicker;
        public BloombergSecurity(String id) { this.bloombergTicker = id; }
    }

    static class BloombergAttribute
    {
        BloombergSecurity thing;
        String bloombergField;
        String fieldValue;

        public BloombergAttribute(BloombergSecurity t, String a, String b) { this.thing = t; this.bloombergField = a; this.fieldValue = b;}

        public String toString() {
            return String.format("bloombergTicker=%s, a=%s, b=%s", thing.bloombergTicker, bloombergField, fieldValue);
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
        Map<String, Map<String, String>> overrideMap = overrides.stream()
                .collect(Collectors.groupingBy(
                        BloombergFieldOverride::bloombergTicker,
                        Collectors.toMap(
                                BloombergFieldOverride::bloombergField,
                                BloombergFieldOverride::fieldValue
                        )
                ));

        for (BloombergAttribute attribute : attributes) {
            Map<String, String> fieldsMap = overrideMap.get(attribute.thing.bloombergTicker);
            if (fieldsMap != null) {
                String newValue = fieldsMap.get(attribute.bloombergField);
                if (newValue != null) {
                    attribute.fieldValue = newValue;
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
