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
        return repository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
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

    public Member updateOne(Member newMember, long id) {
        return repository.findById(id)
                .map(member -> {
                    member.setNickName(newMember.getNickName());
                    member.setEmail(newMember.getEmail());
                    return repository.save(member);
                })
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
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