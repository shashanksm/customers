package io.shashanksm.customers.dtos;

import java.util.Objects;

import io.shashanksm.customers.entities.Profile;

public class ProfileDto {
	
	private Long id;
	
	private String name;
	
	private String primaryContact;
	
	private String email;
	
	private Boolean contactVerified;
	
	private Boolean emailVerified;
	
	private AccountDto account;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getContactVerified() {
		return contactVerified;
	}

	public void setContactVerified(Boolean contactVerified) {
		this.contactVerified = contactVerified;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, primaryContact);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfileDto other = (ProfileDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(primaryContact, other.primaryContact);
	}

	public ProfileDto(Long id, String name, String primaryContact, String email, Boolean contactVerified,
			Boolean emailVerified, AccountDto account) {
		super();
		this.id = id;
		this.name = name;
		this.primaryContact = primaryContact;
		this.email = email;
		this.contactVerified = contactVerified;
		this.emailVerified = emailVerified;
		
		this.account = account;
	}

	public ProfileDto() {
		super();
	}
	
	public Profile toEntity(AccountDto accountDto) {
		Profile profile = new Profile();
		
		profile.setId(id);
		profile.setName(name);
		profile.setEmail(email);
		profile.setPrimaryContact(primaryContact);
		profile.setContactVerified(contactVerified);
		profile.setEmailVerified(emailVerified);
		profile.setAccount(accountDto.toEntity());
		
		return profile;
	}
	
	public static ProfileDto fromEntity(Profile profile) {
		
		return new ProfileDto(
				
				profile.getId(), 
				profile.getName(), 
				profile.getPrimaryContact(), 
				profile.getEmail(), 
				profile.getContactVerified(), 
				profile.getEmailVerified(),
				AccountDto.fromEntity(profile.getAccount())
			);
	}
	
}
