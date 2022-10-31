package com.codestates.user.controller;

import com.codestates.user.assembler.UserAssembler;
import com.codestates.user.entity.User;
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

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    //DI WIP
    private final UserService userService;
    private final UserAssembler assembler;

    public UserController(UserService userService, UserAssembler assembler) {
        this.userService = userService;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUser(@PathVariable long id) {
        User user = userService.findOne(id);
        return assembler.toModel(user);
    }

    @GetMapping
    public CollectionModel<EntityModel<User>> getUsers() {
        List<EntityModel<User>> users = userService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(UserService.class).findAll()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> postUser(@Valid @RequestBody User user) {
        EntityModel<User> entityModel = assembler.toModel(userService.createOne(user));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@RequestBody User user, @PathVariable long id) {
        EntityModel<User> entityModel = assembler.toModel(userService.updateOne(user, id));
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