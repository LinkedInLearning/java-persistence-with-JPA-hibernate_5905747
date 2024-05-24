package com.mycompany.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

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
