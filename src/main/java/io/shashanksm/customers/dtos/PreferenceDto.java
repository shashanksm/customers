package io.shashanksm.customers.dtos;


import io.shashanksm.customers.entities.Preference;

public record PreferenceDto(Long id, String pKey, String pValue, AccountDto accountDto) {
	
	public Preference toEntity() {
		return new Preference(id, pKey, pValue, accountDto.toEntity());
	}
	
	public static PreferenceDto fromEntity(Preference preference) {
		return new PreferenceDto(
				preference.getId(), 
				preference.getKey(), 
				preference.getValue(), 
				AccountDto.fromEntity(preference.getAccount())
				);
	}
}
