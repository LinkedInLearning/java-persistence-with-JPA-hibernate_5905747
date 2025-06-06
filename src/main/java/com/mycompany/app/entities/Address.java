package com.mycompany.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Represents an address with street, city, and postal code.
 * 
 * This class is marked as @Embeddable, which means it can be used as a
 * component of another entity. It does not have its own identity and is
 * typically used to encapsulate address-related fields within an entity.
 * 
 * Example usage:
 * <pre>
 * @Entity
 * public class User {
 *     @Id
 *     private Long id;
 *     private String name;
 *    
 *     @Embedded
 *     private Address address;
 *
 *     // getters and setters
 *     public Address getAddress() {
 *         return address;
 *     }
 *     public void setAddress(Address address) { 
 *        this.address = address;
 *     }
 * </pre>
 */
@Embeddable
public class Address {

  /**
   * The street of the address.
   * This field is mandatory and cannot be null.
   */
  private String street;
  
  /**
   * The city of the address.
   * This field is mandatory and cannot be null.
   */
  private String city;

  /**
   * The postal code of the address.
   * This field is optional and can be null.
   */
  @Column(name = "postal_code")
  private String postalCode;

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
}
