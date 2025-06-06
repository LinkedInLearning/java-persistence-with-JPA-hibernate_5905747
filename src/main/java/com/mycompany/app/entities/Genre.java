package com.mycompany.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;

/**
 * Genre class representing a base entity for different types of book genres.
 * It serves as a superclass for specific genre types like Fiction and NonFiction.
 * 
 * This class is annotated with JPA annotations to map it to a database table.
 * 
 * @Entity annotation indicates that this class is a JPA entity.
 * @Table annotation specifies the name of the table in the database.
 * @Inheritance annotation defines the inheritance strategy for this entity.
 * 
 * @InheritanceType.JOINED indicates that each subclass will have its own table,
 * and the base class will have a separate table as well.
 */
@Entity
@Table(name = "genre")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Genre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String code;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
