/**
 * 
 */
package com.dmd.mall.security.server;

import com.dmd.mall.model.domain.MemberDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhailiang
 *
 */
public class TokenJwtEnhancer implements TokenEnhancer {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		MemberDetails m=(MemberDetails)authentication.getPrincipal();
		Map<String, Object> info = new HashMap<>();
		info.put("id", m.getUmsMember().getId());
		info.put("nickName", m.getUmsMember().getNickname());
		info.put("username", m.getUmsMember().getUsername());
		info.put("icon", m.getUmsMember().getIcon());
		info.put("personalizedSignature", m.getUmsMember().getPersonalizedSignature());
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
