package in.expedite.service;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@SpringBootTest
@RunWith(SpringRunner.class)
public class TeamServicesTest {

	@Autowired
	private TeamServices teamServices;
	
	@Test
	public void testGetMembersForTeam() {
		 teamServices.getMembersForTeam(1L);
	}

}
