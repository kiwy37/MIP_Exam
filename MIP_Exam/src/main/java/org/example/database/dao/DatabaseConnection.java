package org.example.database.dao;

import jakarta.persistence.*;
import java.util.function.Consumer;

public class DatabaseConnection {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public DatabaseConnection() {
        this.initTransaction();
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public boolean executeTransaction(Consumer<EntityManager> action) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            action.accept(entityManager);
            entityTransaction.commit();
        } catch (RuntimeException e) {
            System.err.println("Transaction error: " + e.getLocalizedMessage());
            entityTransaction.rollback();
            return false;
        }
        return true;
    }

    private boolean initTransaction() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("default");
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            System.err.println("Error at initialing DatabaseManager: " + e.getMessage().toString());
            return false;
        }
        return true;
    }

    public void refresh(){
        entityManager.getEntityManagerFactory().getCache().evictAll();
    }
}
