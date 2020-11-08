package com.nearlog.user.domain.oauth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class OAuthToken {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private Long expires_in;
    private String scope;

}

