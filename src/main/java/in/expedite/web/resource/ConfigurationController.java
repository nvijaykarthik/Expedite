package in.expedite.web.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.entity.Configuration;

@RestController
@RequestMapping("/resource/config")
public class ConfigurationController {

	@RequestMapping(produces="application/json",method=RequestMethod.GET)
	public List<Configuration> getConfiguration() throws Exception{
		List<Configuration> cnfglst= new ArrayList<Configuration>();
		for(int i=0;i<10;i++){
			Configuration conf = new Configuration();
		conf.setKey("Hello");
		conf.setValue("World");
		cnfglst.add(conf);
		}
		return  cnfglst;
	}
	
}
