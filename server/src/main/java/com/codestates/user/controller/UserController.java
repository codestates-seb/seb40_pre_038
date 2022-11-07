package com.codestates.user.controller;

import com.codestates.user.assembler.UserAssembler;
import com.codestates.user.dto.UserDto;
import com.codestates.user.entity.User;
import com.codestates.user.mapper.UserMapper;
import com.codestates.user.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = {"http://localhost:3000",
                "http://pre-project-038-client.s3-website.ap-northeast-2.amazonaws.com",
                "http://seb40-pre-038.vercel.app",
        "http://ec2-13-125-208-244.ap-northeast-2.compute.amazonaws.com:8080"}, allowCredentials = "true")
@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;
    private final UserAssembler assembler;

    private final UserMapper mapper;

    public UserController(UserService userService, UserAssembler assembler, UserMapper mapper) {
        this.userService = userService;
        this.assembler = assembler;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public EntityModel<UserDto.Response> getUser(@PathVariable long id) {
        User user = userService.findOne(id);
        UserDto.Response response = mapper.userToUserResponseDto(user);
        return assembler.toModel(response);
    }

    @GetMapping
    public CollectionModel<EntityModel<UserDto.Response>> getUsers() {
        List<EntityModel<UserDto.Response>> users = userService.findAll().stream()
                .map(mapper::userToUserResponseDto)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        System.out.println(users.getClass());
        System.out.println(linkTo(methodOn(UserService.class).findAll()).withSelfRel());

        return CollectionModel.of(users,
                linkTo(methodOn(UserService.class).findAll()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> postUser(@Valid @RequestBody UserDto.Post requestBody) {
        User user = mapper.userPostToUser(requestBody);
        UserDto.Response response = mapper.userToUserResponseDto(userService.createOne(user));
        EntityModel<UserDto.Response> entityModel = assembler.toModel(response);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@Valid @RequestBody UserDto.Patch requestBody, @PathVariable long id) {
        User user = userService.updateOne(mapper.userPatchDtoToUser(requestBody), id);
        EntityModel<UserDto.Response> entityModel = assembler.toModel(mapper.userToUserResponseDto(user));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        userService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }
}