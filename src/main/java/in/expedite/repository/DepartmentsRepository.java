package in.expedite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import in.expedite.entity.Departments;

public interface DepartmentsRepository extends PagingAndSortingRepository<Departments, Integer>,JpaSpecificationExecutor<Departments>{

	public Iterable<Departments> findByDepartmentNameContainingIgnoreCase(String departmentName);
}
