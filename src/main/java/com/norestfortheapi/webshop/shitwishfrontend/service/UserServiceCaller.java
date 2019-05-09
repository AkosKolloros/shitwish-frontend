package com.norestfortheapi.webshop.shitwishfrontend.service;

import com.norestfortheapi.webshop.shitwishfrontend.model.WishUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserServiceCaller {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.base}")
    private String baseUrl;

    @Value("${url.users}")
    private String userUrl;

    public WishUser getUser(long id) {
        try {
            ResponseEntity<WishUser> wishUserResponseEntity = restTemplate.exchange(baseUrl + userUrl + "/" + id,
                    HttpMethod.GET
                    , null, new ParameterizedTypeReference<WishUser>() {
                    });
            return wishUserResponseEntity.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public boolean postUser(WishUser user) {
        HttpStatus httpStatus = restTemplate.postForEntity(baseUrl + userUrl, user, WishUser.class).getStatusCode();
        if (httpStatus.equals(HttpStatus.CREATED)) {
            return true;
        }
        return false;
    }

    public boolean authenticateUser(WishUser loginUser) {
        HttpStatus httpStatus = restTemplate.postForEntity(baseUrl + userUrl + "/authentication", loginUser, WishUser.class).getStatusCode();
        if (httpStatus.equals(HttpStatus.ACCEPTED)) {
            return true;
        }
        return false;
    }
}
