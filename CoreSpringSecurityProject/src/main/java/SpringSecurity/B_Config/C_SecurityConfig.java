package SpringSecurity.B_Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class C_SecurityConfig extends WebSecurityConfigurerAdapter {

	// For More Information Use
	// https://howtodoinjava.com/spring-security/login-form-example/

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Jay").password(passwordEncoder().encode("Password@1234")).roles("ADMIN")
				.and().withUser("Heena").password(passwordEncoder().encode("Password@1234")).roles("MANAGER").and()
				.withUser("Mansi").password(passwordEncoder().encode("Password@1234")).roles("EMPLOYEE");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/home")
				.hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE").antMatchers("/admin/**").hasAnyRole("ADMIN")
				.antMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER").antMatchers("/employee/**")
				.hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE").antMatchers("/**").denyAll().and().formLogin()
				.loginPage("/login").usernameParameter("username").passwordParameter("password")
				.loginProcessingUrl("/process-login").defaultSuccessUrl("/home").failureUrl("/login?error=true")
				.permitAll().and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID").permitAll().and().csrf().disable().exceptionHandling()
				.accessDeniedPage("/access-denied").and().sessionManagement().maximumSessions(1)
				.expiredUrl("/login?expired=true");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
