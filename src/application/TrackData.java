package application;


public class TrackData {

	private String artists;
	private String name;
	private String previewUrl;
	private String imageUrl;

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
}