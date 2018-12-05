package application;

import org.junit.jupiter.api.Test;
import application.FavoritesData;

import static org.hamcrest.CoreMatchers.instanceOf;
//import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

class FavoritesDataTests {

	@Test
	void testGetRecommendation() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		assertThat(c, instanceOf(TrackData.class));
	}
	
	@Test
	void testNumberOfFavorites() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		
		fd.addToFavorites(c);
		int num = fd.numberOfFavorites();
		assertEquals(num, 1);
	}
	
	@Test
	void testNumberOfMinutes() {
		FavoritesData fd = new FavoritesData();
		fd.totalDuration = 5;
		int result = fd.totalDuration;
		assertEquals(result, 5);
	}
	
	@Test
	void testAddToFavorites() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		
		fd.addToFavorites(c);
		TrackData td = fd.getAt(0);
		assertEquals(td, c);
	}

	@Test
	void testPeek() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		
		fd.addToFavorites(c);
		TrackData td = fd.peek();
		assertEquals(td, c);
	}
	
	@Test
	void testGetNextSong() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		
		fd.addToFavorites(c);
		TrackData td = fd.getNextSong();
		assertEquals(td, c);
	}
	
	@Test
	void  testGetFavoritesList() {
		FavoritesData fd = new FavoritesData();		
		assertEquals(0, fd.getFavoritesList().size());
	}
}
