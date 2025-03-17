package z.practice.object2;

public class Movie {
	String title;
	String director;
	double rating;
	int price;
	boolean isPlaying;
	
	
	public Movie() {
		super();
	}
	
	public Movie(String title, String director, double rating, int price, boolean isPlaying) {
		super();
		this.title = title;
		this.director = director;
		this.rating = rating;
		this.price = price;
		this.isPlaying = isPlaying;
	}


	public void SetOff() {
		this.isPlaying = false;
	}
	
	public void MInfo(){
		System.out.println();
	}
}
