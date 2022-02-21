package dev.wcs.tutoring.fruits.persistence;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;  

@Entity
@Cacheable
public class Fruit extends PanacheEntity {

	 @Column(length = 40, unique = true)
	 public String name;

}