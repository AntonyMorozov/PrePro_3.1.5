//package ru.kata.spring.rest;
//
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@Component
//public class RestTemplateClient {
//    private static final String urlUsers = "http://94.198.50.185:7081/api/users";
//
//    private final RestTemplate restTemplate;
//
//    private final HttpHeaders headers = new HttpHeaders();
//
//    public RestTemplateClient(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public ResponseEntity<List<User>> getAllUsers() {
//        return restTemplate.exchange(urlUsers, HttpMethod.GET, null,
//                new ParameterizedTypeReference<>() {
//                });
//    }
//
//    public ResponseEntity<String> addUser(User user, String cookies) {
//        headers.set("Cookie", cookies);
//        HttpEntity<User> entity = new HttpEntity<>(user, headers);
//        return restTemplate.exchange(urlUsers, HttpMethod.POST, entity, String.class);
//    }
//
//    public ResponseEntity<String> updateUser(User user, String cookies) {
//        headers.set("Cookie", cookies);
//        HttpEntity<User> entity = new HttpEntity<>(user, headers);
//        return restTemplate.exchange(urlUsers, HttpMethod.PUT, entity, String.class);
//    }
//
//    public ResponseEntity<String> deleteUser(User user, String cookies) {
//
//        headers.set("Cookie", cookies);
//        HttpEntity<User> entity = new HttpEntity<>(user, headers);
//        return restTemplate.exchange(urlUsers + "/" + user.getId(), HttpMethod.DELETE, entity, String.class);
//    }
//}
