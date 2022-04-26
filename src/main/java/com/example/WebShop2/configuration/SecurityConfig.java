package com.example.WebShop2.configuration;


import com.example.WebShop2.model.CustomUserDetails;
import com.example.WebShop2.repository.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserDetailsService customUserDetails;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().
               antMatchers("/**", "/shop/**", "/register", "/admin/**").permitAll().
//               antMatchers("/admin/**").hasRole("ADMIN").
               anyRequest()
               .authenticated()
               .and()
               .formLogin()
               .loginPage("/login")
               .permitAll()
               .failureUrl("/login?error=true")
               .defaultSuccessUrl("/")
               .usernameParameter("email")
               .passwordParameter("password");

       http.headers().frameOptions().disable();


    }
    @Bean
    protected BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetails);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**");
    }
}
