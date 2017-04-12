package in.expedite.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.entity.Departments;
import in.expedite.service.DepartmentsService;

@RestController
@RequestMapping("/resource/departments")
public class DepartmentsController {

	@Autowired
	DepartmentsService departmentService;
	
	@RequestMapping(path="/pagable",method=RequestMethod.GET,produces="application/json")
	public Iterable<Departments> getDepartments(@RequestParam("p") Integer pageNo,@RequestParam(required=false) String departmentName,@RequestParam(required=false) String manager){
		return departmentService.getDepartments(pageNo, departmentName, manager);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public Iterable<Departments> getAllDepartments(){
		return departmentService.getAllDepartments();
	}
	
	
	@RequestMapping(method=RequestMethod.GET,produces="application/json",path="/search")
	public Iterable<Departments> getDepartmentsByName(@RequestParam(required=false,name="s") String departmentName){
		return departmentService.getDepartmentsByName(departmentName);
	}
}
