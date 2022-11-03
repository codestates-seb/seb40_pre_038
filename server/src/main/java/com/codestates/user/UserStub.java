//package com.codestates.user;
//
//import com.codestates.user.entity.User;
//import com.codestates.user.repository.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class UserStub {
//
//    private static final Logger log = LoggerFactory.getLogger(UserStub.class);
//
//    @Bean
//    CommandLineRunner UserInit(UserRepository userRepository) {
//
//        return args -> {
//
//            log.info("USER STUB " + userRepository.save(new User(1L, "mashed", "abc@email.com")));
//            log.info("USER STUB " + userRepository.save(new User(2L, "potato", "def@email.com")));
//
//            // wip
//            // user1 -> USER
//            // user2 -> ADMIN
//        };
//    }
//}
