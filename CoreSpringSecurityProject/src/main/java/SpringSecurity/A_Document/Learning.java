package SpringSecurity.A_Document;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

public class Learning {

	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true) // Prevents new logins when maximum
																					// sessions are reached
				.sessionRegistry(sessionRegistry()).expiredUrl("/login?expired=true") // Redirect URL when a session
																						// expires
				.and().sessionAuthenticationStrategy(concurrentSessionControlAuthenticationStrategy()) // Custom session
																										// authentication
																										// strategy
				.and().authorizeRequests()
				// Your antMatchers and authorization rules here...
				.and()
				// Other configurations...
				.formLogin()
				// Login form configuration...
				.permitAll().and().logout()
				// Logout configuration...
				.permitAll().and().csrf().disable();
	}

	@Bean
	public SessionAuthenticationStrategy concurrentSessionControlAuthenticationStrategy() {
		return new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry()) {
			@Override
			protected void allowableSessionsExceeded(List<SessionInformation> sessions, int allowableSessions,
					SessionRegistry registry) throws SessionAuthenticationException {
				// Redirect to login page with a message indicating maximum session limit
				// reached
				throw new SessionAuthenticationException("Maximum sessions reached. Please try again later.");
			}
		};
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

}
