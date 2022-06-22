package io.github.prluciohermano.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.prluciohermano.security.jwt.JwtAuthFilter;
import io.github.prluciohermano.security.jwt.JwtService;
import io.github.prluciohermano.service.impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usuarioService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests().antMatchers("/login").permitAll()
            .antMatchers("/api/pedidos/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/api/produtos/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/**").permitAll()
            .antMatchers("/index*").permitAll()
            .antMatchers("/**").permitAll()
//            .antMatchers("/logout").permitAll()
            .antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
            .antMatchers(HttpMethod.POST, "/login").permitAll() 
//              .antMatchers("/webapp/**").permitAll()
//              .antMatchers("/webapp/pages/**").permitAll()
            .anyRequest().authenticated()
            .and().formLogin().loginPage("/login").permitAll()
            .defaultSuccessUrl("/arearestrita")
            .and()
            .logout().invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/login?logout=true"))
            .logoutSuccessUrl("/login?logout=true").permitAll()
                   
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        ;
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
        		"/static/",
        		"/resources/static/estilo/**",
        		"/css/**", "/js/", "/img/**",
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
