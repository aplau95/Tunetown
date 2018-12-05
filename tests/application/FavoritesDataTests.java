package application;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FavoritesDataTests {

	// Integration Test
	@Test
	void testGetRecommendation() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		assertThat(c, instanceOf(TrackData.class));
	}
	
	// Integration Test
	@Test
	void testNumberOfFavorites() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		
		fd.addToFavorites(c);
		int num = fd.numberOfFavorites();
		assertEquals(num, 1);
	}
	
	// Unit Test
	@Test
	void testNumberOfMinutes() {
		FavoritesData fd = new FavoritesData();
		fd.totalDuration = 5;
		int result = fd.totalDuration;
		assertEquals(result, 5);
	}
	
	// Integration Test
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
	@Test
	void testGetNextSong() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		FavoritesData fd = new FavoritesData();
		TrackData c = spotify.getNextRecommendation();
		
		fd.addToFavorites(c);
		TrackData td = fd.getNextSong();
		assertEquals(td, c);
	}
	
	// Unit Test
	@Test
	void  testGetFavoritesList() {
		FavoritesData fd = new FavoritesData();		
		assertEquals(0, fd.getFavoritesList().size());
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

	@Test
	void testSimpleSearchLoop0() {
		FavoritesData fd = new FavoritesData();
		assertEquals(0, fd.searchNames("test").size());
	}

	@Test
	void testSimpleSearchLoop1() {
		FavoritesData fd = new FavoritesData();

		TrackData sample1 = new TrackData();
		sample1.setName("test");
		sample1.setDuration(10);
		fd.addToFavorites(sample1);

		assertEquals(1, fd.searchNames("test").size());
	}

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
