package in.expedite.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import in.expedite.entity.User;
import in.expedite.entity.State;
import in.expedite.repository.UserRepository;
import in.expedite.utils.CollectionUtil;

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
