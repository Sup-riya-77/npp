sspackage com.project.npp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.npp.entities.Customer;
import com.project.npp.entities.Status;
import com.project.npp.exceptions.CustomerNotFoundException;
import com.project.npp.repositories.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repo;
	@Override
	public Customer addCustomer(Customer customer) {
		customer.setStatus(Status.PENDING);
		Customer cust= repo.save(customer);
		return cust;
	}

	@Override
	public Customer getCustomerById(Integer id) throws CustomerNotFoundException{
		Optional<Customer> cust=repo.findById(id);
		if(cust.isPresent())
		{
			return cust.get();
		}
	 throw new CustomerNotFoundException("Customer with Id "+id+" Not Found");
	}

	@Override
	public Customer updateCustomer(Customer customer)throws CustomerNotFoundException {
		Optional<Customer> c=repo.findById(customer.getCustomerId());
		if(c.isPresent())
		{
	        Customer cust= repo.save(customer);
	        return cust;
		}
		 throw new CustomerNotFoundException("Customer with Id "+customer.getCustomerId()+" Not Found");
	}

	@Override
	public String deleteCustomerById(Integer id) throws CustomerNotFoundException{
		Optional<Customer> cust=repo.findById(id);
		if(cust.isPresent())
		{
			repo.deleteById(id);
			return "Deleted Successfully!!";
		}
		 throw new CustomerNotFoundException("Customer with Id "+id+" Not Found");
	}

}
