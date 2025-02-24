package in.mohitit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mohitit.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	public Customer findByEmail(String email);
}
