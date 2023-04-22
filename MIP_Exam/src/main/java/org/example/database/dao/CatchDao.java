package org.example.database.dao;

import jakarta.persistence.TypedQuery;

import java.util.List;

public class CatchDao implements DaoI<CatchEntity>{
    private DatabaseConnection connection = new DatabaseConnection();
    @Override
    public CatchEntity get(Long id) {
        return connection.getEntityManager().find(CatchEntity.class, Long.valueOf(id));
    }
    @Override
    public List<CatchEntity> getAll() {
        TypedQuery<CatchEntity> query = connection.getEntityManager().createQuery("SELECT a FROM CatchEntity a", CatchEntity.class);
        return query.getResultList();
    }
    @Override
    public void create(CatchEntity movie) {
        connection.executeTransaction(entityManager -> entityManager.persist(movie));
    }

    public void deleteCatch() {
        CatchEntity catchToDelete = connection.getEntityManager().createQuery("SELECT c FROM CatchEntity c ORDER BY c.ora asc , c.minute asc , c.cautara asc ", CatchEntity.class)
                .setMaxResults(1)
                .getSingleResult();
        connection.executeTransaction(entityManager -> entityManager.remove(catchToDelete));
    }
    public CatchEntity getByNume(String numec) {
        CatchEntity echipa = null;
        try {
            echipa = connection.getEntityManager().createQuery("SELECT u FROM CatchEntity u WHERE u.nume=:numec", CatchEntity.class)
                    .setParameter("numec", numec).getSingleResult();
        }catch(Exception ex){
            echipa = null;
        }finally {
            return echipa;
        }
    }

    public List<CatchEntity> getByNumeAll(String numec) {
        List<CatchEntity> echipa = null;
        try {
            echipa = connection.getEntityManager().createQuery("SELECT u FROM CatchEntity u WHERE u.nume=:numec", CatchEntity.class)
                    .setParameter("numec", numec).getResultList();
        } catch (Exception ex) {
            echipa = null;
        } finally {
            return echipa;
        }
    }

    public void updateCautara(long id, int cautara) {
        CatchEntity catchToUpdate = connection.getEntityManager().find(CatchEntity.class, id);
        catchToUpdate.setCautara(cautara);
        connection.executeTransaction(entityManager -> entityManager.merge(catchToUpdate));
    }

}
