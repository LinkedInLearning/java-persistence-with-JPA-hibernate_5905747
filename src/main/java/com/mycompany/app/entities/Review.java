package com.mycompany.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "review")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id")
  private int id;

  private String comment;

  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book;

  private int rating;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  @Override
  public String toString() {
    return "Review [id=" + id + ", comment=" + comment + ", book=" + book + ", rating=" + rating + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((comment == null) ? 0 : comment.hashCode());
    result = prime * result + ((book == null) ? 0 : book.hashCode());
    result = prime * result + rating;
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
    Review other = (Review) obj;
    if (id != other.id)
      return false;
    if (comment == null) {
      if (other.comment != null)
        return false;
    } else if (!comment.equals(other.comment))
      return false;
    if (book == null) {
      if (other.book != null)
        return false;
    } else if (!book.equals(other.book))
      return false;
    if (rating != other.rating)
      return false;
    return true;
  }

}
