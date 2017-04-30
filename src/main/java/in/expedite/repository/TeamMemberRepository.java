package in.expedite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.entity.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

}
