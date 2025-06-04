package com.mycompany.app;

import com.mycompany.app.entities.Book;
import com.mycompany.app.entities.BookType;
import com.mycompany.app.entities.Item;
import com.mycompany.app.entities.keys.ItemKey;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * This application introduces basic concepts of using an ORM, in this case
 * Hibernate, as a JPA provider.
 * <p>
 * The main class demonstrates how to create, find, update, detach, reattach,
 * and remove instances of the Book entity using JPA. It also shows how to use
 * the getReference method to obtain a reference to an entity without
 * immediately
 * loading it from the database.
 * <p>
 * To run this application, ensure you have the necessary dependencies for JPA
 * and Hibernate in your project. The example uses an in-memory H2 database
 * for simplicity, but you can configure it to use any other database by
 * changing
 * the JDBC URL and driver in the persistence.xml file.
 * <p>
 * An example persistence.xml file is provided below, which should be placed in
 * the src/main/resources/META-INF directory of your project. This file defines
 * the persistence unit and the properties required to connect to the database.
 * 
 * <pre>{@code 
 * <?xml version="1.0" encoding="UTF-8"?> 
 * <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
 *           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *           xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
 *           http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
 *           version="2.1">
 *  <persistence-unit name="library_persistence_unit">
 *      <class>com.mycompany.app.entities.Book</class>
 *      <properties>
 *          <property name="jakarta.persistence.jdbc.driver" value=
"org.h2.Driver"/>
 *          <property name="jakarta.persistence.jdbc.url" value=
"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"/>
 *          <property name="jakarta.persistence.jdbc.user" value="sa"/>
 *          <property name="jakarta.persistence.jdbc.password" value=""/>
 *          <property name="hibernate.hbm2ddl.auto" value="create-drop"/>
 *          <property name="hibernate.dialect" value=
"org.hibernate.dialect.H2Dialect"/>
 *      </properties>
 *  </persistence-unit> 
 * </persistence>
 * }</pre>
 * 
 * <p>
 * The example uses an in-memory H2 database, which is suitable for testing and
 * development purposes.
 * <p>
 * Ensure you have the necessary dependencies in your pom.xml or build.gradle
 * file
 * for JPA and Hibernate.
 * For example, if you are using Maven (pom.xml), example dependencies would be:
 * 
 * <pre>{@code
 * <dependency>
 *    <groupId>org.hibernate</groupId>
 *    <artifactId>hibernate-core</artifactId>
 *    <version>5.4.32.Final</version>
 * </dependency>
 * <dependency>
 *    <groupId>org.hibernate</groupId>
 *    <artifactId>hibernate-entitymanager</artifactId>
 *    <version>5.4.32.Final</version>
 * </dependency>
 * <dependency>
 *    <groupId>com.h2database</groupId>
 *    <artifactId>h2</artifactId>
 *    <version>1.4.200</version>
 *    <scope>runtime</scope>
 * </dependency>
 * <dependency>
 *    <groupId>jakarta.persistence</groupId>
 *    <artifactId>jakarta.persistence-api</artifactId>
 *    <version>2.2.3</version>
 * </dependency>
 *}</pre>
 *
 * <p>
 * Or, if you are using Gradle (build.gradle), dependencies would
 * be:
 * 
 * <pre>{@code
 *   dependencies {
 *     implementation 'org.hibernate.orm:hibernate-core:6.4.4.Final'
 *     implementation 'jakarta.persistence:jakarta.persistence-api:3.1.0'
 *     // Add your JDBC driver as needed, e.g. for H2:
 *     runtimeOnly 'com.h2database:h2:2.2.224'
 *     // ...other dependencies...
 *   }
 * }</pre>
 * <p>
 * The above dependencies are examples and may need to be adjusted based on
 * your project setup. Make sure to adjust the versions according to your
 * project setup and requirements.
 * <p>
 * You can also use a different database by changing the JDBC URL and driver
 * in the persistence.xml file.
 * For example, to use MySQL, you would change the JDBC URL to:
 * 
 * <pre>{@code
 * <property name="jakarta.persistence.jdbc.url" value=
 * "jdbc:mysql://localhost:3306/mydb"/>
 * }</pre>
 * 
 * Or, if using PostgreSQL, you would change the JDBC URL to:
 * 
 * <pre>{@code
 * <property name="jakarta.persistence.jdbc.url" value=
 * "jdbc:postgresql://localhost:5432/mydb"/>
 * }
 * </pre>
 * 
 * <p>
 * The JDBC driver for MySQL or PostgreSQL must also be included in your
 * project dependencies.
 * For example, if you are using MySQL, you would add a dependency
 * 
 * <pre>{@code
 * <dependency>
 *    <groupId>mysql</groupId>
 *    <artifactId>mysql-connector-java</artifactId>
 *    <version>8.0.26</version>
 * </dependency>
 * }</pre>
 * 
 * or in a Gradle build script (build.gradle), you would add:
 * 
 * <pre>{@code
 * dependencies {
 *  runtimeOnly 'mysql:mysql-connector-java:8.0.26'
 * }
 * }</pre>
 *
 * <p>
 * If PostgreSQL is your choice, you would add the PostgreSQL driver
 * dependency in your pom.xml like this:
 * *
 * 
 * <pre>{@code
 * <dependency>
 *   <groupId>org.postgresql</groupId>
 *   <artifactId>postgresql</artifactId>  
 *  <version>42.2.20</version>
 * </dependency>
 * }</pre>
 *
 * or in a Gradle build script (build.gradle), you would add:
 * 
 * <pre>{@code 
 * dependencies {
 *  runtimeOnly 'org.postgresql:postgresql:42.2.20'
 * } 
 * }</pre>
 * 
 * <p>
 * Always adjust the JDBC URL, user, and password according to your database
 * setup. Adjust the version numbers according to your project requirements and
 * the
 * latest available versions. Ensure that the database is running and accessible
 * when you run the
 * application.
 * <p>
 * This application is a simple demonstration of how to use JPA with Hibernate
 * to perform basic CRUD operations on an entity. It is intended for educational
 * purposes and to provide a starting point for working with JPA in Java
 * applications.
 * <p>
 * Note: The code provided here is a basic example and does not include error
 * handling or advanced features such as transaction management, caching, or
 * query optimization. In a production application, you would want to implement
 * proper error handling, logging, and possibly use a more sophisticated
 * configuration for your persistence unit.
 * <p>
 * This code is provided as-is and is intended for educational purposes.
 * It is recommended to refer to the official JPA and Hibernate documentation
 * for more detailed information and best practices when working with JPA in
 * Java applications.
 * <p>
 * To execute and test the various methods in this class, you can
 * uncomment the corresponding method calls in the main method. Each method
 * demonstrates a different aspect of JPA entity management, such as creating,
 * finding, updating, detaching, reattaching, and removing entities.
 * You can run the application to see how each method works and observe the
 * output in the console.
 */

public class Main {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("library_persistence_unit");

    // Uncomment the method calls below to test different functionalities
    // createInstance(emf);
    // createMultipleInstances(emf);
    // findAndUpdateInstance(emf);
    // detachAndReattachInstance(emf);
    // removeInstance(emf);
    // useGetReference(emf);
    // useRefreah(emf);
    // createEntityWithCompositeKey(emf);

    emf.close();
  }

  /**
   * This method demonstrates how to create multiple instances of the Book entity
   * and persists them to the database.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method five Book instances with unique names and ISBNs, persists them
  // to the database, and commits the transaction. It uses the EntityManager
  // to manage the persistence context and ensure that the entities are saved
  // to the database. The method also ensures that the transaction is properly
  // managed by beginning a transaction, committing it after persisting the
  // entities, and closing the EntityManager to release resources.
  // The method is designed to be called from the main method to demonstrate
  // the creation and persistence of Book entities in a JPA context.
  // It is a basic example of how to use JPA to create and persist entities in a
  // relational database using Hibernate as the JPA provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void createMultipleInstances(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      for (int i = 1; i <= 5; i++) {
        Book book = new Book();
        book.setName("my book " + i);
        book.setIsbn(i + "23-4567890123");
        em.persist(book);
      }
      em.flush(); // Ensure the entities are persisted before committing
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  /**
   * This method creates a single instance of the Book entity and persists it
   * to the database.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method demonstrates how to create and persist a single Book entity.
  // It creates a Book instance with a name and ISBN, persists it to the database,
  // and commits the transaction.
  // The method uses the EntityManager to manage the persistence context and
  // ensure that the entity is saved to the database.
  // The method is designed to be called from the main method to demonstrate
  // the creation and persistence of a Book entity in a JPA context. 
  // It is a basic example of how to use JPA to create and persist entities in a
  // relational database using Hibernate as the JPA provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void createInstance(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Book book = new Book();
      book.setName("my book");
      book.setIsbn("123-4567890123");
      em.persist(book);
      System.out.println(book);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  /**
   * This method finds an existing instance of the Book entity, updates its
   * ISBN, and commits the changes to the database.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method demonstrates how to find an existing Book entity by its ID,
  // update its ISBN, and commit the changes to the database. While this method
  // updates the ISBN of the Book entity, any other properties of the
  // Book entity can also be updated in a similar manner. The book entity is
  // printed to the console after the update to show the changes made.
  // It uses the EntityManager to manage the persistence context and ensure that
  // the changes are saved to the database.
  // The method is designed to be called from the main method to demonstrate
  // the process of finding and updating an entity in a JPA context.
  // It is a basic example of how to use JPA to find and update entities in a
  // relational database using Hibernate as the JPA provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void findAndUpdateInstance(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Book book = em.find(Book.class, 2);
      if (book != null) {
        book.setIsbn("223-4567890123");
        System.out.println(book);
      }

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  /**
   * This method demonstrates how to detach an entity from the persistence context
   * and then reattach it by merging it back into the context.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method creates a new Book instance, sets its ID, name, and ISBN, and
  // then merges
  // it into the persistence context. The merge operation updates the entity in
  // the database if it already exists or creates a new entity if it does not.
  // The method is designed to be called from the main method to demonstrate
  // the process of detaching and reattaching an entity in a JPA context.
  // It is a basic example of how to use JPA to manage the lifecycle of entities
  // in a relational database using Hibernate as the JPA provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void detachAndReattachInstance(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Book book = new Book();
      book.setId(1);
      book.setName("my book");
      book.setIsbn("123-4567890123");
      em.merge(book);
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  /**
   * This method removes an existing instance of the Book entity from the
   * persistence context and commits the changes to the database.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method demonstrates how to remove an existing Book entity from the
  // persistence context and commit the changes to the database.
  // It retrieves the Book entity with the given ID, removes it from the
  // persistence
  // context, and commits the transaction to persist the changes.
  // The book entity is printed to the console after removal to show that it has
  // been removed from the persistence context.
  // It also prints the removed Book entity to the console.
  // The method uses the EntityManager to manage the persistence context and
  // ensure that the entity is removed from the database.
  // The method is designed to be called from the main method to demonstrate
  // the process of removing an entity in a JPA context.
  // It is a basic example of how to use JPA to remove entities from a
  // relational database using Hibernate as the JPA provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void removeInstance(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Book book = em.find(Book.class, 1);
      em.remove(book);
      System.out.println(book);
      // Note: The book is now in a removed state, and it will not be managed by the
      // EntityManager anymore.
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  /**
   * This method demonstrates how to use the getReference method to obtain a
   * reference to an entity without immediately loading it from the database.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method retrieves a reference to the Book entity with the given ID,
  // which does not hit the database immediately. The actual database access
  // happens when a property of the book is accessed, triggering the loading of
  // the entity. The method is designed to be called from the main method to
  // demonstrate the use of getReference in a JPA context.
  // It is a basic example of how to use JPA to obtain a reference to an entity
  // without immediately loading it from the database using Hibernate as the JPA
  // provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void useGetReference(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      // getReference does not hit the database immediately; it returns a proxy.
      Book book = em.getReference(Book.class, 2);

      // The book variable is a proxy that represents the Book entity with given ID.
      // It does not load the entity from the database until a property is accessed.
      // This is useful for performance optimization, as it allows you to defer
      // loading the entity until it is actually needed.
      System.out.println(book);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  /**
   * This method demonstrates how to use the refresh method to ensure that an
   * entity reflects the latest state from the database.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method retrieves a reference to the Book entity with the given ID,
  // modifies its name, and then calls the refresh method to reload the entity
  // from the database, ensuring that it reflects the latest state.
  // The method is designed to be called from the main method to demonstrate
  // the use of refresh in a JPA context.
  // It is a basic example of how to use JPA to refresh an entity and ensure it
  // reflects the latest state from the database using Hibernate as the JPA
  // provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void useRefreah(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Book book = em.getReference(Book.class, 2);
      System.out.println("Before change " + book);
      book.setName("Updated Book Name");
      System.out.println("After change " + book);

      // Refresh the entity to ensure it reflects the latest state
      em.refresh(book);
      System.out.println("After refresh " + book);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  /**
   * This method demonstrates how to create an entity with a composite key.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method creates a BookType entity with a composite key consisting
  // of a code and subCode, or an Item entity with a composite key consisting
  // of a code and a number. The method persists these entities to the database
  // and commits the transaction.
  // The method is designed to be called from the main method to demonstrate
  // the creation of entities with composite keys in a JPA context.
  // It is a basic example of how to use JPA to create and persist entities with
  // composite keys in a relational database using Hibernate as the JPA provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  // Note: The code for creating BookType and Item entities with composite keys
  // is commented out. Uncomment the relevant sections to create these entities.
  // Ensure that the BookType and Item classes are properly defined with
  // composite key annotations and that the ItemKey class is defined with the
  // appropriate composite key annotations as well.
  // The BookType class should have fields for code, subCode, and name,
  // and the Item class should have a field for id of type ItemKey,
  // which should be a composite key class with fields for code and number.
  // The ItemKey class should be defined with the appropriate annotations for
  // composite keys, such as @Embeddable and @EmbeddedId, to ensure that JPA
  // recognizes it as a composite key class.
  private static void createEntityWithCompositeKey(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      // Remove the block comment markers surrounding the following
      // block of code to create a BookType entity with a composite key.
      /*
       * // Create a new BookType instance with a composite key
       * BookType bookType = new BookType();
       * bookType.setCode("FIC");
       * bookType.setSubCode("SF001");
       * bookType.setName("Fiction");
       * 
       * // Persist the BookType entity
       * em.persist(bookType);
       */

      // Remove the block comment markers surrounding the following
      // block of code to create an Item entity with a composite key.
      /*
       * // Create a new Item instance with a composite key
       * ItemKey id = new ItemKey();
       * id.setCode("FIC");
       * id.setNumber(100);
       * 
       * Item item = new Item();
       * item.setId(id);
       * item.setName("Science Fiction Book");
       * 
       * em.persist(item);
       */
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }
}