package in.expedite.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.expedite.entity.Departments;
import in.expedite.service.DepartmentsService;

public class DepartmentsController {

	@Autowired
	DepartmentsService departmentService;
	
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public Iterable<Departments> getDepartments(@RequestParam("p") Integer pageNo,
			@RequestParam String departmentName,@RequestParam String manager){
		return departmentService.getDepartments(pageNo, departmentName, manager);
	}
}
