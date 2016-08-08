package com.bridgelabz.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="appointment")
public class Appointment {
	
	@Id
	int appointment_id;

	@Column
	@ManyToOne
	private ClientAppts clientAppt;
}
