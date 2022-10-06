package com.jpa;

import com.jpa.entity.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Slf4j
public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaStudy");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
        /*    Member member = new Member();
            member.setName("A");

            Member member2 = new Member();
            member.setName("B");

            Member member3 = new Member();
            member.setName("C");

            entityManager.persist(member);
            entityManager.persist(member2);
            entityManager.persist(member3);

            log.info("member1 = {}", member.getName());
            log.info("member2 = {}", member2.getName());
            log.info("member3 = {}", member3.getName());*/

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
