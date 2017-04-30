package in.expedite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.entity.Team;

public interface TeamRepository extends JpaRepository<Team,Long> {

}
