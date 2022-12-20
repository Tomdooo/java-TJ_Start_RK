package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Member;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberController(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @JsonView(View.AllMember.class)
    @GetMapping("/members")
//    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public List<Member> getMembers() {
        List<Member> members = memberRepository.findAll();
        System.out.println("members.toString()");
        return members;
    }

    @JsonView(View.AllMember.class)
//    @Secured("ROLE_USER")
    @GetMapping("/members/{id}")

    public Optional<Member> getMemberById(@PathVariable int id){
        return memberRepository.findById(id);
    }

    @JsonView(View.AllMember.class)
    @PostMapping("/members")
    public Member addMember(@RequestBody Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

//    @RolesAllowed("ROLE_USER")
    @JsonView(View.AllMember.class)
    @PutMapping("/members")
    public Member updateMember(@RequestBody Member member) {
        Member update = memberRepository.getReferenceById(member.getId());

        update.update(member);

        return memberRepository.save(update);
    }

    @JsonView(View.AllMember.class)
    @DeleteMapping("/members/{id}")
    public void deleteMemberById(@PathVariable int id){
       memberRepository.deleteById(id);
    }
}
