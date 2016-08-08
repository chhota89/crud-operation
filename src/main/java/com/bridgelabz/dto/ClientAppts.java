package com.bridgelabz.dto;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class ClientAppts {

	
	@Column
	String name;
	@Column
	int age;
	
	@Column
	@OneToMany(mappedBy = "clientAppt", fetch = FetchType.LAZY, targetEntity = Appointment.class)
	private Collection<Date> appointments;
}
