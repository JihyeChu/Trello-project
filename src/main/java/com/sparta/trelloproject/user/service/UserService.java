package com.sparta.trelloproject.user.service;

import com.sparta.trelloproject.user.dto.AuthRequestDto;
import com.sparta.trelloproject.user.dto.PasswordRequestDto;
import com.sparta.trelloproject.user.dto.ProfileRequestDto;
import com.sparta.trelloproject.user.dto.ProfileResponseDto;
import com.sparta.trelloproject.user.entity.Password;
import com.sparta.trelloproject.user.entity.User;
import com.sparta.trelloproject.user.entity.UserRoleEnum;
import com.sparta.trelloproject.user.repository.PasswordRepository;
import com.sparta.trelloproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder PasswordEncoder;
    private final PasswordRepository passwordRepository;

    // 회원가입
    public void signup(AuthRequestDto authRequestDto) {
        String userName = authRequestDto.getUserName();
        String password = PasswordEncoder.encode(authRequestDto.getPassword());
        String email = authRequestDto.getEmail();
        UserRoleEnum role = authRequestDto.getRole();

        if (userRepository.findByUserName(userName).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        User user = new User(userName, password, email, role);
        userRepository.save(user);
    }

    // 로그인
    public void login(AuthRequestDto authRequestDto) {
        String userName = authRequestDto.getUserName();
        String password = authRequestDto.getPassword();

        //Id 확인
        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new IllegalArgumentException("Id가 틀렸습니다.")
        );

        //패스워드 확인
        if (!PasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    // 프로필 조회
    public ProfileResponseDto getProfile(Long id) {
        User user = findUser(id);

        return new ProfileResponseDto(user);
    }

    // 프로필 수정
    @Transactional
    public void updateProfile(Long id, ProfileRequestDto profileRequestDto) {
        User user = findUser(id);

        user.setEmail(profileRequestDto.getEmail());
    }

    public User findUser(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );
    }

    // 비밀번호 변경
    @Transactional
    public void updatePassword(PasswordRequestDto passwordRequestDto, Long id){
        User user = findUser(id);
        Password passwordEntity = passwordRepository.findByUser(user);


    }

    private String checkPassword(String newPassword, Password passwordEntity){
        if (PasswordEncoder.matches(newPassword, passwordEntity.getFirstPassword())){
            throw new IllegalArgumentException("이전 비밀번호와 동일합니다.");
        }
        return newPassword;
    }
}
