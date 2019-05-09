package com.norestfortheapi.webshop.shitwishfrontend.model;

import lombok.*;
import org.springframework.context.annotation.Scope;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Scope("session")
@ToString
public class WishUser {
    private long id;
    private String userName;
    private String password;
    private UserAddress address;
    private String email;
}
