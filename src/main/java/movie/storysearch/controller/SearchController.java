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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import movie.storysearch.document.Movie;

@Controller
@RequiredArgsConstructor
public class SearchController {
	private final RestHighLevelClient client;

	@ResponseBody
	@GetMapping("/")
	public List<Movie> run() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		SearchRequest searchRequest = new SearchRequest("movies");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("summary", "조선"));
		searchRequest.source(searchSourceBuilder);
		SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
		SearchHit[] hits = response.getHits().getHits();
		List<Movie> movies = new ArrayList<>();

		Arrays.stream(hits).forEach(hit -> {
			try {
				movies.add(objectMapper.readValue(hit.getSourceAsString(), Movie.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});

		return movies;
	}
}
