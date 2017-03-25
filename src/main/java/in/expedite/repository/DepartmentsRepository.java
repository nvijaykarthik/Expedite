package in.expedite.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import in.expedite.entity.Departments;

public interface DepartmentsRepository extends PagingAndSortingRepository<Departments, Integer>,JpaSpecificationExecutor<Departments>{

}
