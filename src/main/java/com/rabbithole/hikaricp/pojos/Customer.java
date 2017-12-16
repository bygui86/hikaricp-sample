package com.rabbithole.hikaricp.pojos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Customer {

	private int id;
	private String name;
	private String email;
	private Date date;
	
	// constructor, getters, setters, toString, equals and hashcode
	
}
