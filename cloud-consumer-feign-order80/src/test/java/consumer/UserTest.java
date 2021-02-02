package consumer;

import com.atguigu.springcloud.OrderFeignMain80;
import com.atguigu.springcloud.entity.User;
import com.atguigu.springcloud.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderFeignMain80.class)
public class UserTest {

    private static Logger log = LoggerFactory.getLogger(UserTest.class);

    @Resource
    private UserService userService;

    @Test
    public void  getUserTest(){
        User user = userService.getUser(4L);
        System.out.println(user);
    }

    @Test
    public void  addUserTest(){

        User user = new User();

        user.setName("李白");
        user.setAge(88);
        userService.addUser(user);
        System.out.println(user);
    }

}
