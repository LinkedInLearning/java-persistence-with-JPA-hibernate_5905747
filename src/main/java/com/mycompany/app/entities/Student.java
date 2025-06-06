package com.mycompany.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents a student entity in the system.
 * Inherits common member attributes from the Member class.
 * 
 * This class is mapped to the "student" table in the database.
 * It contains a specific attribute for student code,
 * which is unique to the Student entity.
 * 
 * The @Entity annotation indicates that this class is a JPA entity,
 * and the @Table annotation specifies the table name in the database.
 */
@Entity
@Table(name = "student")
public class Student extends Member {

  @Column(name = "student_code")
  private String studentCode;

  public String getStudentCode() {
    return studentCode;
  }

  public void setStudentCode(String studentCode) {
    this.studentCode = studentCode;
  }
}
