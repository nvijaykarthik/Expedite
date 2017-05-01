package in.expedite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import in.expedite.entity.TeamMember;
import in.expedite.entity.User;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

	@Query("Select u from User u where u.userId in (select T.userId from TeamMember T where T.teamId=:teamId)")
	public List<User> getMembersForTeam(@Param(value = "teamId") Long teamId);
	
	 @Modifying
	 @Transactional
	public void deleteByUserIdAndTeamId(String userId,Long teamId);
}
