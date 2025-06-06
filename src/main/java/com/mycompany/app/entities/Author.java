package com.mycompany.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents an author in the system.
 * 
 * This class is marked as @Entity, indicating that it is a JPA entity
 * and will be mapped to a database table named "author".
 * 
 * Example usage:
 * 
 * <pre>
 * &#64;Entity
 * public class Book {
 *   &#64;Id
 *   private Long id;
 *   private String title;
 *
 *   @ManyToOne
 *   private Author author;
 *
 *   // getters and setters
 * }
 * </pre>
 */
@Entity
@Table(name = "author")
public class Author {

  /**
   * The unique identifier for the author.
   * This field is auto-generated and serves as the primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "author_id")
  private int id;

  /**
   * The name of the author.
   * This field is mandatory and cannot be null.
   */
  @Column(name = "author_name")
  private String name;

  /**
   * The address of the author.
   * This field is embedded and contains address-related information.
   * This field is added later, in module 6 of the linked in learning course.
   * It is marked as @Embedded, indicating that it is a complex type
   * that will be stored in the same table as the author.
   */
  @Embedded
  private Address address;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
