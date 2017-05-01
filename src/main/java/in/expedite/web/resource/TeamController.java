package in.expedite.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.entity.Team;
import in.expedite.entity.TeamMember;
import in.expedite.entity.User;
import in.expedite.service.TeamServices;
import in.expedite.utils.ExJsonResponse;

@RestController
@RequestMapping("/resource/team")
public class TeamController {

	@Autowired
	private TeamServices teamServices;
	
	@RequestMapping(method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ExJsonResponse addTeam(@RequestBody Team team){
		teamServices.addTeam(team);
		return new ExJsonResponse(0, "Succesfully Added");
	}
	
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Team> getTeam(){
		return teamServices.getAllTeam();
	}
	
	@RequestMapping(path="/members",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getMembersForTeam(@RequestParam Long teamId){
		return teamServices.getMembersForTeam(teamId);
	}
	
	@RequestMapping(path="/addMember",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ExJsonResponse addTeamMember(@RequestBody TeamMember teamMember){
		teamServices.addTeamMember(teamMember);
		return new ExJsonResponse(0, "Succesfully Added");
	}
	
	@RequestMapping(path="/members",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ExJsonResponse deleteMemberFromTeam(@RequestParam String userId ,@RequestParam Long teamId){
		 teamServices.deleteMembersFromTeam(userId,teamId);
		return new ExJsonResponse(0, "Succesfully Deleted");
	}
}
