package in.expedite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import in.expedite.entity.Department;

public interface DepartmentsRepository extends PagingAndSortingRepository<Department, Integer>,JpaSpecificationExecutor<Department>{

	public Iterable<Department> findByDepartmentNameContainingIgnoreCase(String departmentName);
}
