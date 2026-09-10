package com.mycompany.app;

import com.mycompany.app.entities.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("library_persistence_unit");

    // createInstance(emf);
   //findAndUpdateInstance(emf);
    detachAndReattachInstance(emf);

  }

  private static void createInstance(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager(); // Represent the persistence context

    try {
      em.getTransaction().begin();

      Book b = new Book();
      b.setName("my book3");
      b.setIsbn("333-456");
      em.persist(b);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void findAndUpdateInstance(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      Book b1 = em.find(Book.class, 1);
      b1.setName("my new book");
      System.out.println(b1);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void detachAndReattachInstance(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
       Book book1 =new Book();
       book1.setId(1);
       book1.setName("my new book");
       book1.setIsbn("123-456");

       em.merge(book1);
       em.detach(book1);
       book1.setName("my new newest book");
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

}
