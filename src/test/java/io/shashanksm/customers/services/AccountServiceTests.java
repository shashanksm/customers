package io.shashanksm.customers.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.shashanksm.customers.dtos.AccountDto;
import io.shashanksm.customers.entities.Account;
import io.shashanksm.customers.repositories.AccountRepository;

@SpringBootTest
class AccountServiceTests {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRepository testRepo;
	
	public static List<Account> TEST_ACCOUNTS = new ArrayList<>();
	
	@BeforeEach
	public void setup() {
		Account account = new Account(555L, "STANDARD_TEST", LocalDateTime.now(), LocalDateTime.now(), "ACTIVE_TEST");
		TEST_ACCOUNTS.add(testRepo.save(account));
	}
	
	@AfterEach
	public void teardown() {
		for(Account account : TEST_ACCOUNTS) {
			testRepo.delete(account);
		}
		
		TEST_ACCOUNTS.clear();
	}
	
	@Test
	void testFindAccountById() {
		
		Long expected = TEST_ACCOUNTS.get(0).getId();
		
		AccountDto actualAccountDto = this.accountService.findAccountById(expected).get();
		
		Long actual = actualAccountDto.getId();
		
		assertEquals(expected, actual);
		
	}

	@Test
	void testFindAllAccounts() {
		
		createTestAccounts(4);
		
		int expected = TEST_ACCOUNTS.size();
		
		int size = TEST_ACCOUNTS.size();
		
		int pageNumber = 0;
		
		List<AccountDto> allAccountsFromService = accountService.findAllAccounts(pageNumber, size);
		
		allAccountsFromService.forEach(System.out::println);
		
		int actual = allAccountsFromService.size();
		
		assertEquals(expected, actual);
		
	}

	private void createTestAccounts(int count) {
		
		for(int i = 0; i<count; i++) {
			TEST_ACCOUNTS.add(testRepo.save(new Account(555L, "STANDARD_TEST", LocalDateTime.now(), LocalDateTime.now(), "ACTIVE_TEST")));
		}
	}

	@Test
	void testCreateAccount() {
		
		assertTrue(true);
	}

	@Test
	void testUpdateAccount() {
		
		LocalDateTime tenYearsAgo = LocalDateTime.now().minusYears(10);
		
		Account account = TEST_ACCOUNTS.get(0);
		
		Long id = account.getId();
		
		account.setCreated(tenYearsAgo);
		
		this.accountService.updateAccount(id, AccountDto.fromEntity(account));
		
		LocalDateTime actual = this.accountService.findAccountById(id).get().getCreated();
		
		LocalDateTime expected = tenYearsAgo;
		
		assertThat(actual)
			.hasHour(expected.getHour())
			.hasYear(expected.getYear())
			.hasMonth(expected.getMonth());
		
	}

	@Test
	void testDeleteAccount() {
		assertTrue(true);
	}

}
