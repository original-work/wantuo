package com.unionx.wantuo.domains;
import org.apache.shiro.authc.AuthenticationException;
@SuppressWarnings("serial")
public class OAuth2AuthenticationException extends AuthenticationException {
    public OAuth2AuthenticationException(Throwable cause) {
        super(cause);
    }
}
