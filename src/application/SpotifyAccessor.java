package application;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.*;

public class SpotifyAccessor {

	private static final String clientId = "160b683f23e946ed8000ec438e36890a";
	private static final String clientSecret = "efa3a5718c6a49acb3828305c3a01c7b";

	private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
			.setClientId(clientId)
			.setClientSecret(clientSecret)
			.build();

	public SpotifyAccessor() {

		try {

			final ClientCredentials clientCredentials = spotifyApi.clientCredentials()
					.build()
					.execute();

			spotifyApi.setAccessToken(clientCredentials.getAccessToken());

		} catch (IOException | SpotifyWebApiException e) {
			Logger.getInstance().log("Error" + e.getMessage());
		}
		
	}
	
	public TrackData getNextRecommendation() {

		try {

			do {
				final Recommendations recommendations = spotifyApi.getRecommendations()
						.limit(10)
						.market(CountryCode.US)
						.seed_genres("pop, rock")
						.build()
						.execute();

				TrackSimplified[] simplifiedTracks = recommendations.getTracks();

				Optional<TrackSimplified> tOptional = Arrays.stream(simplifiedTracks)
						.filter((t) -> t.getPreviewUrl() != null)
						.findFirst();

				if(tOptional.isPresent()) {
					Track t = spotifyApi.getTrack(tOptional.get().getId()).build().execute();
					Artist a = spotifyApi.getArtist(t.getArtists()[0].getId()).build().execute();
					return new TrackData(){{
						setName(t.getName());
						setPreviewUrl(t.getPreviewUrl());
						setDuration(t.getDurationMs());
						setAlbum(t.getAlbum().getName());
						setArtists(Arrays.stream(t.getArtists()).map(ArtistSimplified::getName).collect(Collectors.joining(", ")));
						setImageUrl(Arrays.stream(t.getAlbum().getImages()).max(Comparator.comparingInt(com.wrapper.spotify.model_objects.specification.Image::getWidth)).get().getUrl());
						setGenre(a.getGenres()[0]);
					}};
				}

			} while(true);

		} catch (IOException | SpotifyWebApiException e) {
	    	Logger.getInstance().log("Error: " + e.getMessage());
		}

		return null;
	}
	
}