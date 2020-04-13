package com.appsdeveloperblogs.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblogs.io.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);// query method
	UserEntity findUserByEmailVerificationToken(String token);

}
