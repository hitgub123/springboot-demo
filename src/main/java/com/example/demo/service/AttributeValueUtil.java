package com.example.demo.service;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import java.util.HashMap;
import java.util.Map;

public class AttributeValueUtil {
    public static Map<String, AttributeValue> toAttributeValues(User user) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(user.getId()).build());
        item.put("name", AttributeValue.builder().s(user.getName()).build());
        item.put("email", AttributeValue.builder().s(user.getEmail()).build());
        return item;
    }

    public static Map<String, AttributeValue> toKey(String keyName, String keyValue) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(keyName, AttributeValue.builder().s(keyValue).build());
        return key;
    }

    public static User fromAttributeValues(Map<String, AttributeValue> item) {
        if (item == null || item.isEmpty()) return null;
        return new User(
                item.get("id").s(),
                item.get("name").s(),
                item.get("email").s()
        );
    }
}