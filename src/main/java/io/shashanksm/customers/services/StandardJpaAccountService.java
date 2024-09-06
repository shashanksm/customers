package io.shashanksm.customers.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.shashanksm.customers.dtos.AccountDto;
import io.shashanksm.customers.entities.Account;
import io.shashanksm.customers.repositories.AccountRepository;

@Service
public class StandardJpaAccountService implements AccountService {
	
	private static final Logger logger = LoggerFactory.getLogger(StandardJpaAccountService.class);

	private AccountRepository accountRepository;
	
	
	public StandardJpaAccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Optional<AccountDto> findAccountById(Long id) {
		
		logger.trace("findAccountById called for "+id);
		
		Optional<Account> foundAccount = this.accountRepository.findById(id);
		
		if(foundAccount.isPresent()) {
			
			logger.trace("retrieved successfully for "+id);
			return foundAccount.map(AccountDto::fromEntity);
			
		}else {
			
			logger.warn("account with id "+id+" was not found");
			return Optional.empty();
		}
	}

	@Override
	public List<AccountDto> findAllAccounts(int pageNumber, int size) {
		
		logger.trace("findAllAccounts requested with page-number : "+pageNumber+" and size : "+size);
		
		List<AccountDto> foundAccounts;
		
		Page<Account> foundPage = this.accountRepository.findAll(PageRequest.of(pageNumber, size));
		
		foundAccounts = foundPage.toList()
				.stream()
				.map(AccountDto::fromEntity)
				.toList();
		
		logger.trace("all accounts retrieved successfully for pageNumber : "+pageNumber+" and size : "+size);
		return foundAccounts;
	}

	@Override
	public Optional<AccountDto> createAccount(AccountDto accountDto) {
		
		logger.trace("createAccount called for "+accountDto.toString());
		
		//TODO add validation here, if necessary
		
		Account createdAccount = this.accountRepository.save(accountDto.toEntity());

		
		logger.trace("account successfully created : "+createdAccount.toString());
		
		return Optional.of(AccountDto.fromEntity(createdAccount));
	}

	@Override
	public Optional<AccountDto> updateAccount(Long id, AccountDto accountDto) {
		// TODO Auto-generated method stub
		
		if(this.accountRepository.existsById(id)) {
			
			logger.trace("account with id "+id +"exists");
			
			if(accountDto.getId() != id) {
				logger.warn("requested id and accountDto.id appear to be different");
				accountDto.setId(id);
			}
			
			//use saveAndFlush(..) only if there are triggers in db that react to creating rows 
			//otherwise, let ORM (hibernate, in this case) handle persisting transaction
			
			//this.accountRepository.saveAndFlush(accountDto.toEntity());
			Account updatedAccount = this.accountRepository.save(accountDto.toEntity());
			
			logger.trace("account successfully updated");
			
			return Optional.of(AccountDto.fromEntity(updatedAccount));
			
		}else {
			logger.warn("account with id "+id +" was not found");
			logger.warn("nothing updated");
			return Optional.empty();
		}
		
		
	}

	@Override
	public Optional<AccountDto> deleteAccount(Long id) {
		
		Optional<Account> deleteAccount = this.accountRepository.findById(id);
		
		if(deleteAccount.isPresent()) {
			
			logger.trace("account with id "+id+" found");
			this.accountRepository.delete(deleteAccount.get());
			logger.trace("account successfully deleted");
			return deleteAccount.map(AccountDto::fromEntity);
		}
		
		return Optional.empty();
	}

}
