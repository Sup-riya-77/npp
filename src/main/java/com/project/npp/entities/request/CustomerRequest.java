package com.project.npp.entities.request;
import com.project.npp.entities.Status;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String name;
	private String email;
	private Long  phoneNumber;
	private Integer currentOperatorId;
	private Integer newOperatorId;
	private Status status;

}
