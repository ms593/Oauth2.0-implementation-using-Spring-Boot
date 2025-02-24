package in.mohitit.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.mohitit.entity.Customer;
import in.mohitit.repo.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService {
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Autowired
	private CustomerRepository customerRepo;
	
	
	public boolean saveCustomer(Customer c) {
		
		
		String encodePwd = pwdEncoder.encode(c.getPwd());
		c.setPwd(encodePwd);
		
		Customer saveCustomer = customerRepo.save(c);
		
		return saveCustomer.getCid()!=null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Customer c = customerRepo.findByEmail(email);
		
		return new User(c.getEmail(),c.getPwd(),Collections.emptyList());
		
		
	}
	
}
