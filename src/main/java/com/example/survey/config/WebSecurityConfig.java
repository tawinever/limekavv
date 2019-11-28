package com.example.survey.config;

import com.example.survey.helper.backend.SimpleAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .ignoringAntMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/submitTransaction").permitAll()
                .antMatchers("/promo").permitAll()
                .antMatchers("/font/*", "/font/**", "/js/*", "/js/**", "/css/*", "/css/**", "/img/*", "/img/**", "/DataTables/*", "/DataTables/**").permitAll()
                .antMatchers("/stats").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()

                .successHandler(successHandler)

                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                //todo set encoded password
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select email, password, status from usr where email = ?")
                .authoritiesByUsernameQuery("select email, role from usr where email = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
