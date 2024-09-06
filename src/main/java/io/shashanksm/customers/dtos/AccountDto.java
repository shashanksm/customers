package io.shashanksm.customers.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

import io.shashanksm.customers.entities.Account;

public class AccountDto {

	private Long id;

	private String type;

	private LocalDateTime created;

	private LocalDateTime lastLogin;

	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AccountDto(Long id, String type, LocalDateTime created, LocalDateTime lastLogin, String status) {
		super();
		this.id = id;
		this.type = type;
		this.created = created;
		this.lastLogin = lastLogin;
		this.status = status;
	}

	public AccountDto() {
		super();
	}

	@Override
	public String toString() {
		return "AccountDto [id=" + id + ", type=" + type + ", created=" + created + ", lastLogin=" + lastLogin
				+ ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, status, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountDto other = (AccountDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(status, other.status) && Objects.equals(type, other.type);
	}

	public Account toEntity() {
		Account account = new Account();

		account.setCreated(created);
		if (this.id != null)
			account.setId(id);
		account.setLastLogin(lastLogin);
		account.setStatus(status);
		account.setType(type);

		return account;
	}

	public static AccountDto fromEntity(Account account) {
		AccountDto accountDto = new AccountDto();

		accountDto.id = account.getId();
		accountDto.created = account.getCreated();
		accountDto.lastLogin = account.getLastLogin();
		accountDto.status = account.getStatus();
		accountDto.type = account.getType();
		return accountDto;
	}
}
