package com.codestates.user.service;

import com.codestates.auth.utils.CustomAuthorityUtils;
import com.codestates.auth.utils.UserRegistrationApplicationEvent;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.user.entity.User;
import com.codestates.user.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;
    private final ApplicationEventPublisher publisher;

    public UserService(UserRepository repository,
                         PasswordEncoder passwordEncoder,
                         CustomAuthorityUtils authorityUtils,
                         ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
        this.publisher = publisher;
    }

    @Transactional(readOnly = true)
    public User findOne(long id) {
        return findVerifiedUser(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    public User createOne(User user) {
        // Email exists exception
        Optional<User> verifiedUser = repository.findByEmail(user.getEmail());
        if (verifiedUser.isPresent())
            throw new BusinessLogicException(ExceptionCode.EMAIL_EXISTS);
        // Nickname exists exception
        Optional<User> verifiedName = repository.findByNickName(user.getNickName());
        if (verifiedName.isPresent())
            throw new BusinessLogicException(ExceptionCode.NICKNAME_EXISTS);

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(user.getEmail());
        user.setRoles(roles);
        User savedUser = repository.save(user);

        publisher.publishEvent(new UserRegistrationApplicationEvent(savedUser));
        return savedUser;
    }

    public User updateOne(User user, long id) {
        User verifiedUser = findVerifiedUser(id);
        verifiedUser.setEmail(user.getEmail());
        verifiedUser.setNickName(user.getNickName());
        return repository.save(verifiedUser);
    }

    public void deleteOne(Long id) {
        User verifiedUser = findVerifiedUser(id);
        verifiedUser.setUserStatus(User.UserStatus.USER_QUIT);
        repository.save(verifiedUser);
    }

    @Transactional(readOnly = true)
    public User findVerifiedUser(long id) {
        Optional<User> optionalUser =
                repository.findById(id);
        return optionalUser.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    public User getLoginUser() { // 로그인된 유저로
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null || authentication.getName().equals("anonymousUser"))
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);

        Optional<User> optionalUser = repository.findByEmail(authentication.getName());
        User user = optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        System.out.println("HERE:"+user.getUserId());

        return user;
    }
}