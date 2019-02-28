package com.ojs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ojs.controller.Appcontroller;
import com.ojs.entity.Question;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	Appcontroller appcontroller;

	@Test
	public void contextLoads() {

		Question question = appcontroller.getAllTestQuestionsWithANsDB();

		System.out.println(question);
	}
}
