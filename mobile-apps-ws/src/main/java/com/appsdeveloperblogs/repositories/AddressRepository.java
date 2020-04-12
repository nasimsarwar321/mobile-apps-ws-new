package com.appsdeveloperblogs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblogs.io.entity.AddressEntity;
import com.appsdeveloperblogs.io.entity.UserEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
   List<AddressEntity> findAllByUserDetails(UserEntity userEntity);
   AddressEntity findByAddressId(String addressId);
}
