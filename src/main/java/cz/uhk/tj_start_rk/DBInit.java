package cz.uhk.tj_start_rk;

import cz.uhk.tj_start_rk.model.*;
import cz.uhk.tj_start_rk.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Null;
import java.util.*;
import java.util.function.Function;

public class DBInit {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tj_start");
        EntityManager em =emf.createEntityManager();
        em.getTransaction().begin();

        //Add teams
        Team team1 = new Team("aaaa");
        em.persist(team1);
        Team team2 = new Team("bbbb");
        em.persist(team2);

        //Add members
        Member member1 = new Member("Admin","Václav", "Buřil", "vasek", "Password123@",team1);
        em.persist(member1);
        Member member2 = new Member("Hrac","Tomáš", "Němeček", "tomas", "Password123@",team2);
        em.persist(member2);

        //Add events
        Event event1 = new Event("Turnaj","aaaa",new Date(2022, Calendar.DECEMBER,10,15, 0),new Date(2022, Calendar.DECEMBER,10,15, 45),member1);
        em.persist(event1);
        Event event2 = new Event("Turnaj","aaaa",new Date(2023, Calendar.JANUARY,10,15, 0),new Date(2023, Calendar.JANUARY,10,15, 45),member2);
        em.persist(event2);

        //Add trainings
        Training training1 = new Training(new Date(2023, Calendar.JANUARY,10,15, 0),  new Date(2023, Calendar.JANUARY,10,15, 45),"1.","",1,member1);
        em.persist(training1);
        Training training2 = new Training(new Date(2023, Calendar.JANUARY,11,15, 0),  new Date(2023, Calendar.JANUARY,11,15, 45),"2.","",2,team1);

        em.persist(training2);

        //Matches
        Match match1 = new Match();
        List<Team> teams = new ArrayList<>();
        //TODO date Match
        teams.add(team1);
        teams.add(team2);
        match1.setTeams(teams);
        match1.setHeader("aaaaaaaaaaa");
        match1.setNote("bbbbbbbbb");
        match1.setStart(new Date());
        em.persist(match1);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
