package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {
    // Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository; //singleton

    @Test
    public void create(){
        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer"; // LoginUserAuditorAware 적용으로 자동 createdBy 설정


        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(UserStatus.REGISTERED);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt); // LoginUserAuditorAware 적용으로 자동 createdAt, createdBy 설정
        user.setCreatedBy(createdBy);

        //생성자의 인자가 4개인 생성자를 만들지 않아도 이렇게 할수가 있음음
        // new User(account, password, status, email)
//       User u = User.builder()
//                .account(account)
//                .password(password)
//                .status(status)
//                .email(email)
//                .build();

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
        Assertions.assertEquals("AdminServer", newUser.getCreatedBy());
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");
        Assertions.assertNotNull(user);

    }

//    @Test
//    public User read(@RequestParam Long id){
//        Optional<User> user = userRepository.findById(2L);
//
//        user.ifPresent(selectUser -> {
//            System.out.println("user : " + selectUser);
//            System.out.println("email : " + selectUser.getEmail());
//        });
//
//        return user.get();
//    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update mehtod()");

            userRepository.save(selectUser);
            //해당 아이디가 있는지 없는지 확인해서 있으면 update 해줌
        });
    }

//    @DeleteMapping("/api/user")
    @Test
    @Transactional // 마지막에 롤백해줌 = 실행안됨.
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assertions.assertFalse(deleteUser.isPresent());

//        if(deleteUser.isPresent()){
//            System.out.println("데이터 존재");
//        }else{
//            System.out.println("데이터 삭제");
//        }
    }
}
