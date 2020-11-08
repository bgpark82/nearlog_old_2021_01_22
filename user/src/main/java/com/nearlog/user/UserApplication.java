package com.nearlog.user;

import com.nearlog.user.domain.oauth.db.OAuthClientDetails;
import com.nearlog.user.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootApplication
@RequiredArgsConstructor
public class UserApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	private final EntityManager em;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		OAuthClientDetails details = OAuthClientDetails.builder()
				.clientId("test")
				.resourceIds(null)
				.clientSecret(passwordEncoder.encode("test"))
				.scope("read,write")
				.authorizedGrantTypes("authorization_code,refresh_token,client_credentials")
				.webServerRedirectUri("http://localhost:8082/oauth2/callback")
				.authorities("ROLE_USER")
				.accessTokenValidity(3600)
				.refreshTokenValidity(50000)
				.additionalInformation(null)
				.autoapprove(null)
				.build();

		User user = User.builder()
				.email("bgpark")
				.password(passwordEncoder.encode("123"))
				.userRole(User.UserRole.USER)
				.build();

		em.persist(details);
		em.persist(user);
	}
}
