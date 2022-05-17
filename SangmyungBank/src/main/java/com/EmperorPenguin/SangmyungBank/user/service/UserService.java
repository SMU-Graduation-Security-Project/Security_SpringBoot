package com.EmperorPenguin.SangmyungBank.user.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.UserException;
import com.EmperorPenguin.SangmyungBank.user.dto.UserLoginReq;
import com.EmperorPenguin.SangmyungBank.user.dto.UserRegisterReq;
import com.EmperorPenguin.SangmyungBank.user.entity.Role;
import com.EmperorPenguin.SangmyungBank.user.entity.User;
import com.EmperorPenguin.SangmyungBank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    @Transactional
    public void register(UserRegisterReq userRegisterRequest) {
        String loginId = userRegisterRequest.getLoginId();
        String email = userRegisterRequest.getEmail();
        String phoneNumber = userRegisterRequest.getPhoneNumber();
        String password1 = userRegisterRequest.getPassword1();
        String password2 = userRegisterRequest.getPassword2();

        if(userRepository.findByLoginId(loginId).isPresent()){
            throw new UserException(ExceptionMessages.ERROR_USER_ID_DUPLICATE);
        }

        // 사용자의 아이디와 비밀번호 검증
        checkLoginId(loginId);
        checkUserPassword(password1,password2);

        if(userRepository.findByEmail(email).isPresent()){
            throw new UserException(ExceptionMessages.ERROR_USER_EMAIL_DUPLICATE);
        }
        if(userRepository.findByPhoneNumber(phoneNumber).isPresent()){
            throw new UserException(ExceptionMessages.ERROR_USER_PHONENUMBER_DUPLICATE);
        }
        else {
            try{
                userRepository.save(userRegisterRequest.toEntity(passwordEncoder.encode(password1), Role.USER));
            }catch (Exception e){
                // Exception 이 발생한 이유와 위치는 어디에서 발생했는지 전체적인 단계를 다 출력합니다.
                e.printStackTrace();
                throw new UserException("회원가입에 실패했습니다.");
            }
        }

    }
    @Transactional
    public void login(UserLoginReq userLoginReq)
    {
        User user = userRepository
                .findByLoginId(userLoginReq.getLoginId())
                .orElseThrow(() -> new UserException(ExceptionMessages.ERROR_USER_NOT_FOUND));
        if (!passwordEncoder.matches(userLoginReq.getPassword(), user.getPassword())){
            throw new UserException(ExceptionMessages.ERROR_USER_PASSWORD);
        }

        // HttpSession Or JWT 도입예정.
    }

    private void checkUserPassword(String password1, String password2) {
        // Password의 규칙은 영문자, 특수문자를 포함 8~20이하이다.
        Pattern passwordExpression = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        if (!passwordExpression.matcher(password1).matches()) {
            throw new UserException(ExceptionMessages.ERROR_USER_PASSWORD_FORMAT);
        } else if (!password1.equals(password2)) {
            throw new UserException("입력한 비밀번호가 서로 다릅니다.");
        }
    }
    private void checkLoginId(String loginId) {
        // 시작은 영문으로만,{영문, 숫자} 으로만 이루어진 5 ~ 12자 이하이다.
        Pattern nameExpression = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{4,11}$");
        if (!nameExpression.matcher(loginId).matches()) {
            throw new UserException(ExceptionMessages.ERROR_USER_ID_FORMAT);
        }
    }
}
