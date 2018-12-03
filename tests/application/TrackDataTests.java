package application;

import application.TrackData;

//import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class TrackDataTests {

	@Test
	void TestSetGetArtists() {
		TrackData td = new TrackData(); 
		td.setArtists("Beyonce");
		String result = td.getArtists();
		assertEquals(result, "Beyonce");
	}
	
	@Test
	void TestSetGetName() {
		TrackData td = new TrackData(); 
		td.setName("Jay");
		String result = td.getName();
		assertEquals(result, "Jay");
	}
	
	@Test
	void TestSetGetPreviewURL() {
		TrackData td = new TrackData(); 
		td.setPreviewUrl("www.url.com");
		String result = td.getPreviewUrl();
		assertEquals(result, "www.url.com");
	}
	
	@Test
	void TestSetGetImageUrl() {
		TrackData td = new TrackData(); 
		td.setImageUrl("www.img.com");
		String result = td.getImageUrl();
		assertEquals(result, "www.img.com");
	}

	@Test
	void TestSetGetDuration() {
		TrackData td = new TrackData(); 
		td.setDuration(30);
		int result = td.getDuration();
		assertEquals(result, 30);
	}
	@Test
	void TestSetGetGenre() {
		TrackData td = new TrackData(); 
		td.setImageUrl("Pop");
		String result = td.getImageUrl();
		assertEquals(result, "Pop");
	}
	@Test
	void TestSetGetAlbum() {
		TrackData td = new TrackData(); 
		td.setImageUrl("Untitled");
		String result = td.getImageUrl();
		assertEquals(result, "Untitled");
	}
	
}
