package com.main.app;

import com.main.model.UserModel;
import com.main.repository.AuthRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private AuthRepository repo;

	@Test
	public void createUserTest(){
		UserModel user=new UserModel("gon","741");
		UserModel ret= repo.save(user);
		assert(ret.equals(ret));
	}

}
