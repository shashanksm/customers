package io.shashanksm.customers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.shashanksm.customers.entities.Account;
import io.shashanksm.customers.repositories.AccountRepository;

@SpringBootTest
class CustomersApplicationTests {
	
	@Autowired
	private AccountRepository accountRepository;

	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void shouldRetrieveAccount() {
		
		Account account = accountRepository.findById(99L).get();
		
		System.out.println(account.toString());
		
	}
	
	

}
