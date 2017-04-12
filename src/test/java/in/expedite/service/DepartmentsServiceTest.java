package in.expedite.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import in.expedite.entity.Departments;
import in.expedite.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentsServiceTest {

	@Autowired
	DepartmentsService depService;
	
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentsServiceTest.class);
	
	@Test
	public void getDepartments(){
		/*depService.getDepartments(0, null, "vkarthik").forEach(dep->{
			LOG.info("departments :"+dep);
		});*/
		depService.getAllDepartments().forEach(dep->{
			LOG.info("departments :"+dep);
		});
	}
	
}
