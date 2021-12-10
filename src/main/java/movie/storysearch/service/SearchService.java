package movie.storysearch.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import movie.storysearch.document.Movie;

@Service
@RequiredArgsConstructor
public class SearchService {
	private final RestHighLevelClient client;

	public List<Movie> searchThat(String text){
		ObjectMapper objectMapper = new ObjectMapper();

		SearchRequest searchRequest = new SearchRequest("movies");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("summary", text));
		searchRequest.source(searchSourceBuilder);
		SearchResponse response = null;
		List<Movie> movies = new ArrayList<>();
		try {
			response = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHit[] hits = response.getHits().getHits();

			Arrays.stream(hits).forEach(hit -> {
				try {
					movies.add(objectMapper.readValue(hit.getSourceAsString(), Movie.class));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return movies;
	}
}
