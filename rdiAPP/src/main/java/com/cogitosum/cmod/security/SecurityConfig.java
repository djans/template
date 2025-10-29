package com.cogitosum.cmod.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.
                    authorizeHttpRequests(authorizeRequests -> authorizeRequests
                            .anyRequest().authenticated())
                    .oauth2Login(Customizer.withDefaults());
            return http.build();
        }


    // ACL ROLES
//    @Bean
//    public SecurityFilterChain securityFilterChain(
//            HttpSecurity http,
//            RoleLoggingFilter roleLoggingFilter,
//            OAuth2UserService<OAuth2UserRequest,
//            OAuth2User> customOAuth2UserService
//    ) throws Exception {
//        http.authorizeHttpRequests(authorizeRequests ->
//                authorizeRequests
//                    .requestMatchers("/").hasAuthority("ROLE_ROLE01")
//                    .requestMatchers("/admin/**").hasRole("ADMIN")
//                    .anyRequest().authenticated())
//
//                .addFilterAfter(roleLoggingFilter, UsernamePasswordAuthenticationFilter.class)
//                .oauth2Login(oauth2 -> oauth2
//                        .userInfoEndpoint(userInfo -> userInfo
//                                .userService(customOAuth2UserService)));
//
//
//        return http.build();
//    }
    //MAPPIMG GROUP ID WITH ROLES
    // MAYBE NOT NECESSARY AS WE ARE USING AZURE
//    @Bean
//    @Primary
//    public OAuth2UserService<OAuth2UserRequest, OAuth2User> customOAuth2UserService() {
//        return userRequest -> {
//            OAuth2User user = new DefaultOAuth2UserService().loadUser(userRequest);
//            List<GrantedAuthority> mappedAuthorities = new ArrayList<>();
//            System.out.println("mappedAuthorities");
//            List<String> groupIds = user.getAttribute("groups"); // these are GUIDs
//            if (groupIds != null) {
//                for (String groupId : groupIds) {
//                    // Map known group IDs to role names
//                    System.out.println("GROUPE"+groupId);
//                    switch (groupId) {
//                        case "703124b4-0cd1-4f89-8a49-c566deea4602":
//                            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//                            break;
//                        case "3a598333-7926-42e4-97a9-d74cf6a952d5":
//                            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//                            break;
//                        // Add more mappings as needed
//                    }
//                }
//            }
//
//            return new DefaultOAuth2User(mappedAuthorities, user.getAttributes(), "name");
//        };
//    }
}
