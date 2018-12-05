package application;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FavoritesDataTests {

	// Integration Test
	// Chanelle Mosquera
	@Test
	void testGetRecommendation() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		assertThat(c, instanceOf(TrackData.class));
	}
	
	// Integration Test
	// Chanelle Mosquera
	@Test
	void testNumberOfFavorites() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		
		fd.addToFavorites(c);
		fd.addToFavorites(spotify.getNextRecommendation());
		fd.addToFavorites(spotify.getNextRecommendation());
		fd.addToFavorites(spotify.getNextRecommendation());
		int num = fd.numberOfFavorites();
		assertEquals(num, 4);
	}
	
	// Integration Test
	// Oliver Curry
	@Test
	void testNumberOfFavorites2() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();

		fd.addToFavorites(c);
		int num = fd.numberOfFavorites();
		assertEquals(num, 1);
	}
	
	// Unit Test
	// Oliver Curry
	@Test
	void testNumberOfMinutes() {
		FavoritesData fd = new FavoritesData();
		
		fd.totalDuration = 5;
		int result = fd.totalDuration;
		assertEquals(result, 5);
	}
	
	// Integration Test
	// Brandon Luong
	@Test
	void testAddToFavorites() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		
		fd.addToFavorites(c);
		TrackData td = fd.getAt(0);
		assertEquals(td, c);
	}

	// Integration Test
	// Alex Gata
	@Test
	void testPeek() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		
		fd.addToFavorites(c);
		TrackData td = fd.peek();
		assertEquals(td, c);
	}
	
	// Integration Test
	// Brandon Luong
	@Test
	void testPeek2() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		
		fd.addToFavorites(c);
		fd.addToFavorites(spotify.getNextRecommendation());
		TrackData td = fd.peek();
		assertEquals(td, c);
	}
	
	// Integration Test
	// Alex Gata
	@Test
	void testGetNextSong() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		
		fd.addToFavorites(c);
		TrackData td = fd.getNextSong();
		assertEquals(td, c);
	}
	
	// Integration Test
	// Kevin Vincent
	@Test
	void  testGetFavoritesList() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		fd.addToFavorites(c);	
		
		assertEquals(1, fd.getFavoritesList().size());
	}

	// Loop Test #1
	@Test
	void testSearchLoop0() {
		FavoritesData fd = new FavoritesData();
		assertEquals(0, fd.search("test").size());
	}

	// Loop Test #1
	@Test
	void testSearchLoop1() {
		FavoritesData fd = new FavoritesData();

		TrackData sample1 = new TrackData();
		sample1.setName("test");
		sample1.setDuration(100);
		sample1.setArtists("testArtists");
		sample1.setGenre("testGenre");
		sample1.setImageUrl("https://via.placeholder.com/150");
		fd.addToFavorites(sample1);

		assertEquals(1, fd.search("test").size());
	}

	// Loop Test #1
	@Test
	void testSearchLoopN() {
		FavoritesData fd = new FavoritesData();

		for(int i = 0; i < 100; i++) {
			TrackData sample = new TrackData();
			sample.setName("test"+i);
			sample.setDuration(100);
			sample.setArtists("test"+i+"Artists");
			sample.setGenre("test"+i+"Genre");
			sample.setImageUrl("https://via.placeholder.com/150");
			fd.addToFavorites(sample);
		}

		assertEquals(100, fd.search("test").size());
	}

	// Loop Test #2
	@Test
	void testSimpleSearchLoop0() {
		FavoritesData fd = new FavoritesData();
		assertEquals(0, fd.searchNames("test").size());
	}

	// Loop Test #2
	@Test
	void testSimpleSearchLoop1() {
		FavoritesData fd = new FavoritesData();

		TrackData sample1 = new TrackData();
		sample1.setName("test");
		sample1.setDuration(10);
		fd.addToFavorites(sample1);

		assertEquals(1, fd.searchNames("test").size());
	}

	// Loop Test #2
	@Test
	void testSimpleSearchLoopN() {
		FavoritesData fd = new FavoritesData();

		for(int i = 0; i < 100; i++) {
			TrackData sample = new TrackData();
			sample.setName("test"+i);
			sample.setDuration(i);
			fd.addToFavorites(sample);
		}

		assertEquals(100, fd.searchNames("test").size());
	}
}
