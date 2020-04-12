package com.appsdeveloperblogs.service.imple;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblogs.io.entity.AddressEntity;
import com.appsdeveloperblogs.io.entity.UserEntity;
import com.appsdeveloperblogs.repositories.AddressRepository;
import com.appsdeveloperblogs.repositories.UserRepository;
import com.appsdeveloperblogs.service.AddressService;
import com.appsdeveloperblogs.share.dto.AddressDto;

@Service
public class AddressServiceImple implements AddressService {
	@Autowired
	UserRepository UserRepository;
	@Autowired
	AddressRepository addressRepository;
	

	@Override
	public List<AddressDto> getAddresses(String userId) {
		List<AddressDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = UserRepository.findByUserId(userId);
		if (userEntity == null)
			return returnValue;
		Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);
		for (AddressEntity addressEntity:addresses) {
			returnValue.add(modelMapper.map(addressEntity, AddressDto.class));
		}
		System.out.println(returnValue);
		return returnValue;
		
	}


	@Override
	public AddressDto getAddress(String addressId) {
		AddressDto returnValue = null;
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		if(addressEntity!=null)
			returnValue = new ModelMapper().map(addressEntity, AddressDto.class);
		
		return returnValue;
	}

}
