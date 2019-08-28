package lsis.pal;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().requestMatchers(EndpointRequest.toAnyEndpoint()).authenticated()
                .anyRequest().permitAll().and()
                .httpBasic().and()
        .sessionManagement().sessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy())
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
