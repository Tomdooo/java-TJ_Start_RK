package cz.uhk.tj_strat_rk;

import cz.uhk.tj_strat_rk.model.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
