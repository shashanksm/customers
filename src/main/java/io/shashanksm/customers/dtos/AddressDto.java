package io.shashanksm.customers.dtos;

import java.util.Objects;

import io.shashanksm.customers.entities.Address;

public class AddressDto {

	private Long id;
	private String name;
	private String unit;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String region;
	private String postalCode;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String refion) {
		this.region = refion;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, city, id, postalCode, region, unit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressDto other = (AddressDto) obj;
		return Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)
				&& Objects.equals(city, other.city) && Objects.equals(id, other.id)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(region, other.region)
				&& Objects.equals(unit, other.unit);
	}

	public AddressDto(Long id, String name, String unit, String addressLine1, String addressLine2, String city,
			String region, String postalCode, AccountDto account) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.region = region;
		this.postalCode = postalCode;
		this.account = account;
	}

	public AddressDto() {
		super();
	}

	public Address toEntity() {
		Address address = new Address();
		
		if(id != null)
			address.setId(id);
		
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setCity(city);
		address.setId(id);
		address.setName(name);
		address.setRegion(region);
		address.setPostalCode(postalCode);
		address.setUnit(unit);
		
		address.setAccount(account.toEntity());
		
		
		return address;
	}
	
	public static AddressDto fromEntity(Address address) {
		return new AddressDto(
				address.getId(),
				address.getName(),
				address.getUnit(),
				address.getAddressLine1(),
				address.getAddressLine2(),
				address.getCity(),
				address.getRegion(),
				address.getPostalCode(), 
				AccountDto.fromEntity(address.getAccount())
			);
	}

	@Override
	public String toString() {
		return "AddressDto [id=" + id + ", name=" + name + ", unit=" + unit + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", city=" + city + ", region=" + region + ", postalCode="
				+ postalCode + ", account=" + account + "]";
	}
	
	
}
