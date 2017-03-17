package in.expedite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.expedite.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	

}
