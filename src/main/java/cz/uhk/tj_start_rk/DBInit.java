package cz.uhk.tj_start_rk;

import cz.uhk.tj_start_rk.model.Member;
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
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DBInit {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tj_start");
        EntityManager em =emf.createEntityManager();
        em.getTransaction().begin();

        Member member1 = new Member("Admin","Václav", "Buřil");
        em.persist(member1);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
