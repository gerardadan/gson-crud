package com.netmind.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.netmind.common.model.Student;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class StudentDaoImplIntegrationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FileManagerDao.createFile("alumno.json");
	}

	/*
	 * @AfterClass public static void tearDownAfterClass() throws Exception { }
	 * 
	 * @Before public void setUp() throws Exception { }
	 * 
	 * @After public void tearDown() throws Exception { }
	 * 
	 * @Test public void testAddToJsonFile() throws IOException { StudentDao
	 * studentDao = new StudentDaoImpl();
	 * 
	 * Student student = new Student();
	 * 
	 * studentDao.add(student); fail("Not yet implemented"); }
	 */
	  @Test
	  public void getAll() throws Exception { 

		  StudentDaoImpl studentDao = new StudentDaoImpl();
		  List<Student> list = studentDao.getAllFromJson();
		  for(Student student : list) {
			    System.out.println(student.getName());
			}
	  }
}
