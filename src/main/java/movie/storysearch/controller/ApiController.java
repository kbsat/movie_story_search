package movie.storysearch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import movie.storysearch.document.Movie;
import movie.storysearch.service.SearchService;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class ApiController {
	private final SearchService searchService;

	@GetMapping
	public List<Movie> search(
		@RequestParam(name = "summary") String summary,
		@RequestParam(name = "from", defaultValue = "0") int from,
		@RequestParam(name = "size", defaultValue = "10") int size
	) {
		return searchService.searchThat(summary, from, size);
	}
}
