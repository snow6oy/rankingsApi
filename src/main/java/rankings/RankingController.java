package net.fnarg.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RankingController {

	@Autowired
	private RankingRepo repository;
  private final String query = new String();

  @RequestMapping("/rankings")
  public List<Rankings> ranking(@RequestParam(value="query", defaultValue="facebook.com") String query) {
		List<Rankings> rank = repository.findByDomainStartingWith(query);
    return rank;
  }
}