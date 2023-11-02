package com.fisher.securityapp.config;

import com.fisher.securityapp.service.PersonDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailsService personDetailsService;

    @Override
    // Конфигурируем Аутентификацию
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService);
    }

    @Override
    // Конфигурируем работу с http запросами, а именно
    // авторизацию (т.е. какие страницы доступны)
    // страницы входа/выхода, страницы успеха/неудачи
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // настраиваем авторизацию (т.е. доступы)
                .antMatchers("/auth/login", "/auth/registration", "/error")
                .permitAll() // эти страницы доступны всем
                .anyRequest().authenticated() // для других запросов юзер должен аутентифицироваться
                .and()
                .formLogin().loginPage("/auth/login") // на какую страницу вести неавторизованных
                .loginProcessingUrl("/process_login") // адрес того куда отправлять данные с формы
                .defaultSuccessUrl("/hello", true) // страница после удачной аутентификации
                .failureUrl("/auth/login?error") // страница после не удачной аутентификации
                .and()
                .logout()
                .logoutUrl("/logout") // адрес для выхода
                .logoutSuccessUrl("/auth/login"); // куда перекидывать после выхода
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
