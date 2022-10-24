package com.jpa.jpql;


import com.jpa.entity.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Slf4j
public class JpqlTest {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaStudy");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            List<Member> findAllMember = entityManager.createQuery("select M from Member", Member.class).getResultList();

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
