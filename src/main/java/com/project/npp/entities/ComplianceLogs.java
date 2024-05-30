package com.project.npp.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="compliance_logs")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ComplianceLogs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer logId;
	@ManyToOne
	private PortRequest portRequest;
	private boolean checkPassed;
	private String notes;
	private LocalDate checkDate;
	

}
