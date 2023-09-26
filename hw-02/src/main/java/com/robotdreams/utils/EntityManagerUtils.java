package com.robotdreams.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtils {

    static EntityManagerFactory emf = null;

    public static EntityManager getEntityManager(String persistenceUniteName) {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(persistenceUniteName);
        }

        return emf.createEntityManager();
    }

    public static void close(EntityManager em) {
        em.clear();
        em.close();
    }

}
