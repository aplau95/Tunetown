package application;
import java.util.*;

public class FavoritesData {

    private Stack<TrackData> favoritesList = new Stack<TrackData>();

    public FavoritesData(){
    }
    
    public TrackData getNextSong(){
        return favoritesList.pop();
    }

    public TrackData getAt(Integer index){
        return favoritesList.get(index);
    }
    
    public void addToFavorites(TrackData track){
        favoritesList.push(track);
    }

    public Integer numberOfFavorites(){
        return favoritesList.size();
    }

}