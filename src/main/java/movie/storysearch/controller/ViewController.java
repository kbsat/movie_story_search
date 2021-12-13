package movie.storysearch.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import movie.storysearch.document.Movie;
import movie.storysearch.service.SearchService;

@Controller
@RequiredArgsConstructor
public class ViewController {
	private final SearchService searchService;

	@GetMapping("/")
	public String index(Model model) {
		return "idx";
	}

	@GetMapping("/search")
	public String search(Model model, @RequestParam(name = "summary") String summary){
		List<Movie> movies = searchService.searchThat(summary, 0, 100);
		model.addAttribute("movies", movies);
		return "search";
	}

}
