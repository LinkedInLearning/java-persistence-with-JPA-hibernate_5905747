package com.mycompany.app;

import java.util.List;
import java.util.Set;

import com.mycompany.app.entities.Address;
import com.mycompany.app.entities.Author;
import com.mycompany.app.entities.Book;
import com.mycompany.app.entities.BookType;
import com.mycompany.app.entities.CardPayment;
import com.mycompany.app.entities.CashPayment;
import com.mycompany.app.entities.Category;
import com.mycompany.app.entities.Fiction;
import com.mycompany.app.entities.Field;
import com.mycompany.app.entities.Group;
import com.mycompany.app.entities.Item;
import com.mycompany.app.entities.NonFiction;
import com.mycompany.app.entities.Review;
import com.mycompany.app.entities.Student;
import com.mycompany.app.entities.Student2;
import com.mycompany.app.entities.Teacher;
import com.mycompany.app.entities.Teacher2;
import com.mycompany.app.entities.User;
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

    try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("library_persistence_unit")) {
      // Uncomment the method calls below to test different functionalities
      // createInstance(emf);
      // createMultipleInstances(emf);
      // findAndUpdateInstance(emf);
      // detachAndReattachInstance(emf);
      // removeInstance(emf);
      // useGetReference(emf);
      // useRefreah(emf);
      // createEntityWithCompositeKey(emf);
      // createOneToOneRelationship(emf);
      // createOneToManyRelationship(emf);
      // createManyToManyRelationship(emf);
      // mappedSuperclassStrategy(emf);
      // singleTableStrategy(emf);
      // joinedTableStrategy(emf);
      // tablePerClassStrategy(emf);
      // compositionWithAssociation(emf);
      // compositionWithEmbedable(emf);
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

  /**
   * This method demonstrates how to create a one-to-one relationship
   * between the Book and Author
   * entities and persists them to the database.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method creates a Book instance and an Author instance,
  // sets the relationship between them, persists both entities to the
  // database, and commits the transaction.
  // The method uses the EntityManager to manage the persistence context and
  // ensure that the entities are saved to the database.
  // The method is designed to be called from the main method to demonstrate
  // the creation of a one-to-one relationship in a JPA context.
  // It is a basic example of how to use JPA to create and persist entities with
  // a one-to-one relationship in a relational database using Hibernate as the JPA
  // provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void createOneToOneRelationship(EntityManagerFactory emf) {

    try (EntityManager em = emf.createEntityManager()) {
      em.getTransaction().begin();

      Book book = new Book();
      book.setName("another one of my books");
      book.setIsbn("623-4567890123");

      Author author = new Author();
      author.setName("John Doe");

      book.setAuthor(author);

      em.persist(book);
      em.persist(author);

      em.getTransaction().commit();
    }
  }

  /**
   * This method demonstrates how to create a one-to-many relationship
   * between the Book and Review entities and persists them to the database.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method creates a Book instance and two Review instances,
  // sets the relationship between them, persists both entities to the
  // database, and commits the transaction.
  // The method uses the EntityManager to manage the persistence context and
  // ensure that the entities are saved to the database.
  // The method is designed to be called from the main method to demonstrate
  // the creation of a one-to-many relationship in a JPA context.
  // It is a basic example of how to use JPA to create and persist entities with
  // a one-to-many relationship in a relational database using Hibernate as the
  // JPA
  // provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void createOneToManyRelationship(EntityManagerFactory emf) {
    Book book = new Book();
    book.setName("Book with Authors");
    book.setIsbn("123-4567890123");

    Review review1 = new Review();
    review1.setComment("Great book!");
    review1.setBook(book);

    Review review2 = new Review();
    review2.setComment("Very informative.");
    review2.setBook(book);

    book.setReviews(List.of(review1, review2));

    try (EntityManager em = emf.createEntityManager()) {
      Author author = em.find(Author.class, 1);
      book.setAuthor(author);

      em.getTransaction().begin();

      em.persist(book);

      em.getTransaction().commit();
    }
  }

  /**
   * This method demonstrates how to create a many-to-many relationship
   * between the Group and User entities and persists them to the database.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method creates a Group instance and two User instances,
  // sets the relationship between them, persists both entities to the
  // database, and commits the transaction.
  // The method uses the EntityManager to manage the persistence context and
  // ensure that the entities are saved to the database.
  // The method is designed to be called from the main method to demonstrate
  // the creation of a many-to-many relationship in a JPA context.
  // It is a basic example of how to use JPA to create and persist entities with
  // a many-to-many relationship in a relational database using Hibernate as the
  // JPA
  // provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void createManyToManyRelationship(EntityManagerFactory emf) {
    try (EntityManager em = emf.createEntityManager()) {
      em.getTransaction().begin();

      User user1 = new User();
      user1.setName("Alice");

      User user2 = new User();
      user2.setName("Bob");

      Group group1 = new Group();
      group1.setName("Developers");

      Group group2 = new Group();
      group2.setName("Designers");

      group1.setUsers(List.of(user1, user2));
      group2.setUsers(List.of(user1));

      em.persist(group1);
      em.persist(group2);

      em.getTransaction().commit();
    }
  }

  /**
   * This method demonstrates how to use the mapped superclass strategy
   * to create entities that share common attributes.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method creates instances of Student and Teacher entities,
  // which inherit common attributes from a mapped superclass.
  // It sets specific attributes for each entity, persists them to the
  // database, and commits the transaction.
  // The method uses the EntityManager to manage the persistence context and
  // ensure that the entities are saved to the database.
  // The method is designed to be called from the main method to demonstrate
  // the use of mapped superclass strategy in a JPA context.
  // It is a basic example of how to use JPA to create and persist entities that
  // share common attributes in a relational database using Hibernate as the JPA
  // provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void mappedSuperclassStrategy(EntityManagerFactory emf) {
    try (EntityManager em = emf.createEntityManager()) {
      em.getTransaction().begin();

      Student student = new Student();
      student.setName("John Doe");
      student.setStudentCode("S12345");

      Teacher teacher = new Teacher();
      teacher.setName("Jane Smith");
      teacher.setTeacherCode("T67890");

      em.persist(student);
      em.persist(teacher);

      em.getTransaction().commit();
    }
  }

  /**
   * This method demonstrates how to use the single table inheritance strategy
   * to create entities that share a single table in the database.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method creates instances of Student2 and Teacher2 entities,
  // which inherit common attributes from a base class Member2 and are stored
  // in a single table in the database using the single table inheritance
  // strategy.
  // It sets specific attributes for each entity, persists them to the
  // database, and commits the transaction.
  // The method uses the EntityManager to manage the persistence context and
  // ensure that the entities are saved to the database.
  // The method is designed to be called from the main method to demonstrate
  // the use of single table inheritance strategy in a JPA context.
  // It is a basic example of how to use JPA to create and persist entities that
  // share a single table in a relational database using Hibernate as the JPA
  // provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void singleTableStrategy(EntityManagerFactory emf) {
    try (EntityManager em = emf.createEntityManager()) {
      em.getTransaction().begin();

      Student2 student = new Student2();
      student.setName("John Doe");
      student.setStudentCode("S12345");

      Teacher2 teacher = new Teacher2();
      teacher.setName("Jane Smith");
      teacher.setTeacherCode("T67890");

      em.persist(student);
      em.persist(teacher);

      em.getTransaction().commit();
    }
  }

  /**
   * This method demonstrates how to use the joined table inheritance strategy
   * to create entities that are stored in separate tables but share a common
   * base class.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method creates instances of Fiction and NonFiction entities,
  // which inherit common attributes from a base class Genre and are stored
  // in separate tables using the joined table inheritance strategy.
  // It sets specific attributes for each entity, persists them to the
  // database, and commits the transaction.
  // The method uses the EntityManager to manage the persistence context and
  // ensure that the entities are saved to the database.
  // The method is designed to be called from the main method to demonstrate
  // the use of joined table inheritance strategy in a JPA context.
  // It is a basic example of how to use JPA to create and persist entities that
  // share a common base class but are stored in separate tables in a
  // relational database using Hibernate as the JPA provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void joinedTableStrategy(EntityManagerFactory emf) {
    try (EntityManager em = emf.createEntityManager()) {
      em.getTransaction().begin();

      // Create instances of Fiction and NonFiction entities
      Fiction fiction = new Fiction();
      fiction.setCode("FIC");
      fiction.setSetting("Dystopian Future");

      NonFiction nonFiction = new NonFiction();
      nonFiction.setCode("NFIC");
      nonFiction.setTopic("Science");

      // Persist the entities
      em.persist(fiction);
      em.persist(nonFiction);

      em.getTransaction().commit();
    }
  }

  /**
   * This method demonstrates how to use the table-per-class inheritance strategy
   * to create entities that are stored in separate tables for each subclass.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method creates instances of CardPayment and CashPayment entities,
  // which inherit common attributes from a base class Payment and are stored
  // in separate tables using the table-per-class inheritance strategy.
  // It sets specific attributes for each entity, persists them to the
  // database, and commits the transaction.
  // The method uses the EntityManager to manage the persistence context and
  // ensure that the entities are saved to the database.
  // The method is designed to be called from the main method to demonstrate
  // the use of table-per-class inheritance strategy in a JPA context.
  // It is a basic example of how to use JPA to create and persist entities that
  // share a common base class but are stored in separate tables in a
  // relational database using Hibernate as the JPA provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void tablePerClassStrategy(EntityManagerFactory emf) {
    try (EntityManager em = emf.createEntityManager()) {
      em.getTransaction().begin();

      CardPayment cardPayment = new CardPayment();
      cardPayment.setCardNumber("1234-5678-9012-3456");
      cardPayment.setAmount(100.0);
      em.persist(cardPayment);

      CashPayment cashPayment = new CashPayment();
      cashPayment.setCode("CASH123");
      cashPayment.setAmount(50.0);
      em.persist(cashPayment);

      em.getTransaction().commit();
    }
  }

  /**
   * This method demonstrates how to create a composition relationship
   * with an association between Category and Field entities.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method creates instances of Category and Field entities,
  // establishes a many-to-many relationship between them, and persists
  // the entities to the database.
  // It sets the categories for each field and the fields for each category,
  // ensuring that the relationship is bidirectional.
  // The method uses the EntityManager to manage the persistence context and
  // ensure that the entities are saved to the database.
  // The method is designed to be called from the main method to demonstrate
  // the creation of a composition relationship with an association in a JPA
  // context.
  // It is a basic example of how to use JPA to create and persist entities with
  // a composition relationship in a relational database using Hibernate as the
  // JPA provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void compositionWithAssociation(EntityManagerFactory emf) {
    try (EntityManager em = emf.createEntityManager()) {
      em.getTransaction().begin();

      // Create a new Category instances
      Category category1 = new Category();
      category1.setName("Music");

      Category category2 = new Category();
      category2.setName("Art");

      // Create a new Field instances and associate it with the category1
      Field field1 = new Field();
      field1.setName("History");

      Field field2 = new Field();
      field2.setName("New advences");

      // Set the categories for each field
      field1.setCategories(Set.of(category1, category2));
      field2.setCategories(Set.of(category1, category2));

      // Set the fields for each category
      category1.setFields(Set.of(field1, field2));
      category2.setFields(Set.of(field1, field2));

      // Persist the entities
      em.persist(field1);
      em.persist(field2);

      em.getTransaction().commit();
    }
  }

  /**
   * This method demonstrates how to create a composition relationship
   * with an embeddable Address entity in the Author entity.
   *
   * @param emf The EntityManagerFactory used to create EntityManager instances.
   */
  // This method creates an Author entity with an embedded Address entity,
  // sets the address for the author, persists the author to the database,
  // and commits the transaction.
  // The method uses the EntityManager to manage the persistence context and
  // ensure that the entities are saved to the database.
  // The method is designed to be called from the main method to demonstrate
  // the creation of a composition relationship with an embeddable entity in a JPA
  // context.
  // It is a basic example of how to use JPA to create and persist entities with
  // a composition relationship using an embeddable entity in a relational database
  // using Hibernate as the JPA provider.
  // The method is annotated with @SuppressWarnings("unused") to indicate that it
  // is intentionally not used in the current context, but it can be uncommented
  // in the main method to execute it and see the results.
  @SuppressWarnings("unused")
  private static void compositionWithEmbedable(EntityManagerFactory emf) {
    try (EntityManager em = emf.createEntityManager()) {
      em.getTransaction().begin();

      // Create an Author entity with an embedded Address entity
      Author author = new Author();
      author.setName("William Shakespeare");

      // Create an Address entity and set its properties
      // The Address entity is marked as @Embeddable, allowing it to be embedded
      // within the Author entity.
      // This allows the Address entity to be treated as part of the Author entity,
      // and its fields will be stored in the same table as the Author entity.
      // The Address entity does not have its own identity and is typically used
      // to encapsulate address-related fields within an entity.
      // The Address entity is created and its properties are set.
      Address address = new Address();
      address.setStreet("Stratford-upon-Avon");
      address.setCity("Warwickshire");
      address.setPostalCode("CV37 6QW");

      // Set the address for the author
      // The Author entity has an Address field that is marked with @Embedded,
      // indicating that it contains an embedded Address entity.
      // This allows the Address entity to be stored as part of the Author entity
      // in the same table, and its fields will be mapped to columns in the Author
      // table.
      // The address is set for the author, establishing the composition relationship
      // between the Author and Address entities.
      // The Address entity is embedded within the Author entity, allowing it to be
      // treated as part of the Author entity and its fields to be stored in the same
      // table as the Author entity.
      // The Address entity does not have its own identity and is typically used to
      // encapsulate address-related fields within an entity.
      // The Address entity is created and its properties are set.
      // The Address entity is embedded within the Author entity, allowing it to be
      // treated as part of the Author entity and its fields to be stored in the same
      // table as the Author entity.
      author.setAddress(address);

      // Persist the author entity
      // The Author entity is persisted to the database, which means that it will be
      // saved to the database and its state will be managed by the EntityManager.
      // The EntityManager is responsible for managing the persistence context and
      // ensuring that the entities are saved to the database.
      em.persist(author);

      em.getTransaction().commit();
    }
  }
}