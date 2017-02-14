package in.expedite.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import in.expedite.entity.Configuration;

public interface ConfigurationRepository extends PagingAndSortingRepository<Configuration, Integer>,JpaSpecificationExecutor<Configuration>{

}
