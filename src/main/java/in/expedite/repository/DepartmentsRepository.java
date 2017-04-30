package in.expedite.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import in.expedite.entity.Department;

public interface DepartmentsRepository extends PagingAndSortingRepository<Department, Integer>,JpaSpecificationExecutor<Department>{

	public Iterable<Department> findByDepartmentNameContainingIgnoreCase(String departmentName);
}
