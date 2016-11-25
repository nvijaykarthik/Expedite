package in.expedite.web.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource/config")
public class Configuration {

	@RequestMapping(produces="application/json",method=RequestMethod.GET)
	public String hello(){
		return "Hello World";
	}
	
}
