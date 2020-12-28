package lt.verbus.eshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/h2/**", "/sign-in", "/public/**", "/", "/sign-out").permitAll()
                    .antMatchers("/private/**").authenticated()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .permitAll()
                    .loginPage("/sign-in")
                    .loginProcessingUrl("/sign-in")
                    .usernameParameter("user")
                    .passwordParameter("pass")
                    .defaultSuccessUrl("/private/user")
                    .failureUrl("/sign-in?error")
                    .and()
                .logout()
                    .logoutUrl("/sign-out")
                    .logoutSuccessUrl("/public/product");

        http.csrf().ignoringAntMatchers("/h2/**");
        http.headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//      -- Custom user storage --
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());

//        -- JDBC user storage example --
//        auth.jdbcAuthentication()
//                .passwordEncoder(encoder())
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT username, password, TRUE as enabled FROM user WHERE username = ?")
//                .authoritiesByUsernameQuery("SELECT u.username, r.role_name " +
//                        "FROM role r " +
//                        "JOIN user_role ur ON r.id = ur.role_id " +
//                        "JOIN user u ON u.id = ur.user_id " +
//                        "WHERE username = ?");


//        -- in memory user storage example --
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(encoder().encode("user"))
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password(encoder().encode("admin"))
//                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
