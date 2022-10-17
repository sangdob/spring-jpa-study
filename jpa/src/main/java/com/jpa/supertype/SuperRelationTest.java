package com.jpa.supertype;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

@Slf4j
public class SuperRelationTest {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaStudy");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            Movie movie = new Movie();
            movie.setActor("송강");
            movie.setDirector("abc");

            entityManager.persist(movie);
            entityManager.flush();
            entityManager.clear();

            Movie findMovie = entityManager.find(Movie.class, movie.getId());
            log.info("findMovies = {}", movie.toString());

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
