package com.mycompany.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name = "grp")
public class Group {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "group_id", nullable = false)
  private int id;

  @Column(name = "group_name")
  private String name;

  /*
   * This is a many-to-many relationship between Group and User entities.
   * A group can have many users, and a user can belong to many groups.
   * The @JoinTable annotation specifies the join table that holds the
   * relationship.
   * 
   * The join table is named "user_groups", with "group_id" as the join column
   * and "user_id" as the inverse join column.
   * 
   * The @JoinColumn annotation specifies the foreign key columns in the join
   * table.
   * 
   * The joinColumns attribute specifies the column in the join table that
   * refers to the Group entity primary key, while the inverseJoinColumns
   * attribute specifies the column in the join table that refers to the
   * User entity primary key.
   * 
   * The @ManyToMany annotation indicates that this is a many-to-many
   * relationship.
   * The users field will hold the list of users that belong to this group.
   */
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_group", joinColumns = @jakarta.persistence.JoinColumn(name = "group_id"), inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "user_id"))
  private List<User> users;

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

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Group [id=" + id + ", name=" + name + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
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
    Group other = (Group) obj;
    if (id != other.id)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }
}
