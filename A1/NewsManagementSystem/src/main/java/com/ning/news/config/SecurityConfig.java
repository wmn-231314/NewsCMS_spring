package com.ning.news.config;

import com.ning.news.config.Config;
import com.ning.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String SECRET_KEY = "123456";

    @Lazy
    @Autowired
    UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 防止网站攻击
        http.csrf().disable()  // 关闭csrf功能
            .formLogin()
                .loginPage("/login")
                .usernameParameter("account")
                .passwordParameter("password")
                .failureForwardUrl("/login?error")
                .defaultSuccessUrl("/index")
                .and()
            .authorizeRequests()
                .antMatchers("/","/index","/login","/register","/logout","/verify/**","/news/**","/static/**").permitAll()
                .antMatchers("/user/**").hasAnyRole(Config.VIP_USER,Config.PRIMARY_EDITOR,Config.SENIOR_EDITOR,Config.ADMIN)
                .antMatchers("/newsManage/**").hasAnyRole(Config.PRIMARY_EDITOR,Config.SENIOR_EDITOR)
                .antMatchers("/review/**").hasRole(Config.SENIOR_EDITOR)
                .antMatchers("/admin/**").hasRole(Config.ADMIN)
                .anyRequest().authenticated()
            .and()
            .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index")
            .and()
            .rememberMe() // 开启记住密码功能
                .rememberMeServices(getRememberMeServices())
                .key(SECRET_KEY) // 必须提供
            .and()
            .exceptionHandling().accessDeniedPage("/error/403");
    }

    private TokenBasedRememberMeServices getRememberMeServices() {
        TokenBasedRememberMeServices services = new TokenBasedRememberMeServices(SECRET_KEY, userService);
        services.setCookieName("remember-cookie");
//        services.setTokenValiditySeconds(100); // 默认14天
        return services;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
