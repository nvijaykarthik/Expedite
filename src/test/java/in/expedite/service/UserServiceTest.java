package in.expedite.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserService userService;
	
	@Test
	public void searchUser(){
		userService.searchUser(null, "", null, "", null, 0);
		userService.searchUser("vkarthik", "", null, "", null, 0);
		userService.searchUser("vkarthik", "", null, "", "Active", 0);
	}
}
