package io.shashanksm.customers.entities;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile_t")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "primary_contact")
	private String primaryContact;

	@Column(name = "email")
	private String email;

	@Column(name = "contact_verified")
	private Boolean contactVerified;

	@Column(name = "email_verified")
	private Boolean emailVerified;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account", referencedColumnName = "id")
	private Account account;

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
	
	

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Profile(Long id, String name, String primaryContact, String email, Boolean contactVerified,
			Boolean emailVerified, Account account) {
		super();
		this.id = id;
		this.name = name;
		this.primaryContact = primaryContact;
		this.email = email;
		this.contactVerified = contactVerified;
		this.emailVerified = emailVerified;
		this.account = account;
	}

	public Profile() {
		super();
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
		Profile other = (Profile) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(primaryContact, other.primaryContact);
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", name=" + name + ", primaryContact=" + primaryContact + ", email=" + email
				+ ", contactVerified=" + contactVerified + ", emailVerified=" + emailVerified + "]";
	}

}
