package com.codestates.event;

import com.codestates.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberRegistrationApplicationEvent {

    private Member member;

    public MemberRegistrationApplicationEvent(Member member) {
        this.member = member;
    }
}
