package be.intecbrussel.schoolsout.repositories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFactory {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEmf(){
        if (entityManagerFactory == null) {

            entityManagerFactory = Persistence.createEntityManagerFactory("pearlDataBase");
        }
        return entityManagerFactory;
    }
}
