package com.ashbyp.scratch.bbg;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class Overrides1 {

    static class Thing
    {
        String id;
        public Thing(String id) { this.id = id; }
    }

    static class Attr
    {
        Thing thing;
        String a;
        String b;

        public Attr(Thing t, String a, String b) { this.thing = t; this.a = a; this.b = b;}

        public String toString() {
            return String.format("bloombergTicker=%s, a=%s, b=%s", thing.id, a, b);
        }
    }

    record Override(String id, String field, String value) {}
    static List<Override> OVERRIDES = List.of(
            new Override("3", "first", "3-first-override"),
            new Override("7", "second", "7-second-override"),
            new Override("1", "first", "1-first-override"),
            new Override("1", "second", "1-second-override")
            );

    static List<Attr> generate()
    {
        List<Attr> atts = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            var t = new Thing(String.valueOf(i));
            atts.add(new Attr(t, "first", String.valueOf(i)));
            atts.add(new Attr(t, "second", String.valueOf(i+1)));
        }
        return atts;
    }

    static List<Override> loadOverridesFromFile() throws IOException {
        Properties properties = new Properties();
        properties.load(Overrides1.class.getResourceAsStream( "/META-INF/overrides.properties"));

        List<Override> overrides = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            String[] parts = key.split("\\.");
            if (parts.length == 2) {
                String id = parts[0];
                String field = parts[1];
                String value = properties.getProperty(key);
                overrides.add(new Override(id, field, value));
            }
        }
        return overrides;
    }

    static List<Override> loadAndParseOverridesFromFile() throws IOException {
        Properties properties = new Properties();
        properties.load(Overrides1.class.getResourceAsStream( "/META-INF/overrides.properties"));

        String jsonString = properties.getProperty("overrides");
        if (jsonString == null || jsonString.trim().isEmpty()) {
            throw new IOException("Overrides property is missing or empty.");
        }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<List<Override>>(){}.getType());
    }

    public static void applyOverrides(List<Attr> attributes, List<Override> overrides) {
        // Create a map from overrides
        Map<String, Map<String, String>> overrideMap = overrides.stream()
                .collect(Collectors.groupingBy(
                        Override::id,  // Group by bloombergTicker
                        Collectors.toMap(
                                Override::field,  // Map by bloombergField
                                Override::value   // Values are the override values
                        )
                ));

        // Apply the overrides to the attributes
        for (Attr attribute : attributes) {
            // Get the overrides for the attribute's bloombergTicker
            Map<String, String> fieldsMap = overrideMap.get(attribute.thing.id);
            if (fieldsMap != null) {
                // Get the override for the attribute's bloombergField 'a'
                String newValue = fieldsMap.get(attribute.a);
                if (newValue != null) {
                    // Set the new fieldValue to the attribute's bloombergField 'b'
                    attribute.b = newValue;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<Attr> atts = generate();
        atts.forEach(System.out::println);
//        applyOverrides(atts, OVERRIDES);
        applyOverrides(atts, loadAndParseOverridesFromFile());
        atts.forEach(System.out::println);
    }

}
