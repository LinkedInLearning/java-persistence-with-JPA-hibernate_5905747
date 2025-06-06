package com.mycompany.app.entities;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;

/**
 * Represents a member entity in the system.
 * This class serves as a base class for different types of members,
 * such as teachers and students, using the Single Table Inheritance strategy.
 * 
 * This class is mapped to the "member" table in the database,
 * and it uses a discriminator column to differentiate between member types.
 * The discriminator column is named "member_type".
 * 
 * The @Entity annotation indicates that this class is a JPA entity,
 * and the @Table annotation specifies the table name in the database.
 * The @DiscriminatorColumn annotation specifies the column used to distinguish
 * between different member types in the single table inheritance strategy.
 */
@Entity
@Table(name = "member")
@Inheritance(strategy = jakarta.persistence.InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type")
public class Member2 {

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
