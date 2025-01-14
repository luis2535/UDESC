package br.com.example.gerenciamento;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityConfig extends WebSecurityConfiguration {

    @SuppressWarnings({ "deprecation", "removal" })
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/auth").permitAll()
                .anyRequest().authenticated()
                .and().cors()
                .and().csrf().disable();
    }
}
