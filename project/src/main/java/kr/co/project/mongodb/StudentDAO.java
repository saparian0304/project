package kr.co.project.mongodb;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Map insert(Map map) {
		return mongoTemplate.insert(map, "student");
	}
	
}
