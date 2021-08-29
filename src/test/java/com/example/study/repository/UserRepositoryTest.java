package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
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
        // String sql = insert into user(%s, %s, %d) value (account, email, age);
        User user = new User();
//        user.setId(); 자동으로 생성됨 db에서
        user.setAccount("TestUser03");
        user.setEmail("test3@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User user1 = userRepository.save(user);
        System.out.println("newUser: " + user1);
    }

    @Test
    @Transactional
    public void read(){


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
