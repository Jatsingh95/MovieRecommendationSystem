
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsFile)
    {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsFile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> AverageRatings = new ArrayList<Rating>();
        for(String s : movies)
        {
            double avgRating = getAverageByID(s,minimalRaters);
            if(avgRating!=0.0)
            {
                Rating r = new Rating(s,avgRating);
                AverageRatings.add(r);
            }
        }
        return AverageRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> AverageRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String s : movies)
        {
            double avgRating = getAverageByID(s,minimalRaters);
            if(avgRating!=0.0)
            {
                Rating r = new Rating(s,avgRating);
                AverageRatings.add(r);
            }
        }
        return AverageRatings;
    }
}
