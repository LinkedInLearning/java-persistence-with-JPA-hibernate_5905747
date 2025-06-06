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
@Table(name = "fiction")
public class Fiction extends Genre {

  private String setting;

  public String getSetting() {
    return setting;
  }

  public void setSetting(String setting) {
    this.setting = setting;
  }
}
