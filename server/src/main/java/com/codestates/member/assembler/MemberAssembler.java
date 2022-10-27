package com.codestates.member.assembler;

import com.codestates.member.controller.MemberController;
import com.codestates.member.entity.Member;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MemberAssembler implements RepresentationModelAssembler <Member, EntityModel<Member>> {

    @Override
    public EntityModel<Member> toModel(Member member) {
        return EntityModel.of(member,
                linkTo(methodOn(MemberController.class).getMember(member.getMemberId())).withSelfRel(),
                linkTo(methodOn(MemberController.class).getMembers()).withRel("members"));
    }

}
