package kr.ac.jejunu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@org.springframework.web.bind.annotation.RestController
//위에꺼 쓰면 @Responsebody 안써도 됨
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestController {
    private final UserDao userDao;

    @GetMapping("/{id}")
    public User get(@PathVariable Integer id){
        return userDao.findById(id);
    }

    @PostMapping
    public User create(@RequestBody User user){
        userDao.insert(user);
        return user;
    }
}
