package net.fnarg.api;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RankingRepo extends MongoRepository<Rankings, Long> {
		List<Rankings> findByDomainStartingWith(String domain);
}