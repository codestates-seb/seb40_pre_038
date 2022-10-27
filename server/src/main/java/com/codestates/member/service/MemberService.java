package com.codestates.member.service;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member findOne(long id) {
        return findVerifiedMember(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    public Member createOne(Member member) {
        Optional<Member> emailCheck = repository.findByEmail(member.getEmail());
        if (emailCheck.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        return member;
    }

    public Member updateOne(Member member, long id) {
        Member verifiedMember = findVerifiedMember(id);
        verifiedMember.setEmail(member.getEmail());
        verifiedMember.setNickName(member.getNickName());
        return repository.save(verifiedMember);
    }

    public void deleteOne(Long id) {
        Member verifiedMember = findVerifiedMember(id);
        verifiedMember.setMemberStatus(Member.MemberStatus.MEMBER_QUIT);
        repository.save(verifiedMember);
    }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(long id) {
        Optional<Member> optionalMember =
                repository.findById(id);
        return optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }
}