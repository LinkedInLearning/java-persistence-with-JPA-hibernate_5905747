package com.mycompany.app;

import java.util.List;

import com.mycompany.app.entities.Author;
import com.mycompany.app.entities.Book;
import com.mycompany.app.entities.Group;
import com.mycompany.app.entities.Item;
import com.mycompany.app.entities.Review;
import com.mycompany.app.entities.User;
import com.mycompany.app.entities.keys.ItemKey;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("library_persistence_unit");

    // createInstance(emf);
    // findAndUpdateInstance(emf);
    // detachAndReattachInstance(emf);
    // removeInstance(emf);
    // useGetReference(emf);
    // useRefresh(emf);
    // createEntityWithComposedPK(emf);
    // oneToOneRelationship(emf);
    // oneToManyRelationship(emf);
    manyToManyRelationship(emf);

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
      Book b1 = new Book();
      b1.setId(1);
      b1.setName("my newest book");
      b1.setIsbn("123-456");
      em.merge(b1);
      em.detach(b1);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void removeInstance(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      Book b1 = em.find(Book.class, 1);
      em.remove(b1);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void useGetReference(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Book b1 = em.getReference(Book.class, 1);
      System.out.println(b1);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void useRefresh(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      Book b1 = em.find(Book.class, 1);
      System.out.println(b1);
      b1.setName("some book");
      System.out.println("Before " + b1);

      em.refresh(b1);
      System.out.println("After " + b1);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void createEntityWithComposedPK(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      // BookType bt = new BookType();
      // bt.setCode("C001");
      // bt.setSubCode("SC001");
      // bt.setName("Fiction-Horror");

      // em.persist(bt);

      ItemKey id = new ItemKey();
      id.setCode("ABC");
      id.setNumber(100);

      Item i = new Item();
      i.setId(id);
      i.setName("ABC-100");

      em.persist(i);

      em.getTransaction().commit();
    } finally {
      em.close();
    }

  }

  private static void oneToOneRelationship(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Book book = new Book();
      book.setName("another book");
      book.setIsbn("1010-111");

      Author author = new Author();
      author.setName("John");

      book.setAuthor(author);

      em.persist(book);
      em.persist(author);

      em.getTransaction().commit();

    } finally {
      em.close();
    }
  }

  private static void oneToManyRelationship(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Book book = new Book();
      book.setName("book 123");
      book.setIsbn("123-123");

      Author author = em.find(Author.class, 1);
      book.setAuthor(author);

      Review review1 = new Review();
      review1.setComment("This book in good");
      review1.setBook(book);
      Review review2 = new Review();
      review2.setComment("This book is lovely");
      review2.setBook(book);

      book.setReviews(List.of(review1, review2));

      em.persist(book);

      em.getTransaction().commit();

    } finally {
      em.close();
    }
  }

  private static void manyToManyRelationship(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      User user1 = new User();
      user1.setName("User1");
      User user2 = new User();
      user2.setName("User2");

      Group group1 = new Group();
      group1.setName("Group1");
      Group group2 = new Group();
      group2.setName("Group2");

      group1.setUsers(List.of(user1, user2));
      group2.setUsers(List.of(user1));

      em.persist(group1);
      em.persist(group2);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }
}
