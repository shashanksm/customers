package io.shashanksm.customers.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.shashanksm.customers.dtos.AccountDto;
import io.shashanksm.customers.dtos.ProfileDto;
import io.shashanksm.customers.entities.Profile;
import io.shashanksm.customers.repositories.ProfileRepository;

@Service
public class StandardJpaProfileService implements ProfileService{
	
	private static final Logger logger = LoggerFactory.getLogger(StandardJpaProfileService.class);

	private ProfileRepository profileRepository;
		
	public StandardJpaProfileService(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}
	
	@Override
	public Optional<ProfileDto> createProfile(ProfileDto profileDto, AccountDto accountDto) {
		
		//TODO add instrumentation
		
		logger.info("creating profile for account with id : "+accountDto.getId());
		
		Profile profile = this.profileRepository.save(profileDto.toEntity(accountDto));
		
		ProfileDto createdProfile = ProfileDto.fromEntity(profile);
		
		logger.info("created profile for account with id : "+accountDto.getId());
		
		return Optional.of(createdProfile);
		
	}

	@Override
	public Optional<ProfileDto> findProfileById(Long id) {
		
		logger.info("finding profile with id : "+id);
		
		return this.profileRepository.findById(id)
				.map(ProfileDto::fromEntity);
	}

	@Override
	public Optional<ProfileDto> findProfileByAccountId(Long accountId) {
		logger.info("finding profile with accountId : "+accountId);
		return this.profileRepository.findByAccountId(accountId)
				.map(ProfileDto::fromEntity);
		
	}

	@Override
	public List<ProfileDto> findAllProfiles(int pageNumber, int countPerPage) {
		
		logger.info("finding all profiles for page: "+pageNumber+ " and size :"+countPerPage);
		
		PageRequest pageRequest = PageRequest.of(pageNumber, countPerPage);
		
		List<ProfileDto> ret = this.profileRepository.findAll(pageRequest)
				.toList()
				.stream()
				.map(ProfileDto::fromEntity)
				.collect(Collectors.toList());
		
		return ret;
	}

	
	@Override
	public Optional<ProfileDto> updateProfile(Long id, ProfileDto profileDto) {
		Optional<ProfileDto> ret = Optional.empty();
		
		//check if profile with that id exists
		if(this.profileRepository.existsById(id)) {
			
			Profile foundProfile = this.profileRepository.findById(id).get();
			
			foundProfile.setContactVerified(profileDto.getContactVerified());
			foundProfile.setEmail(profileDto.getEmail());
			foundProfile.setEmailVerified(profileDto.getEmailVerified());
			foundProfile.setName(profileDto.getName());
			foundProfile.setPrimaryContact(profileDto.getPrimaryContact());
			
			this.profileRepository.save(foundProfile);
			
		}else {
			logger.warn("profile with id : "+id+" does not exist");
			logger.info("nothing updated");
		}
		
		
		return ret;
	}

	@Override
	public Optional<ProfileDto> deleteProfileById(Long id) {
		logger.info("deleting profile with id : "+id);
		if(this.profileRepository.existsById(id)) {
			Optional<Profile> foundProfileOptional = this.profileRepository.findById(id);
			
			Profile foundProfile = foundProfileOptional.get();
			
			this.profileRepository.delete(foundProfile);
			
			return foundProfileOptional.map(ProfileDto::fromEntity);
		}else {
			logger.warn("profile with id : "+id+" does not exist");
			logger.info("nothing updated");
			return Optional.empty();
		}
	}

}
