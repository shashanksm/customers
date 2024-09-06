package io.shashanksm.customers.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import io.shashanksm.customers.dtos.AccountDto;
import io.shashanksm.customers.dtos.ProfileDto;
import io.shashanksm.customers.entities.Account;
import io.shashanksm.customers.entities.Profile;
import io.shashanksm.customers.repositories.ProfileRepository;

@SpringJUnitConfig(ProfileServiceTest.Config.class)
class ProfileServiceTest {

	@Configuration
	static class Config {
	}

	@Mock
	private ProfileRepository profileRepository;

	@InjectMocks
	private ProfileService profileService = new StandardJpaProfileService(profileRepository);

	@BeforeEach
	public void arrange() {

	}

	@Test
	void testCreateProfile() {
		Account account = new Account();
		AccountDto accountDto = AccountDto.fromEntity(account);
		Profile profile = new Profile(222L, "", "", "", true, true, account);
		ProfileDto profileDto = ProfileDto.fromEntity(profile);

		when(profileRepository.save(Mockito.any(Profile.class))).thenReturn(profile);
		;

		Optional<ProfileDto> expected = Optional.of(profileDto);

		Optional<ProfileDto> actual = profileService.createProfile(profileDto, accountDto);

		assertEquals(expected, actual);

	}

	@Test
	void testFindProfileById() {

		Profile profile = new Profile(222L, "", "", "", true, true, null);
		ProfileDto profileDto = ProfileDto.fromEntity(profile);

		when(profileRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(profile));
		;

		Optional<ProfileDto> expected = Optional.of(profileDto);

		Optional<ProfileDto> actual = profileService.findProfileById(222L);

		assertEquals(expected, actual);
	}

	@Test
	void testFindProfileByAccountId() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAllProfiles() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateProfile() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteProfileById() {
		fail("Not yet implemented");
	}

}
