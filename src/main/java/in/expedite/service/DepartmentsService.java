package in.expedite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import in.expedite.entity.Department;
import in.expedite.repository.DepartmentsRepository;
import in.expedite.specification.SpecificationUtils;

@Service
public class DepartmentsService {

	@Value("${expedite.page.size}")
	private Integer pageSize;
	
	@Autowired
	DepartmentsRepository departmentRepo;
	
	public Iterable<Department> getDepartments(@RequestParam("p") Integer pageNo,
			@RequestParam String departmentName,@RequestParam String manager){
		
		PageRequest pg = new PageRequest(pageNo, pageSize);
		Specification<Department> spec = SpecificationUtils.getDepartmentsSpecs(departmentName, manager);
		
		return departmentRepo.findAll(spec, pg);
	}
	
	public Department addDepartment(Department dept){
		return departmentRepo.save(dept);
	}
	
	
	public Iterable<Department> getAllDepartments(){
		return departmentRepo.findAll();
	}
	
	public Iterable<Department> getDepartmentsByName(String deptName){
		return departmentRepo.findByDepartmentNameContainingIgnoreCase(deptName);
	}

	public Department getDeptById(Long deptId) {
		return departmentRepo.findById(deptId);
	}
	
	
}
