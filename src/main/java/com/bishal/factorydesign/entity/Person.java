package com.bishal.factorydesign.entity;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public abstract class Person {

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

}
