package application;


public class TrackData {

	private String artists;
	private String name;
	private String previewUrl;
	private String imageUrl;
	private Integer duration;
	private String genre;
	private String album;

	public String getArtists() {
		return artists;
	}

	public String getName() {
		return name;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Integer getDuration() {
		return duration;
	}

	public String getGenre() {
		return genre;
	}

	public String getAlbum() {
		return album;
	}

	public void setArtists(String artists) {
		this.artists = artists;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}