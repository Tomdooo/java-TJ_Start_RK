package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Member;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.MemberRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {
    private MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @JsonView(View.AllMember.class)
    @GetMapping("/members")
    public List<Member> getMembers() {
        List<Member> members = memberRepository.findAll();
        System.out.println("members.toString()");
        return members;
    }

    @JsonView(View.AllMember.class)
    @GetMapping("/members/{id}")
    public Optional<Member> getTeams(@PathVariable int id){
        return memberRepository.findById(id);
    }

    @JsonView(View.AllMember.class)
    @PostMapping("/members")
    public Member addMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @JsonView(View.AllMember.class)
    @DeleteMapping("/members/{id}")
    public void deleteMemberById(@PathVariable int id){
       memberRepository.deleteById(id);
    }
}
