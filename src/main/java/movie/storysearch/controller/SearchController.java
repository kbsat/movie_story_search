package movie.storysearch.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import movie.storysearch.document.Movie;
import movie.storysearch.service.SearchService;

@Controller
@RequiredArgsConstructor
public class SearchController {
	private final RestHighLevelClient client;
	private final SearchService searchService;

	@GetMapping("/")
	public String index(Model model) {
		List<Movie> movies
			= searchService.searchThat("조선");
		model.addAttribute("movies", movies);
		return "hello";
	}
}
