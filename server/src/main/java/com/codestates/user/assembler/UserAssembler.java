package com.codestates.user.assembler;

import com.codestates.user.controller.UserController;
import com.codestates.user.dto.UserDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler implements RepresentationModelAssembler <UserDto.Response, EntityModel<UserDto.Response>> {

    @Override
    public EntityModel<UserDto.Response> toModel(UserDto.Response response) {
        return EntityModel.of(response,
                WebMvcLinkBuilder.linkTo(methodOn(UserController.class).getUser(response.getUserId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
    }

}