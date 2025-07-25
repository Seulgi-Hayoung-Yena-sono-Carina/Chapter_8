package LikelionSummerStudy.blogSummer.service;

import LikelionSummerStudy.blogSummer.domain.User;
import LikelionSummerStudy.blogSummer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
//스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * loadUserByUsername() 메서드: 필수로 구현해야 하는 메서드
     * 사용자 이름(email)으로 사용자의 정보를 가져오는 메서드
     */
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new IllegalArgumentException(email));
    }
}
