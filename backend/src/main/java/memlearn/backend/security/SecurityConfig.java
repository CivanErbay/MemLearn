package memlearn.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final MongoDbUserDetailsService userDetailsService;
    private final JWTAuthFilter jwtAuthFilter;

    @Autowired
    public SecurityConfig(MongoDbUserDetailsService userDetailsService, JWTAuthFilter jwtAuthFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    //configure zieht sich "auth" (vom Fronend?) in dem Format AuthenticationManagerBuilder.
    //Auf "auth" aus dem input wird userDetailsService aus dem MongoDbUserDetailsService aufgerufen.
    //Warum userDetailservice in userDetailService?
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
/*        auth.inMemoryAuthentication().withUser("Frank").password("123").roles("user").and()
        .withUser("Civan").password("456").roles("admin");*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
      /*  return new BCryptPasswordEncoder();*/
    }

    @Override //Here we can configure which URL's should be protected -- das wird ausgeführt beim Serverstart
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //normalerweise sind Post/Put/Get etc. standardmäßig verboten.
                .authorizeRequests()
                .antMatchers("/learn/**").hasRole("kanzler")
                .antMatchers("/**").permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }

             /*   .antMatchers("/login").permitAll()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/admin/**").hasRole("admin");*/

    @Bean   //Useable everywhere because of bean - in this case for the login-Controller
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
