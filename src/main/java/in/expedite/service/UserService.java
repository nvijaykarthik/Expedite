package in.expedite.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import in.expedite.entity.User;
import in.expedite.entity.UserState;
import in.expedite.repository.UserRepository;
import in.expedite.utils.CollectionUtil;

@Component
@Transactional
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	@Qualifier("bcryptEncoder")
	PasswordEncoder encoder;
	
	@Value("${expedite.page.size}")
	private Integer pageSize;
	
	/**
	 * Add new user
	 * @param user
	 */
	public void addUser(User user){
		log.debug("Adding new user "+user);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	/**
	 * get user by user id
	 * @param userId
	 * @return
	 */
	public User getUser(String userId){
		log.debug("Get user by user id "+userId);
		return userRepository.findUserIdQuery(userId);
	}

	/**
	 * Deactivating user
	 * @param user
	 * @return
	 */
	public User deActivate(User user)
	{
		log.debug("Deactivating user "+user);
		user.setState(UserState.INACTIVE.toString());
		return userRepository.save(user); 
	}

	/**
	 * Resetting password 
	 * @param userId
	 * @return
	 */
	public User resetPassword(String userId)
	{
		User user = new User();
		user.setUserId(userId);		
		user.setPassword(encoder.encode("password"));
		log.debug("Resetting Password "+user);
		return userRepository.save(user); 
	}
	
	/**
	 * Updating User
	 * @param user
	 * @return
	 */
	public User updateUser(User user)
	{
		log.debug("Resetting Password "+user);
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user); 
	}
	
	/**
	 * get list of all users.
	 * @return
	 */
	public List<User> getUsers(){
		log.debug("Getting all the Users");
		List<User> users=CollectionUtil.toList(userRepository.findAll());
		// PageRequest request =
		//            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "firstName");

		return users; 
	}
	
	public Long getCount(){
		log.debug("Getting total Users");
		return userRepository.count(); 
	}
}
