package in.expedite.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.entity.Department;
import in.expedite.entity.User;
import in.expedite.service.DepartmentsService;
import in.expedite.service.UserService;
import in.expedite.utils.ExJsonResponse;

@RestController
@RequestMapping("/resource/departments")
public class DepartmentsController {

	@Autowired
	private DepartmentsService departmentService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path="/pagable",method=RequestMethod.GET,produces="application/json")
	public Iterable<Department> getDepartments(@RequestParam("p") Integer pageNo,@RequestParam(required=false) String departmentName,@RequestParam(required=false) String manager){
		return departmentService.getDepartments(pageNo, departmentName, manager);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public Iterable<Department> getAllDepartments(){
		return departmentService.getAllDepartments();
	}
	
	
	@RequestMapping(method=RequestMethod.GET,produces="application/json",path="/search")
	public Iterable<Department> getDepartmentsByName(@RequestParam(required=false,name="s") String departmentName){
		return departmentService.getDepartmentsByName(departmentName);
	}
	
	
	@RequestMapping(method=RequestMethod.POST,produces="application/json")
	public ExJsonResponse addDepartment(@RequestBody Department deps){
		departmentService.addDepartment(deps);
		return new ExJsonResponse(0,"Sucessfully Added");
	}

	@RequestMapping(path="/manager",method=RequestMethod.GET,produces="application/json")
	public User getManager(@RequestParam String managerId){
    	return userService.getUser(managerId);
	}
	
	@RequestMapping(path="/deptById",method=RequestMethod.GET,produces="application/json")
	public Department getDeptById(@RequestParam Long deptId){
    	return departmentService.getDeptById(deptId);
	}
}
