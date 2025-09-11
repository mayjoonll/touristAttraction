package com.my.touristAttraction.Service;

import com.my.touristAttraction.dto.CustomUserDetails;
import com.my.touristAttraction.entity.UserEntity;
import com.my.touristAttraction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userData = userRepository.findByUsername(username);

        if (ObjectUtils.isEmpty(userData)) {
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다: " + username);
        }

        return new CustomUserDetails(userData);
    }
}
