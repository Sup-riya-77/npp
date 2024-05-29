package com.project.npp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "operators")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Operator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer operator_id;
	
	private String operator_name;
	
	private String contact_info;
	
	
	

}
