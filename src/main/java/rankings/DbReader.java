package net.fnarg.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DbReader implements CommandLineRunner {

	@Autowired
	private RankingRepo repository;

	@Override
	public void run(String... args) throws Exception {
		// fetch all records
		List<Rankings> rankings = repository.findAll();
		System.out.println("number of records found: "+ rankings.size());
	}
}