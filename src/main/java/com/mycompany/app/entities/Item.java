package com.mycompany.app.entities;

import com.mycompany.app.entities.keys.ItemKey;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

  @EmbeddedId
  private ItemKey id;
  
  @Column(name = "item_name")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ItemKey getId() {
    return id;
  }

  public void setId(ItemKey id) {
    this.id = id;
  }
}
