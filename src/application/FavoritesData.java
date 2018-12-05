package application;
import java.util.*;

public class FavoritesData {

    private LinkedList<TrackData> favoritesList = new LinkedList<>(); 
    Integer totalDuration = 0;

    public FavoritesData(){
        //dummy contructor for dummies ;^)
    }
    
    public TrackData getNextSong(){
        return favoritesList.pop();
    }

    public TrackData getAt(Integer index){
        return favoritesList.get(index);
    }

    public TrackData peek(){
        return favoritesList.get(0);
    }
    
    public void addToFavorites(TrackData track){
        totalDuration += track.getDuration();
        favoritesList.addLast(track);
    }

    public Integer numberOfFavorites(){
        return favoritesList.size();
    }

    public Integer numberOfMinutes() {
        return totalDuration;
    }

    public List<TrackData> getFavoritesList() {
        return favoritesList;
    }

}