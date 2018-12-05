package application;

import org.junit.jupiter.api.Test;

import application.LoopingAudioPlayer;
import application.TrackData;
import application.controller.discover.DiscoverController;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

class LoopingAudioPlayerTests {

	// Integration Test
	// Andrew Lau
	@Test
	void testGetFadeGain() {
		FavoritesData fd = new FavoritesData();
		DiscoverController dc = new DiscoverController(fd);
		SpotifyAccessor spotify = new SpotifyAccessor();
		TrackData currentTrack = spotify.getNextRecommendation();
		
		LoopingAudioPlayer player;
		double result = 0.0;
		
		try {
			player = new LoopingAudioPlayer(new URL(currentTrack.getPreviewUrl()), null);
			result = player.getFadeGain(1.00, 2.00, 1.00, 0.50, 2.00, 1.00, 0.50);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		assertEquals(result, 2.0);
	}
	
	// Integration Test
	// Andrew Lau
	@Test
	void testGetFadeGain2() {
		FavoritesData fd = new FavoritesData();
		DiscoverController dc = new DiscoverController(fd);
		SpotifyAccessor spotify = new SpotifyAccessor();
		TrackData currentTrack = spotify.getNextRecommendation();
		
		LoopingAudioPlayer player;
		double result = 0.0;
		
		try {
			player = new LoopingAudioPlayer(new URL(currentTrack.getPreviewUrl()), null);
			result = player.getFadeGain(1.00, 2.00, 1.00, 0.50, -3.0, 1.00, 0.50);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		assertEquals(result, -3.0);
	}
	
	// Integration Test
	// Kevin Vincent
	@Test
	void testRangeMap() {
		FavoritesData fd = new FavoritesData();
		DiscoverController dc = new DiscoverController(fd);
		SpotifyAccessor spotify = new SpotifyAccessor();
		TrackData currentTrack = spotify.getNextRecommendation();
		
		LoopingAudioPlayer player;
		double result = 0.0;
		
		try {
			player = new LoopingAudioPlayer(new URL(currentTrack.getPreviewUrl()), null);
			result = player.rangeMap(1.00, 2.00, 1.00, 0.50, 2.00);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		assertEquals(result, 0.5);
	}
}
