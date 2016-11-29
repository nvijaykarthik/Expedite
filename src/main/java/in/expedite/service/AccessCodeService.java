package in.expedite.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.expedite.entity.AccessCode;
import in.expedite.repository.AccessCodeRepository;

/**
 * Access code service which has getall, add and delete operation, there is no update. 
 * @author vijaykarthik N 
 *
 */
@Component
public class AccessCodeService {

	@Autowired
	AccessCodeRepository acRepo;
	
	private static final Logger LOG = LoggerFactory.getLogger(AccessCodeService.class);
	
	/**
	 * Adding new ACCESS CODE WITH MENU LINK 
	 * @param accessCode
	 * @return
	 */
	public AccessCode addAccessCode(AccessCode accessCode){
		LOG.debug("Adding new Access Code: "+accessCode);
		return acRepo.save(accessCode);
	}
	
	/**
	 * Deleting the access code.
	 * @param accessCode
	 */
	public void deleteAccessCode(AccessCode accessCode){
		LOG.debug("Removing the access code: "+ accessCode);
		acRepo.delete(accessCode);
	}
	
	public List<AccessCode> getAccessCodes(){
		LOG.debug("Retrieving all the Access codes available");
		 List<AccessCode> ac=acRepo.findAll();
		LOG.trace("Access codes : "+ac);
		return ac;
	}
}
