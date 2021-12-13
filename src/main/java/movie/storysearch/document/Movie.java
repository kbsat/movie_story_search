package movie.storysearch.document;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie implements Serializable {
	private String title;
	private String genre;
	private String url;
	private String img;
	private String date;
	private String director;
	private Float rating;
	private String summary;
}
