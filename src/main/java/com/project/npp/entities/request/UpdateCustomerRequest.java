package com.project.npp.entities.request;


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
public class UpdateCustomerRequest {
	private Integer customerId;
	private String name;
	private String email;
	private Long  phoneNumber;
	private Integer currentOperatorId;
	private Integer newOperatorId;

}
