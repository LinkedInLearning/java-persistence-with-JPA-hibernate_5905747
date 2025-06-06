package com.mycompany.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Represents a student entity in the system.
 * Inherits common member attributes from the Member class.
 * 
 * This class is mapped to the "student" table in the database.
 * It contains a specific attribute for student code,
 * which is unique to the Student entity.
 * 
 * The @Entity annotation indicates that this class is a JPA entity,
 * and the @DiscriminatorColumn annotation specifies the column used
 * to distinguish between different member types in the single table inheritance
 * strategy.
 * 
 * This class is part of a single table inheritance strategy,
 * where all member types (like Teacher2 and Student2) are stored in a single
 * table.
 * The discriminator column helps to identify the type of each member record.
 * 
 * This class extends the Member2 class, inheriting its properties and behavior.
 *
 */
@Entity
@DiscriminatorValue("student")
public class Student2 extends Member2 {

  @Column(name = "student_code")
  private String studentCode;

  public String getStudentCode() {
    return studentCode;
  }

  public void setStudentCode(String studentCode) {
    this.studentCode = studentCode;
  }
}
