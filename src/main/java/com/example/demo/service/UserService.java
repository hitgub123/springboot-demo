package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

@Service
public class UserService {

    private final DynamoDbClient dynamoDbClient;

    @Autowired
    public UserService(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void saveUser(User user) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName("Users")
                .item(AttributeValueUtil.toAttributeValues(user))
                .build();
        dynamoDbClient.putItem(request);
    }

    public List<User> getUsersById(String id) {
        // 构造查询条件
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":id", AttributeValue.builder().s(id).build());

        // 构建 Query 请求
        QueryRequest request = QueryRequest.builder()
                .tableName("Users")
                .keyConditionExpression("id = :id")
                .expressionAttributeValues(expressionValues)
                .build();

        // 执行查询
        QueryResponse response = dynamoDbClient.query(request);
        return response.items().stream()
                .map(item -> new User(
                        item.get("id").s(),
                        item.get("name").s(),
                        item.get("email").s()))
                .collect(Collectors.toList());
    }

    public User getUser(String id, String name) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s(id).build());
        key.put("name", AttributeValue.builder().s(name).build());

        // 构建 GetItem 请求
        GetItemRequest request = GetItemRequest.builder()
                .tableName("Users")
                .key(key)
                .build();
        GetItemResponse response = dynamoDbClient.getItem(request);
        return AttributeValueUtil.fromAttributeValues(response.item());
    }
}