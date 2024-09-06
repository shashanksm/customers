package io.shashanksm.customers.services;

import java.util.List;
import java.util.Optional;

import io.shashanksm.customers.dtos.AddressDto;

public interface AddressService {
	
	public Optional<AddressDto> createAddress(AddressDto addressDto);
	
	public Optional<AddressDto> findAddressById(Long id);
	
	public List<AddressDto> findAddressByAccountId(Long accountId);
	
	public List<AddressDto> findAllAddresses(int pageNumber, int countPerPage);
	
	public Optional<AddressDto> updateAddress(Long id, AddressDto addressDto);
	
	public Optional<AddressDto> deleteAddress(Long id);
	
}
