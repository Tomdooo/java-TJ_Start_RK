package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Member;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.MemberRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class MemberController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberController(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @JsonView(View.AllMember.class)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/members")
    public List<Member> getMembers() {
        List<Member> members = memberRepository.findAll();
        System.out.println("members.toString()");
        return members;
    }

    //    @Secured("ROLE_USER")
    @JsonView(View.AllMember.class)
    @GetMapping("/members/{id}")
    public Optional<Member> getMemberById(@PathVariable int id){
        return memberRepository.findById(id);
    }

    @JsonView(View.AllMember.class)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/members")
    public Member addMember(@RequestBody Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

//    @RolesAllowed("ROLE_USER")
    @JsonView(View.AllMember.class)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/members")
    public Member updateMember(@RequestBody Member member) {
        Member update = memberRepository.getReferenceById(member.getId());

        update.update(member);

        return memberRepository.save(update);
    }

    // TODO update hesla

    @JsonView(View.AllMember.class)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/members/{id}")
    public void deleteMemberById(@PathVariable int id){
       memberRepository.deleteById(id);
    }
}
