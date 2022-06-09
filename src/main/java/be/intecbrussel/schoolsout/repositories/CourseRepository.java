package be.intecbrussel.schoolsout.repositories;

import be.intecbrussel.schoolsout.data.Course;
import be.intecbrussel.schoolsout.data.User;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class CourseRepository {

    public Course getOneById(long id){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        return em.find(Course.class, id);
    }


    public List<Course> getAll(){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        Query query = em.createQuery("SELECT v FROM Course v");
        return query.getResultList();
    }


    public void createOne(Course course){

        EntityManager em = EMFactory.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();

    }


    public void updateOne(Course course){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();

    }

    public void deleteOne(long id){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        em.getTransaction().begin();
        Course course = em.find(Course.class, id);
        if(course==null){
            System.out.println("Could not find the course with this id");
            return;
        }else {
            em.remove(course);
            em.getTransaction().commit();
            System.out.println("The course is deleted successfully!");
        }

    }
}
