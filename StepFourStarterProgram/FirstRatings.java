
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename)
    {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        for(CSVRecord record : parser)
        {
            Movie thisMovie = new Movie(record.get("id"), record.get("title"), 
            record.get("year"), record.get("genre"), record.get("director"), 
            record.get("country"), record.get("poster"), 
            Integer.parseInt(record.get("minutes")));
            
            movies.add(thisMovie);
        }
        
        return movies;
    }
    
    private void printAllMovies(ArrayList<Movie> movies)
    {
        for(Movie x : movies)
        {
            System.out.println(x);
        }
    }
    
    private int countMoviesInGenre(ArrayList<Movie> movies, String genre)
    {
        int counter=0;
        for(Movie x : movies)
        {
            if(x.getGenres().contains(genre))
            {
                counter++;
            }
        }
        return counter;
    }
    
    private int countMoviesLongerThan(ArrayList<Movie> movies, int minutes)
    {
        int counter=0;
        for(Movie x : movies)
        {
            if(x.getMinutes()>minutes)
            {
                counter++;
            }
        }
        return counter;
    }
    
    private HashMap<String, Integer> buildMoviesPerDirectorMap(ArrayList<Movie> movies)
    {
        HashMap<String,Integer> dirMap = new HashMap<String,Integer>();
        for(Movie x : movies)
        {
            String directors = x.getDirector();
            String directorArray[]=directors.split("[,]", 0);
            for(String director : directorArray)
            {
                director=director.trim();
                if(!dirMap.containsKey(director))
                {
                    dirMap.put(director,1);
                }
                else
                {
                    dirMap.put(director,dirMap.get(director)+1);
                }
            }
        }
        return dirMap;
    }
    
    private ArrayList<String> buildDirectorsOfMaxMovies (HashMap<String,Integer> dirMap)
    {
        ArrayList<String> maxDir = new ArrayList<String>();
        int maxVal =0;
        for(String s: dirMap.keySet())
        {
            int currVal = dirMap.get(s);
            if(currVal>maxVal)
            {
                maxDir.clear();
                maxVal=currVal;
                maxDir.add(s);
            }
            else if(currVal==maxVal)
            {
                maxDir.add(s);
            }
        }
        System.out.println("The max number of movies per director is " + maxVal);
        return maxDir;
    }
    
    private void printDirectorsOfMaxMovies(ArrayList<String> maxDir)
    {
        System.out.println("The directos are:");
        for(String s:maxDir)
        {
            System.out.println(s);
        }
    }
    
    private void testLoadMovies()
    {
        ArrayList<Movie> movies = loadMovies("ratedmoviesfull.csv");
        
        System.out.println("Number of movies in the list: " + movies.size());
        
        System.out.println("Number of Comedy movies: " 
        + countMoviesInGenre(movies,"Comedy"));
        
        System.out.println("Number of movies longer than 150 minutes: "
        + countMoviesLongerThan(movies,150));
        
        printDirectorsOfMaxMovies(buildDirectorsOfMaxMovies(buildMoviesPerDirectorMap(movies)));
    }
    
    public ArrayList<Rater> loadRaters(String filename)
    {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileResource fr = new FileResource("data/"+filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser)
        {
            String raterID = record.get("rater_id");
            boolean contains = checkIfContains(raters, raterID);
            if(!contains)
            {
                Rater currRater = new EfficientRater(raterID);
                currRater.addRating(record.get("movie_id"), 
                Integer.parseInt(record.get("rating")));
                raters.add(currRater);
            }
            else
            {
                Rater currRater = raters.get(getRaterIndex(raters, raterID));
                if(!currRater.hasRating(record.get("movie_id")))
                {
                    currRater.addRating(record.get("movie_id"), 
                    Integer.parseInt(record.get("rating")));
                    
                    raters.set(getRaterIndex(raters, raterID), currRater);
                }
            }
        }
        return raters;
    }
    
    private boolean checkIfContains(ArrayList<Rater> raters, String raterID)
    {
        for(Rater rater : raters)
        {
            if(rater.getID().equals(raterID))
            {
                return true;
            }
        }
        
        return false;
    }
    
    private int getRaterIndex(ArrayList<Rater> raters, String raterID)
    {
        int i =0;
        for(Rater rater : raters)
        {
            if(rater.getID().equals(raterID))
            {
                return i;
            }
            i++;
        }
        return -1;
    }
    
    private void printAllRaters(ArrayList<Rater> raters)
    {
        for (Rater rater:raters)
        {
            System.out.println("Rater ID: " + rater.getID() +
            " # of ratings "+ rater.numRatings());
            
        }
    }
    
    private int numberOfRatings(ArrayList<Rater> raters,String raterID)
    {
        int answer=0;
        for(Rater rater:raters)
        {
            if(rater.getID().equals(raterID))
            {
                answer=rater.numRatings();
            }
        }
        return answer;
    }
    
    private int maxRatingsPerRater(ArrayList<Rater> raters)
    {
        int answer=0;
        for(Rater rater:raters)
        {
            if(rater.numRatings()>answer)
            {
                answer=rater.numRatings();
            }
        }
        return answer;
    }
    
    private ArrayList<String> buildMaxRater (ArrayList<Rater> raters)
    {
        ArrayList<String> maxRaters = new ArrayList<String> ();
        int maxVal=maxRatingsPerRater(raters);
        for(Rater rater:raters)
        {
            if(rater.numRatings()==maxVal)
            {
                maxRaters.add(rater.getID());
            }
        }
        return maxRaters;
    }
    
    private void printMaxRaters(ArrayList<Rater> raters)
    {
        System.out.println("The raters with the max # of ratings are: ");
        ArrayList<String> maxRaters = buildMaxRater(raters);
        for(String s : maxRaters)
        {
            System.out.println(s);
        }
    }
    
    private int NumberOfRatingsOfMovie(String movieID, ArrayList<Rater> raters)
    {
        int answer =0;
        for(Rater rater:raters)
        {
            ArrayList<String> items = rater.getItemsRated();
            for(String s : items)
            {
                if(s.equals(movieID))
                {
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    private int totalMoviesRated(ArrayList<Rater> raters)
    {
        ArrayList<String> answer = new ArrayList<String>();
        for(Rater rater:raters)
        {
            ArrayList<String> items = rater.getItemsRated();
            for(String s : items)
            {
                if(!answer.contains(s))
                {
                    answer.add(s);
                }
            }
        }
        return answer.size();
    }
    
    public void testLoadRaters()
    {
        ArrayList<Rater> raters = loadRaters("ratings.csv");
        System.out.println("Number of raters: " + raters.size());
        //printAllRaters(raters);
        String raterID = "193";
        System.out.println(raterID + "'s Number of ratings: "+numberOfRatings(raters,raterID));
        System.out.println("Max Ratings per rater: " + maxRatingsPerRater(raters));
        System.out.println(buildMaxRater(raters).size() + " raters have the max number of ratings");
        printMaxRaters(raters);
        
        String movieID = "1798709";
        System.out.println(movieID+" has this many ratings: " + 
        NumberOfRatingsOfMovie(movieID,raters));
        
        System.out.println("Total number of movies rated: " + totalMoviesRated(raters));
    }
}
