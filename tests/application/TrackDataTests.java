package application;

import org.junit.jupiter.api.Test;

import application.FavoritesData;
import application.TrackData;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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
/*
	@Test
	void TestSetGetDuration() {
		TrackData td = new TrackData(); 
		td.setDuration(30);
		Integer result = td.getDuration();
		assertEquals(result, 30);
	}
	@Test
	void TestSetGetImageUrl() {
		TrackData td = new TrackData(); 
		td.setImageUrl("www.img.com");
		String result = td.getImageUrl();
		assertEquals(result, "www.img.com");
	}
	@Test
	void TestSetGetImageUrl() {
		TrackData td = new TrackData(); 
		td.setImageUrl("www.img.com");
		String result = td.getImageUrl();
		assertEquals(result, "www.img.com");
	}
	*/
}
