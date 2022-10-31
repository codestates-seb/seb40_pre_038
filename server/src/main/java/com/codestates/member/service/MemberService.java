package com.codestates.member.service;

import com.codestates.auth.utils.CustomAuthorityUtils;
import com.codestates.auth.utils.MemberRegistrationApplicationEvent;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;
    private final ApplicationEventPublisher publisher;

    public MemberService(MemberRepository repository,
                         PasswordEncoder passwordEncoder,
                         CustomAuthorityUtils authorityUtils,
                         ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
        this.publisher = publisher;
    }

    @Transactional(readOnly = true)
    public Member findOne(long id) {
        return findVerifiedMember(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(repository.findAll());
    }

//    public Member createOne(Member member) {
//        Optional<Member> emailCheck = repository.findByEmail(member.getEmail());
//        if (emailCheck.isPresent())
//            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
//        return member;
//    }  // old version

    public Member createOne(Member member) {
        Optional<Member> verifiedMember = repository.findByEmail(member.getEmail());
        if (verifiedMember.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);
        Member savedMember = repository.save(member);

        publisher.publishEvent(new MemberRegistrationApplicationEvent(savedMember));
        return savedMember;
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