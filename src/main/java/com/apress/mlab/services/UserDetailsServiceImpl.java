package com.apress.mlab.services;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.apress.mlab.repository.ProfileUserRepository;
import com.apress.mlab.domain.ProfileUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ProfileUserRepository profileUserRepository;

	public UserDetailsServiceImpl(ProfileUserRepository profileUserRepository) {
		this.profileUserRepository = profileUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ProfileUser user = profileUserRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(user.getUsername(), user.getPassword(), emptyList());
	}
}
