package com.apress.mlab.repository;

import com.apress.mlab.domain.ProfileUser;
import org.springframework.data.repository.CrudRepository;

public interface ProfileUserRepository extends CrudRepository<ProfileUser, String> {
	ProfileUser findByUsername(String username);
}
