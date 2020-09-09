
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile, String ratingsFile)
    {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(movieFile);
        myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getMovieSize()
    {
        return myMovies.size();
    }
    
    public int getRaterSize()
    {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters)
    {
        double total =0;
        double currVal=0;
        int countRaters=0;
        for(Rater rater: myRaters)
        {
            currVal=rater.getRating(id);
            if(currVal!=-1)
            {
                total=total+currVal;
                countRaters++;
            }
        }
        if(countRaters>=minimalRaters)
        {
            return total/countRaters;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<Rating> AverageRatings = new ArrayList<Rating>();
        for(Movie m : myMovies)
        {
            String id = m.getID();
            double avgRating = getAverageByID(id,minimalRaters);
            if(avgRating!=0.0)
            {
                Rating r = new Rating(id,avgRating);
                AverageRatings.add(r);
            }
        }
        return AverageRatings;
    }
    
    public String getTitle(String id)
    {
        for(Movie m  : myMovies)
        {
            if(m.getID().equals(id))
            {
                return m.getTitle();
            }
        }
        return "NO SUCH ID";
    }
    
    public String getID(String title)
    {
        for(Movie m  : myMovies)
        {
            if(m.getTitle().equals(title))
            {
                return m.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
}
