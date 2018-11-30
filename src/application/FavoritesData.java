package application;
import java.util.*;

public class FavoritesData {

    private Stack<TrackData> favoritesList = new Stack<TrackData>();
    Integer totalDuration = 0;

    public FavoritesData(){
    }
    
    public TrackData getNextSong(){
        return favoritesList.pop();
    }

    public TrackData getAt(Integer index){
        return favoritesList.get(index);
    }

    public TrackData peek(){
        return favoritesList.peek();
    }
    
    public void addToFavorites(TrackData track){
        totalDuration += track.getDuration();
        favoritesList.push(track);
    }

    public Integer numberOfFavorites(){
        return favoritesList.size();
    }

    public Integer numberOfMinutes(){
        return totalDuration;
    }

}