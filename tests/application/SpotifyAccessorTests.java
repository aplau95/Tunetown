package application;

import org.junit.jupiter.api.Test;

import application.SpotifyAccessor;
import application.TrackData;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpotifyAccessorTests {

	// Unit Test
	@Test
	void testGetRecommendation() {
		SpotifyAccessor spotify = new SpotifyAccessor();
		TrackData c = spotify.getNextRecommendation();
		assertThat(c, instanceOf(TrackData.class));
	}
}
