package com.mysite.sbb.Controller;

import com.mysite.sbb.Dao.UserRepository;
import com.mysite.sbb.Domain.User;
import com.mysite.sbb.Util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @RequestMapping("/doJoin")
    @ResponseBody
    public String doJoin(String email, String password, String name) {
        if(Util.empty(email)) {
            return "이메일을 입력해주세요.";
        }
        if(userRepository.existsByEmail(email)) {
            return "이미 존재하는 이메일입니다.";
        }
        if(Util.empty(password)) {
            return "비밀번호를 입력해주세요.";
        }
        if(Util.empty(name)) {
            return "이름을 입력해주세요.";
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setRegDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());

        userRepository.save(user);

        return "%s 회원가입이 완료되었습니다.".formatted(name);
    }
}
