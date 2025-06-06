package com.mycompany.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Represents a teacher entity in the system.
 * Inherits common member attributes from the Member class.
 * 
 * This class is mapped to the "teacher" table in the database.
 * It contains a specific attribute for teacher code,
 * which is unique to the Teacher entity.
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
 */
@Entity
@DiscriminatorValue("teacher")
public class Teacher2 extends Member2 {

  @Column(name = "teacher_code")
  private String teacherCode;

  public String getTeacherCode() {
    return teacherCode;
  }

  public void setTeacherCode(String teacherCode) {
    this.teacherCode = teacherCode;
  }
}
