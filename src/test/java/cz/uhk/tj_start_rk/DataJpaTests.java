//package cz.uhk.tj_start_rk;
//
//import cz.uhk.tj_start_rk.model.*;
//import cz.uhk.tj_start_rk.repositories.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Date;
//import java.util.Optional;
//
//@DataJpaTest
//public class DataJpaTests {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private TeamRepository teamRepository;
//    @Autowired
//    private EventRepository eventRepository;
//    @Autowired
//    private MatchRepository matchRepository;
//    @Autowired
//    private TrainingRepository trainingRepository;
//
//    @Test
//    public void testDbMember() {
//        // create
//        Member member = new Member("USER", "Alfonz", "Mucha", "alfmu", passwordEncoder.encode("Password123@"));
//        Member dbMember = memberRepository.save(member);
//
//        // get
//        Optional<Member> dbMember2 = memberRepository.findById(dbMember.getId());
//        dbMember = dbMember2.get();
//
//        // update
//        Member memberUpdate = new Member("USER", "Alfonzík", "Mušík", "alfmu2", null);
//        dbMember.update(memberUpdate);
//        memberRepository.save(dbMember);
//
//        // delete
//        memberRepository.delete(dbMember);
//    }
//
//    @Test
//    public void testDbTeam() {
//        // create
//        Team team = new Team("Turnajáci");
//        Team dbTeam = teamRepository.save(team);
//
//        // get
//        Optional<Team> dbTeam2 = teamRepository.findById(dbTeam.getId());
//        dbTeam = dbTeam2.get();
//
//        // update
//        Team teamUpdate = new Team("Zápasníci");
//        dbTeam.update(teamUpdate);
//        teamRepository.save(dbTeam);
//
//        // delete
//        teamRepository.delete(dbTeam);
//    }
//
//    @Test
//    public void testDbEvent() {
//        // create
//        Event event = new Event("Turnaj", null, new Date(), new Date(), null);
//        Event dbEvent = eventRepository.save(event);
//
//        // get
//        Optional<Event> dbEvent2 = eventRepository.findById(dbEvent.getId());
//        dbEvent = dbEvent2.get();
//
//        // update
//        Event eventUpdate = new Event("Zápas", "Poznámka", new Date(), new Date(), null);
//        dbEvent.update(eventUpdate);
//        eventRepository.save(dbEvent);
//
//        // delete
//        eventRepository.delete(dbEvent);
//    }
//
//    @Test
//    public void testDbMatch() {
//        // create
//        Match match = new Match("Domácí liga", null, null, null, new Date());
//        Match dbMatch = matchRepository.save(match);
//
//        // get
//        Optional<Match> dbMatch2 = matchRepository.findById(dbMatch.getId());
//        dbMatch = dbMatch2.get();
//
//        // update
//        Match matchUpdate = new Match("Veřejná liga", "Doma", null, null, new Date());
//        dbMatch.update(matchUpdate);
//        matchRepository.save(dbMatch);
//
//        // delete
//        matchRepository.delete(dbMatch);
//    }
//
//    @Test
//    public void testDbTraining() {
//        // create
//        Training training = new Training(new Date(), new Date(), "Domácí liga", null, 3, (Member) null);
//        Training dbTraining = trainingRepository.save(training);
//
//        // get
//        Optional<Training> dbTraining2 = trainingRepository.findById(dbTraining.getId());
//        dbTraining = dbTraining2.get();
//
//        // update
//        Training trainingUpdate = new Training(new Date(), new Date(), "trénink", "poznámka", 2, (Team) null);
//        dbTraining.update(trainingUpdate);
//        trainingRepository.save(dbTraining);
//
//        // delete
//        trainingRepository.delete(dbTraining);
//    }
//}
