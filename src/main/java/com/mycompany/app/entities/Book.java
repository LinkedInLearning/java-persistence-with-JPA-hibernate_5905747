package com.mycompany.app.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id")
  private int id;

  @Column(name = "book_name")
  private String name;

  private String isbn;

  @OneToOne
  @JoinColumn(name = "author_id")
  private Author author;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  private List<Review> reviews;

  private BigDecimal price;

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

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Book [id=" + id + ", name=" + name + ", isbn=" + isbn + "]";
  }
}
