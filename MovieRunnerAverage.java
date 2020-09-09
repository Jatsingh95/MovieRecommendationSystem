
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings()
    {
        String movieFile = "ratedmovies_short.csv";
        String ratingFile = "ratings_short.csv";
        SecondRatings sr = new SecondRatings(movieFile, ratingFile);
        System.out.println("Number of movies: " + sr.getMovieSize());
        System.out.println("Number of raters: " + sr.getRaterSize());
        
        ArrayList<Rating> AverageRatings = sr.getAverageRatings(3);
        Collections.sort(AverageRatings);
        for(Rating r: AverageRatings)
        {
            String id = r.getItem();
            String title = sr.getTitle(id);
            System.out.println(r.getValue() + "\t" + title);
        }
    }
    
    public void getAverageRatingOneMovie()
    {
        String movieFile = "ratedmovies_short.csv";
        String ratingFile = "ratings_short.csv";
        String title = "The Godfather";
        SecondRatings sr = new SecondRatings(movieFile, ratingFile);
        ArrayList<Rating> AverageRatings = sr.getAverageRatings(1);
        String id =sr.getID(title);
        for(Rating r: AverageRatings)
        {
            if(r.getItem().equals(id))
            {
                System.out.println(r.getValue() + "\t" + title);
                break;
            }
        }
    }
    
}
