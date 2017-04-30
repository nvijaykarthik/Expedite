package in.expedite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.expedite.entity.Team;
import in.expedite.repository.TeamMemberRepository;
import in.expedite.repository.TeamRepository;

@Service
public class TeamServices {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private TeamMemberRepository teamMemberRepository;
	
	public void addTeam(Team team){
		teamRepository.save(team);
	}
	
	public List<Team> getAllTeam(){
		return teamRepository.findAll();
	}
	
}
