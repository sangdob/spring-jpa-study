package com.twoway.entity;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
public class OneWayMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpastudy");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setUserName("member1");
            member.setTeam(team);

            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            Team findTeam = entityManager.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            for (Member m : members) {
                log.info("member = {}", m.getUserName());
            }

            team.getMembers().add(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.getStackTrace();
        } finally {
            entityManagerFactory.close();
        }
    }
}
