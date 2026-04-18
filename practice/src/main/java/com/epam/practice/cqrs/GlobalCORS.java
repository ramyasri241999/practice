//package com.epam.practice.cqrs;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///*
// * lets build global level cors
// * 
// * CORS (Cross-Origin Resource Sharing) is a security feature implemented by web browsers to restrict 
// * web pages from making requests to a different domain than the one that served the web page. 
// * This is done to prevent malicious websites from accessing sensitive data on other domains without permission.
// * 
// */
//
//@Configuration
//public class GlobalCORS implements WebMvcConfigurer{
//	
//	/*
//	 *  When allowCredentials is true, allowedOrigins cannot contain the special value "*" since that cannot be set on the "Access-Control-Allow-Origin" response header. To allow credentials to a set of origins, list them explicitly or consider using "allowedOriginPatterns" instead.
//	 */
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//			.allowedOriginPatterns("*")
//			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//			.allowedHeaders("*")
//			.allowCredentials(true);
//	}
//	
//	
//	//if we are using spring security then we need to configure cors in security configuration as well
////	@Bean
////	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////
////	    http
////	        .cors() // 🔥 enable CORS
////	        .and()
////	        .csrf().disable();
////
////	    return http.build();
////	}
//	
//	
//	
////	@Bean
////	public CorsConfigurationSource corsConfigurationSource() {
////
////	    CorsConfiguration config = new CorsConfiguration();
////
////	    config.setAllowedOrigins(List.of("http://localhost:3000"));
////	    config.setAllowedMethods(List.of("*"));
////	    config.setAllowedHeaders(List.of("*"));
////	    config.setAllowCredentials(true);
////
////	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////	    source.registerCorsConfiguration("/**", config);
////
////	    return source;
////	}
//}
//
//
//	
//
//
