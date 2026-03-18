package com.ecommerce.ecommerce_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String image;
  private String name;
  private int priceCents;
  private double stars;
  private int ratingCount;
  private String type;
  private String sizeChartLink;
  private String instructionsLink;
  private String warrantyLink;

  // Constructors
  public Product() {
  }

  public Product(String image, String name, int priceCents, double stars, int ratingCount,
      String type, String sizeChartLink, String instructionsLink, String warrantyLink) {
    this.image = image;
    this.name = name;
    this.priceCents = priceCents;
    this.stars = stars;
    this.ratingCount = ratingCount;
    this.type = type;
    this.sizeChartLink = sizeChartLink;
    this.instructionsLink = instructionsLink;
    this.warrantyLink = warrantyLink;
  }

  // Getters and setters...
  public Long getId() {
    return id;
  }

  public String getImage() {
    return image;
  }

  public String getName() {
    return name;
  }

  public int getPriceCents() {
    return priceCents;
  }

  public double getStars() {
    return stars;
  }

  public int getRatingCount() {
    return ratingCount;
  }

  public String getType() {
    return type;
  }

  public String getSizeChartLink() {
    return sizeChartLink;
  }

  public String getInstructionsLink() {
    return instructionsLink;
  }

  public String getWarrantyLink() {
    return warrantyLink;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPriceCents(int priceCents) {
    this.priceCents = priceCents;
  }

  public void setStars(double stars) {
    this.stars = stars;
  }

  public void setRatingCount(int ratingCount) {
    this.ratingCount = ratingCount;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setSizeChartLink(String sizeChartLink) {
    this.sizeChartLink = sizeChartLink;
  }

  public void setInstructionsLink(String instructionsLink) {
    this.instructionsLink = instructionsLink;
  }

  public void setWarrantyLink(String warrantyLink) {
    this.warrantyLink = warrantyLink;
  }
}
