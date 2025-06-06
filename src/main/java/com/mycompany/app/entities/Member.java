package com.mycompany.app.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Represents a common member entity in the system.
 * This class is intended to be extended by other entities like Teacher and Student.
 * It contains common attributes such as id and name.
 * 
 * The @MappedSuperclass annotation indicates that this class is not an entity itself,
 * but its attributes will be inherited by subclasses.
 * This allows for code reuse and a cleaner design.
 * 
 * Subclasses can define their own entity-specific attributes and behaviors.
 */
@MappedSuperclass
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

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
}
