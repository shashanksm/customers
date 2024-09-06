package io.shashanksm.customers.services;

import java.util.List;
import java.util.Optional;

import io.shashanksm.customers.dtos.AccountDto;
import io.shashanksm.customers.dtos.ProfileDto;

public interface ProfileService {
	
	public Optional<ProfileDto> createProfile(ProfileDto profileDto, AccountDto accountDto);
	
	public Optional<ProfileDto> findProfileById(Long id);
	
	public Optional<ProfileDto> findProfileByAccountId(Long accountId);
	
	public List<ProfileDto> findAllProfiles(int pageNumber, int countPerPage);
	
	public Optional<ProfileDto> updateProfile(Long id, ProfileDto profileDto);
	
	public Optional<ProfileDto> deleteProfileById(Long id);
	
}
