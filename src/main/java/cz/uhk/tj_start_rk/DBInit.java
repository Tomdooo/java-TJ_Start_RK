package cz.uhk.tj_start_rk;

import cz.uhk.tj_start_rk.config.RsaKeyProperties;
import cz.uhk.tj_start_rk.model.*;
import cz.uhk.tj_start_rk.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class DBInit {

    public static void main(String[] args) {
        SpringApplication.run(TjStartRkApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder,
                                        EventRepository events,
                                        MatchRepository matches,
                                        MemberRepository members,
                                        TeamRepository teams,
                                        TrainingRepository trainings) {
        return args -> {
            if (members.findByUsername("tomas").isPresent()) return;

            //Add teams
            Team team1 = new Team("aaaa");
            teams.save(team1);
            Team team2 = new Team("bbbb");
            teams.save(team2);

            //Add members
            Member member1 = new Member("ADMIN","Václav", "Buřil", "vasek", passwordEncoder.encode("Password123@"),team1);
            members.save(member1);
            Member member2 = new Member("USER","Tomáš", "Němeček", "tomas", passwordEncoder.encode("Password123@"),team2);
            members.save(member2);

            //Add events
            Event event1 = new Event("Turnaj","aaaa",new Date(2022, Calendar.DECEMBER,10,15, 0),new Date(2022, Calendar.DECEMBER,10,15, 45),member1);
            events.save(event1);
            Event event2 = new Event("Turnaj","aaaa",new Date(2023, Calendar.JANUARY,10,15, 0),new Date(2023, Calendar.JANUARY,10,15, 45),member2);
            events.save(event2);

            //Add trainings
            Training training1 = new Training(new Date(2023, Calendar.JANUARY,10,15, 0),  new Date(2023, Calendar.JANUARY,10,15, 45),"1.","",1,member1);
            trainings.save(training1);
            Training training2 = new Training(new Date(2023, Calendar.JANUARY,11,15, 0),  new Date(2023, Calendar.JANUARY,11,15, 45),"2.","",2,team1);

            trainings.save(training2);

            //Matches
            Match match1 = new Match();

            List<Team> teamsArray = new ArrayList<>();
            teamsArray.add(team1);
            teamsArray.add(team2);

            match1.setTeams(teamsArray);
            match1.setHeader("aaaaaaaaaaa");
            match1.setNote("bbbbbbbbb");
            match1.setStart(new Date());

            matches.save(match1);
        };
    }
}



