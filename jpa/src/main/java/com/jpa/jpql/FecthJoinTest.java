package com.jpa.jpql;

import com.jpa.oneway.Member;
import com.jpa.oneway.Team;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
public class FecthJoinTest {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaStudy");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("name1");
            entityManager.persist(team);

            Team team1 = new Team();
            team1.setName("팀2");
            entityManager.persist(team1);


            Member member = new Member();
            member.setUserName("회원1");
            member.setTeam(team);
            entityManager.persist(member);


            Member member1 = new Member();
            member.setUserName("회원2");
            entityManager.persist(member1);
            member.setTeam(team1);
            entityManager.persist(member1);

            Member member2 = new Member();
            member.setUserName("회원3");
            member.setTeam(team);
            entityManager.persist(member2);

            entityManager.flush();
            entityManager.clear();

//            fetch join 시 alias 왠만하면 사용하지말기!!
            String query = "SELECT m from member m join fetch t.members";

            List<Member> findAllMember = entityManager.createQuery(query, Member.class).getResultList();

            for ( Member m : findAllMember) {
                log.info("member = {}", m.toString());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.getStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
