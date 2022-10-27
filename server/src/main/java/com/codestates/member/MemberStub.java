package com.codestates.member;

import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberStub {

    private static final Logger log = LoggerFactory.getLogger(MemberStub.class);

    @Bean
    CommandLineRunner MemberInit(MemberRepository memberRepository) {

        return args -> {
            log.info("MEMBER STUB " + memberRepository.save(new Member(1L, "TalkingPotato", "abc@email.com")) );
            log.info("MEMBER STUB " + memberRepository.save(new Member(2L, "SweetPotato", "def@email.com")));

        };

    }

}
