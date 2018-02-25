package io.vijaykarthik.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.vijaykarthik.exception.CustomException;

@RestController
@RequestMapping("/api")
public class ReSTController {

	@RequestMapping("/throwCustomException")
	public void throwCustomeExeption() throws CustomException {
		throw new CustomException("Throwing CUstom Exception");
	}
	
	@RequestMapping("/throwBadReqException")
	public void throwBadReqExeption() throws IllegalArgumentException {
		throw new IllegalArgumentException("Throwing CUstom Exception");
	}
}
