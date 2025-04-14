package com.kh.boot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleAPIService {

    @Value("${google.login-api.client-id}")
    private String googleLoginApiClientId;

    @Value("${google.login-api.redirect-url}")
    private String googleLoginApiRedirectUrl;

    @Value("${google.login-api.client-secret}")
    private String googleLoginApiClientSecret;

    public String requestGoogleEmail(String code){
        String tokenResponse = requestGetGoogleToken(code);
        System.out.println(tokenResponse);

        String accessToken = extractAccessToken(tokenResponse);

        String userInfoResponse = getUserInfo(accessToken);

        String email = extractUserEmail(userInfoResponse);

        return email;
    }

    private String getUserInfo(String accessToken){
        //구글 사용자 정보 api
        String url = "https://www.googleapis.com/oauth2/v3/userinfo";

        //Http 헤더설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        //GET 요청이므로 헤더만 요청한다.
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        //GET 요청으로 사용자 정보 가져오기
        ResponseEntity<String> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        //응답 결과 반환
        return responseEntity.getBody();

    }

    private String extractAccessToken(String tokenResponse){
        //JSON 파싱해서 access_token 추출하기
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode jsonNode = mapper.readTree(tokenResponse);
            return jsonNode.get("access_token").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String extractUserEmail(String tokenResponse){
        //JSON 파싱해서 access_token 추출하기
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode jsonNode = mapper.readTree(tokenResponse);
            return jsonNode.get("email").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String requestGetGoogleToken(String code){
        String url = "https://oauth2.googleapis.com/token";

        //파라미터 설정
        String params = "code=" + code + "&grant_type=authorization_code"
                + "&client_id=" + googleLoginApiClientId
                + "&client_secret=" + googleLoginApiClientSecret
                + "&redirect_uri=" + googleLoginApiRedirectUrl;

        //Http 헤더설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); //form타입

        //HTTP POST요청 전달
        HttpEntity<String> entity = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();

        //구글 토큰 POST 요청
        ResponseEntity<String> responseEntity =  restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        //응답 결과 반환
        return responseEntity.getBody();
    }
}
