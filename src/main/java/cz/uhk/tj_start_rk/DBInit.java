//package cz.uhk.tj_start_rk;
//
//import cz.uhk.tj_start_rk.config.RsaKeyProperties;
//import cz.uhk.tj_start_rk.model.*;
//import cz.uhk.tj_start_rk.repositories.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Calendar;
//import java.util.Date;
//
//@EnableConfigurationProperties(RsaKeyProperties.class)
//@SpringBootApplication
//public class DBInit {
//
//    public static void main(String[] args) {
//        SpringApplication.run(DBInit.class, args);
//    }
//
//
//    @Bean
//    CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder,
//                                        EventRepository events,
//                                        MatchRepository matches,
//                                        MemberRepository members,
//                                        TeamRepository teams,
//                                        TrainingRepository trainings) {
//        return args -> {
////            if (members.findByUsername("tomas").isPresent()) return;
//            System.out.println("\n...Naplňujeme očekávání...\n");
//
//            //Add teams
//            Team team1 = new Team("skupina A");
//            teams.save(team1);
//            Team team2 = new Team("Brtníci");
//            teams.save(team2);
//
//            //Add members
//            Member member1 = new Member("ADMIN","Václav", "Buřil", "vasek", passwordEncoder.encode("Password123@"),team1);
//            members.save(member1);
//            Member member2 = new Member("USER","Tomáš", "Němeček", "tomas", passwordEncoder.encode("Password123@"),team2);
//            members.save(member2);
//
//            //Add events
//            Event event1 = new Event("Turnaj","",new Date(2023, Calendar.JANUARY,18,15, 0),new Date(2022, Calendar.JANUARY,18,15, 45),member1);
//            events.save(event1);
//            Event event2 = new Event("Turnaj","Doma",new Date(2023, Calendar.JANUARY,10,15, 0),new Date(2023, Calendar.JANUARY,10,15, 45),member2);
//            events.save(event2);
//
//            //Add trainings
//            Training training1 = new Training(new Date(2023, Calendar.JANUARY,10,15, 0),  new Date(2023, Calendar.JANUARY,10,15, 45),"1.","",1,member1);
//            trainings.save(training1);
//            Training training2 = new Training(new Date(2023, Calendar.JANUARY,11,15, 0),  new Date(2023, Calendar.JANUARY,11,15, 45),"2.","",2,team1);
//
//            trainings.save(training2);
//
//            //Matches
//            Match match1 = new Match();
//
//            match1.setLeague("2. liga");
//            match1.setHomeTeam(team1);
//            match1.setAwayTeam(team2);
//            match1.setNote("");
//            match1.setStart(new Date());
//
//            matches.save(match1);
//        };
//    }
//}
//
//
//
