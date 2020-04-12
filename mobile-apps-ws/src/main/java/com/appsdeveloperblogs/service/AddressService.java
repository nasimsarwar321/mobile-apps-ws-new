package com.appsdeveloperblogs.service;

import java.util.List;

import com.appsdeveloperblogs.share.dto.AddressDto;

public interface AddressService {

	List<AddressDto> getAddresses(String userId);
	AddressDto getAddress(String addressId);

}
