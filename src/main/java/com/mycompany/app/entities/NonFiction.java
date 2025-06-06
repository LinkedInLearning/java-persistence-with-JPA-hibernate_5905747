package com.mycompany.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Fiction class representing a genre of books that includes a setting.
 * It extends the Genre class to inherit common properties.
 * 
 * This class is annotated with JPA annotations to map it to a database table.
 * 
 * @Entity annotation indicates that this class is a JPA entity.
 * @Table annotation specifies the name of the table in the database.
 */
@Entity
@Table(name = "non_fiction")
public class NonFiction extends Genre {

  private String topic;

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }
}
