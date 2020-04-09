package com.appsdeveloperblogs.ui.contoller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblogs.service.AddressService;
import com.appsdeveloperblogs.service.UserService;
import com.appsdeveloperblogs.service.exception.UserServiceException;
import com.appsdeveloperblogs.share.dto.AddressDto;
import com.appsdeveloperblogs.share.dto.UserDto;
import com.appsdeveloperblogs.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblogs.ui.model.response.AddressesRest;
import com.appsdeveloperblogs.ui.model.response.ErrorMessages;
import com.appsdeveloperblogs.ui.model.response.OperationStatusModel;
import com.appsdeveloperblogs.ui.model.response.RequestOperationName;
import com.appsdeveloperblogs.ui.model.response.RequestOperationStatus;
import com.appsdeveloperblogs.ui.model.response.UserRest;

//import antlr.collections.List;

@RestController
@RequestMapping("users") // http//localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	AddressService addressesService;


	@GetMapping(path="/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public  UserRest getUser(@PathVariable String id) {
		UserRest returnValue = new UserRest();
		UserDto userDto =userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);

		return returnValue;

	}

	@PostMapping(consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserRest returnValue = new UserRest();
		//if(userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		//UserDto  userDto = new UserDto();
		//BeanUtils.copyProperties(userDetails, userDto);
		ModelMapper modelMapper = new ModelMapper();
		//System.out.println(userDetails.getAddresses().get(1).getCountry());
		UserDto  userDto= modelMapper.map(userDetails, UserDto.class);
		//System.out.println("In controller: "+userDto.getEmail());

		UserDto createdUser = userService.createUser(userDto);
		returnValue  = modelMapper.map(createdUser, UserRest.class);
		return returnValue;


	}

	@PutMapping( path ="/{id}", consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest updateUser(@PathVariable String id ,@RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();
		//if(userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		UserDto  userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto updateUser = userService.updateUser(id,userDto);
		BeanUtils.copyProperties(updateUser, returnValue);
		return returnValue;



	}

	@DeleteMapping(path="/{id}",consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		userService.deleteUser(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;


	}
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "0") int limit){

		List<UserRest> returnValue = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page,limit);
		for (UserDto userDto : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}
		return returnValue;


	}
	@GetMapping(path="/{id}/addresses", produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public  List<AddressesRest> getUserAddresses(@PathVariable String id) {
		List<AddressesRest> returnValue = new ArrayList<>();
		List<AddressDto> addressesDto =addressesService.getAddresses(id);
		if(addressesDto!= null && !addressesDto.isEmpty()) {
			Type listType = new TypeToken<List<AddressesRest>>() {}.getType();
			//ModelMapper modelMapper = new ModelMapper();
			returnValue = new ModelMapper().map(addressesDto, listType);
		}
		return returnValue;

	}


}
