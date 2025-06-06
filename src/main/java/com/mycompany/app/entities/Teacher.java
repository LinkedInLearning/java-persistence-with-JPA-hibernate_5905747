package com.mycompany.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents a teacher entity in the system.
 * Inherits common member attributes from the Member class.
 * 
 * This class is mapped to the "teacher" table in the database.
 * It contains a specific attribute for teacher code,
 * which is unique to the Teacher entity.
 * 
 * The @Entity annotation indicates that this class is a JPA entity,
 * and the @Table annotation specifies the table name in the database.
 */
@Entity
@Table(name = "teacher")
public class Teacher extends Member {

  @Column(name = "teacher_code")
  private String teacherCode;

  public String getTeacherCode() {
    return teacherCode;
  }

  public void setTeacherCode(String teacherCode) {
    this.teacherCode = teacherCode;
  }
}
