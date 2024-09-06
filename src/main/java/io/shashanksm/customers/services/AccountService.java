package io.shashanksm.customers.services;

import java.util.List;
import java.util.Optional;

import io.shashanksm.customers.dtos.AccountDto;

public interface AccountService {

	public Optional<AccountDto> findAccountById(Long id);

	public List<AccountDto> findAllAccounts(int page, int size);

	public Optional<AccountDto> createAccount(AccountDto accountDto);

	public Optional<AccountDto> updateAccount(Long id, AccountDto accountDto);

	public Optional<AccountDto> deleteAccount(Long id);

}
