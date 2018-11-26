package application;
import java.util.*;

public class FavoritesData {

    private Stack<TrackData> favoritesList = new Stack<TrackData>();
    
    public TrackData getNextSong(){
        return favoritesList.pop();
    }
    
    public void addToFavorites(TrackData track){
        favoritesList.push(track);
    }

}