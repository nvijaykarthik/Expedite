package in.expedite.web.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.entity.State;
import in.expedite.entity.User;
import in.expedite.service.UserService;
import in.expedite.utils.ExJsonResponse;

@RestController
@RequestMapping("/resource/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public Iterable<User> getUsers(@RequestParam("p") Integer pageNo,
			@RequestParam(name="userId",required=false) String userId,
			@RequestParam(name="firstName",required=false) String firstName,
			@RequestParam(name="lastName",required=false) String secondName,
			@RequestParam(name="email",required=false) String email,
			@RequestParam(name="state",required=false) String state){
		return userService.searchUser(userId, firstName, secondName, email, state, pageNo);
	}
	
	@RequestMapping(path="/states",method=RequestMethod.GET,produces="application/json")
	public List<String> getState(){
		List<String> states=new ArrayList<>();
		for(State state:State.values()){
			states.add(state.getState());
		}
		return states ;
	}
	
	@RequestMapping(method=RequestMethod.POST,produces="application/json")
	public ExJsonResponse saveUser(@RequestBody User user){
		userService.addUser(user);
		return new ExJsonResponse(0,"Sucessfully Added");
	}
	
	@RequestMapping(path="/update",method=RequestMethod.POST,produces="application/json")
	public ExJsonResponse updateUser(@RequestBody User user){
		userService.updateUser(user);
		return new ExJsonResponse(0,"Sucessfully Updated");
	}
	
	@RequestMapping(path="/resetPwd",method=RequestMethod.GET,produces="application/json")
	public ExJsonResponse resetUserPwd(@RequestParam String userId){
		userService.resetPassword(userId);
		return new ExJsonResponse(0,"Password Reset Sucessfull");
	}
	
}
