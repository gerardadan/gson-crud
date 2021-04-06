package com.netmind.dao;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.netmind.common.model.LocalDateSerializer;
import com.netmind.common.model.Student;
import com.netmind.common.model.StudentList;
import com.netmind.dao.contracts.StudentDao;

public class StudentDaoImpl implements StudentDao {

	private static ArrayList<Student> studentList = null;
	static final Logger logger = Logger.getLogger(StudentDaoImpl.class);

	static {
		studentList = new ArrayList<Student>();
	}

	@Override
	public boolean add(Student student) {
		logger.info("add method called");
		studentList.add(student);
		return true;
	}

	@Override
	public boolean addStudentToFile(Student student) throws IOException {
		logger.info("addStudentToFile method called");
		try (FileWriter fileWriter = new FileWriter(
				FileManagerDao.getFileName("txt"), true);
				BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
			bufferWriter.write(student.toTxtFile());
			bufferWriter.write(System.lineSeparator());
		} catch (IOException e) {
			logger.error(e.getMessage() + student.toString());
			throw e;
		}

		return true;
	}

	@Override
	public boolean addToJsonFile(Student student) throws IOException {
		// TODO Auto-generated method stub
		List<Student> studentList = getAllFromJson();
		studentList.add(student);

		try (Writer writer = new FileWriter(
				FileManagerDao.getFileName("json"))) {

			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(LocalDate.class,
					new LocalDateSerializer());

			Gson gson = gsonBuilder.setPrettyPrinting().create();
			gson.toJson(studentList.toArray(), writer);
		} catch (IOException e) {
			logger.error(e.getMessage() + student.toString());
			throw e;
		}

		return true;
	}
	
	@Override
	public List<Student> getAllFromJson() throws IOException {		
		List<Student> listStudent = null;
		try (FileReader reader = new FileReader("alumno.json")) {
			System.out.println();
			Gson gson = new Gson();
			
			Type type = new TypeToken<ArrayList<Student>>(){}.getType();
			listStudent list = gson.fromJson(reader, type);
			
			//listStudent.toString();
			//map.toString();
			
		} catch (IOException e) {
			//logger.error(e.getMessage() + student.toString());
			throw e;
		}
		return listStudent;
	}


	@Override
	public boolean updateToJsonFile(Student student) throws IOException {
		// TODO Auto-generated method stub
		List<Student> studentList = getAllFromJson();
		studentList.remove(student);

		try (Writer writer = new FileWriter(
				FileManagerDao.getFileName("json"))) {

			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(LocalDate.class,
					new LocalDateSerializer());

			Gson gson = gsonBuilder.setPrettyPrinting().create();
			gson.toJson(studentList.toArray(), writer);
		} catch (IOException e) {
			logger.error(e.getMessage() + student.toString());
			throw e;
		}

		return true;
	}
}
