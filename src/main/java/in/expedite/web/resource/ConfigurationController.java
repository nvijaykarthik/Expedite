package in.expedite.web.resource;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.entity.Configuration;
import in.expedite.entity.MyUser;
import in.expedite.service.ConfigurationService;
import in.expedite.utils.ExJsonResponse;


@RestController
@RequestMapping("/resource/config")
public class ConfigurationController {

	@Autowired
	ConfigurationService cs;
	
	private static final Logger LOG = LoggerFactory.getLogger(ConfigurationController.class);
	
	@RequestMapping(produces="application/json",method=RequestMethod.GET)
	public Iterable<Configuration> getConfiguration(@RequestParam(name="p") Integer pageNumber,@RequestParam(name="key",required=false) String key,@RequestParam(name="value",required=false) String value) throws Exception{
		LOG.info("Retrieving All configuration");
		Iterable<Configuration> confList=cs.getAllConfiguration(pageNumber,key,value);
		LOG.debug("Getting All the configuration available "+confList);
		return  confList;
	}
	
	@RequestMapping(produces="application/json",method=RequestMethod.POST)
	public ExJsonResponse addConfig(@Valid @RequestBody Configuration cfg){
		LOG.info("Adding new configuration property" + cfg.toString());
		cs.saveConfiguration(cfg);
    	return new ExJsonResponse(0,"Sucessfully Added");
	}
	
	@RequestMapping(produces="application/json",method=RequestMethod.PATCH)
	public ExJsonResponse saveConfig(@Valid @RequestBody Configuration cfg)	{
		LOG.info("Updating existing configuration property" + cfg.toString());
		cs.saveConfiguration(cfg);
		return new ExJsonResponse(0,"Sucessfully Updated");
	}
	
}
