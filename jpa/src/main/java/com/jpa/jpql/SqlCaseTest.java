package com.jpa.jpql;

import com.jpa.entity.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
public class SqlCaseTest {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaStudy");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            String query = "select" +
                    "case when m.age <= 10 then '학생요금'" +
                    "     when m.age >= 60 then '경로요금'" +
                    "else '일반요금'" +
                    "end" +
                    "from Member m";

            List<String> findAllMember = entityManager.createQuery(query, String.class).getResultList();

            for ( String m : findAllMember) {
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
