package in.expedite.service;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.expedite.entity.Role;
import in.expedite.entity.RoleAccessXref;
import in.expedite.entity.State;
import in.expedite.repository.RoleAccessXrefRepository;
import in.expedite.repository.RoleRepository;
import in.expedite.utils.CollectionUtil;

@Component
@Transactional
public class RoleService {

	@Autowired
	RoleRepository rRepo;
	
	@Autowired
	RoleAccessXrefRepository raxRepo;
	
	private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Getting List of available Roles 
	 * @return
	 */
	public List<Role> getRoles(){
		LOG.debug("Getting List of Available Roles ");
		List<Role> roles=rRepo.findAll();
		LOG.trace("Roles List :"+roles);
		return roles;
	}
	
	/**
	 * Adding new Role
	 * @param role
	 * @return
	 */
	public Role addRole(Role role){
		LOG.debug("Adding Role :" + role);
		return rRepo.save(role);
	}
	
	/**
	 * Activating/DeActivating Role
	 * @param role
	 * @return
	 */
	public Role toggleStatusRole(Role role){
		if(role.getStatus().equals(State.ACTIVE.toString())){
    		role.setStatus(State.INACTIVE.toString());
		}else if(role.getStatus().equals(State.INACTIVE.toString())){
			role.setStatus(State.ACTIVE.toString());
		}
		LOG.debug("toggle Status:"+role);
		return rRepo.save(role);
	}
	
	/**
	 * Adding Role access i.e, granting access level to each role
	 * @param roleAccess
	 * @return
	 */
	public RoleAccessXref addRoleAccess(RoleAccessXref roleAccess){
		LOG.debug("Adding Role Access : "+roleAccess);
		return raxRepo.save(roleAccess);
	}
	
	/**
	 * Removing access to the 
	 * @param roleAccess
	 * @return
	 */
	public void deleteRoleAccess(RoleAccessXref roleAccess){
		LOG.debug("Removing Role Access : "+ roleAccess);
		raxRepo.delete(roleAccess);
	}
}
