//package cz.uhk.tj_start_rk.service;
//
//import cz.uhk.tj_start_rk.model.SecurityUser;
//import cz.uhk.tj_start_rk.repositories.MemberRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JpaUserDetailService implements UserDetailsService {
//    private final MemberRepository memberRepository;
//
//    public JpaUserDetailService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return memberRepository
//                    .findByUsername(username)
//                    .map(SecurityUser::new)
//                    .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
//    }
//}
