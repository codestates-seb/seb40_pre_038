package com.codestates.member.controller;

import com.codestates.member.assembler.MemberAssembler;
import com.codestates.member.entity.Member;
import com.codestates.member.service.MemberService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/members")
public class MemberController {

    //DI WIP
    private final MemberService memberService;
    private final MemberAssembler assembler;

    public MemberController(MemberService memberService, MemberAssembler assembler) {
        this.memberService = memberService;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")
    public EntityModel<Member> getMember(@PathVariable Long id) {

        Member member = memberService.findOne(id);
        return assembler.toModel(member);
    }

    @GetMapping
    public CollectionModel<EntityModel<Member>> getMembers() {

        List<EntityModel<Member>> members = memberService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(members,
                linkTo(methodOn(MemberService.class).findAll()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> postMember(@RequestBody Member member) {
        EntityModel<Member> entityModel = assembler.toModel(memberService.createOne(member));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchMember(@RequestBody Member member, @PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        return null;
    }
}