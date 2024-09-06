package io.shashanksm.customers.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.shashanksm.customers.dtos.AddressDto;
import io.shashanksm.customers.entities.Address;
import io.shashanksm.customers.repositories.AddressRepository;

@Service
public class StandardJpaAddressService implements AddressService {

	private static final Logger logger = LoggerFactory.getLogger(StandardJpaAddressService.class);

	private final AddressRepository addressRepository;

	public StandardJpaAddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public Optional<AddressDto> createAddress(AddressDto addressDto) {

		logger.info("creating address for account with id : " +addressDto.getAccount().getId());

		Optional<AddressDto> ret = Optional.empty();

		this.addressRepository.save(addressDto.toEntity());

		logger.info("address created with id : for account with id : " + addressDto.getAccount().getId());
		return ret;
	}

	@Override
	public Optional<AddressDto> findAddressById(Long id) {
		logger.info("finding address with id : " + id);
		return this.addressRepository.findById(id).map(AddressDto::fromEntity);
	}

	@Override
	public List<AddressDto> findAddressByAccountId(Long accountId) {
		logger.info("finding address with accountId : " + accountId);
		return this.addressRepository.findByAccountId(accountId)
				.stream()
				.map(AddressDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public List<AddressDto> findAllAddresses(int pageNumber, int countPerPage) {
		logger.info("finding all addresses for page: " + pageNumber + " and size :" + countPerPage);

		List<AddressDto> ret = this.addressRepository.findAll(PageRequest.of(pageNumber, countPerPage)).stream()
				.map(AddressDto::fromEntity).collect(Collectors.toList());

		return ret;
	}

	@Override
	public Optional<AddressDto> updateAddress(Long id, AddressDto addressDto) {

		Optional<AddressDto> ret = Optional.empty();

		if (this.addressRepository.existsById(id)) {

			Optional<Address> foundAddressOpt = this.addressRepository.findById(id);

			Address foundAddress = foundAddressOpt.get();

			foundAddress.setAddressLine1(addressDto.getAddressLine1());
			foundAddress.setAddressLine2(addressDto.getAddressLine2());
			foundAddress.setCity(addressDto.getCity());
			foundAddress.setName(addressDto.getName());
			foundAddress.setPostalCode(addressDto.getPostalCode());
			foundAddress.setRegion(addressDto.getRegion());
			foundAddress.setUnit(addressDto.getUnit());

			this.addressRepository.save(foundAddress);

			logger.info("updated address with id : " + id);

			ret = foundAddressOpt.map(AddressDto::fromEntity);

		} else {
			logger.warn("address with id : " + id + " does not exist");
			logger.info("nothing updated");
		}

		return ret;
	}

	@Override
	public Optional<AddressDto> deleteAddress(Long id) {
		Optional<AddressDto> ret = Optional.empty();

		if (this.addressRepository.existsById(id)) {

			Optional<Address> foundAddressOpt = this.addressRepository.findById(id);

			Address foundAddress = foundAddressOpt.get();

			this.addressRepository.delete(foundAddress);

			logger.info("deleted address with id : " + id);

			ret = foundAddressOpt.map(AddressDto::fromEntity);

		} else {
			logger.warn("address with id : " + id + " does not exist");
			logger.info("nothing updated");
		}

		return ret;
	}

}
