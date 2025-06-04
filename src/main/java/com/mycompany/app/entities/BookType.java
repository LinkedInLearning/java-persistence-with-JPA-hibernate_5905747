/**
 * This file is part of MyCompany's Book Management System.
 * It defines the BookType entity which represents a type of book in the system.
 * The BookType entity is identified by a composite key consisting of typeCode and typeSubCode.
 * It includes fields for the type's code, subcode, and name.
 * The entity is mapped to the "book_type" table in the database.
 */
package com.mycompany.app.entities;

import com.mycompany.app.entities.keys.BookTypeKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;


@Entity
@Table(name = "book_type")
@IdClass(BookTypeKey.class)
// The BookTypeKey class should implement Serializable and define the composite key fields
public class BookType {
  
  @Id
  @Column(name = "type_code", nullable = false)
  private String code;

  @Id
  @Column(name = "type_subcode", nullable = false)
  private String subCode;

  @Column(name = "type_name")
  private String name;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getSubCode() {
    return subCode;
  }

  public void setSubCode(String subCode) {
    this.subCode = subCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "BookType [code=" + code + ", subCode=" + subCode + ", name=" + name + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null) ? 0 : code.hashCode());
    result = prime * result + ((subCode == null) ? 0 : subCode.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BookType other = (BookType) obj;
    if (code == null) {
      if (other.code != null)
        return false;
    } else if (!code.equals(other.code))
      return false;
    if (subCode == null) {
      if (other.subCode != null)
        return false;
    } else if (!subCode.equals(other.subCode))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }
}
