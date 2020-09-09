
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    
    public void printAverageRatings()
    {
        String movieFile = "ratedmovies_short.csv";
        String ratingFile = "ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        ArrayList<Rating> AverageRatings = tr.getAverageRatings(1);
        System.out.println("Movies found: " + AverageRatings.size());
        
        Collections.sort(AverageRatings);
        
        for(Rating r: AverageRatings)
        {
            String id = r.getItem();
            String title = MovieDatabase.getTitle(id);
            System.out.println(r.getValue() + "\t" + title);
        }
    }
    
    public void printAverageRatingsByYear()
    {
        String movieFile = "ratedmovies_short.csv";
        String ratingFile = "ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        Filter yf = new YearAfterFilter(2000);
        ArrayList<Rating> AverageRatings = tr.getAverageRatingsByFilter(1,yf);
        System.out.println("Movies found: " + AverageRatings.size());
        
        Collections.sort(AverageRatings);
        
        for(Rating r: AverageRatings)
        {
            String id = r.getItem();
            String title = MovieDatabase.getTitle(id);
            int year = MovieDatabase.getYear(id);
            System.out.println(r.getValue() + "\t" + year + "\t" + title);
        }
    }
    
    public void printAverageRatingsByGenre()
    {
        String movieFile = "ratedmovies_short.csv";
        String ratingFile = "ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        Filter gf = new GenreFilter("Crime");
        ArrayList<Rating> AverageRatings = tr.getAverageRatingsByFilter(1,gf);
        System.out.println("Movies found: " + AverageRatings.size());
        
        Collections.sort(AverageRatings);
        
        for(Rating r: AverageRatings)
        {
            String id = r.getItem();
            String title = MovieDatabase.getTitle(id);
            String genre = MovieDatabase.getGenres(id);
            System.out.println(r.getValue() + "\t" + title);
            System.out.println(genre);
        }
    }
    
    public void printAverageRatingsByMinutes()
    {
        String movieFile = "ratedmovies_short.csv";
        String ratingFile = "ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        Filter mf = new MinutesFilter(110,170);
        ArrayList<Rating> AverageRatings = tr.getAverageRatingsByFilter(1,mf);
        System.out.println("Movies found: " + AverageRatings.size());
        
        Collections.sort(AverageRatings);
        
        for(Rating r: AverageRatings)
        {
            String id = r.getItem();
            String title = MovieDatabase.getTitle(id);
            int time = MovieDatabase.getMinutes(id);
            System.out.println(r.getValue() + "\t"+"Time: " + time + "\t"+ title);
        }
    }
    
    public void printAverageRatingsByDirectors()
    {
        String movieFile = "ratedmovies_short.csv";
        String ratingFile = "ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        Filter df = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> AverageRatings = tr.getAverageRatingsByFilter(1,df);
        System.out.println("Movies found: " + AverageRatings.size());
        
        Collections.sort(AverageRatings);
        
        for(Rating r: AverageRatings)
        {
            String id = r.getItem();
            String title = MovieDatabase.getTitle(id);
            String Director = MovieDatabase.getDirector(id);
            System.out.println(r.getValue() + "\t"+ title);
            System.out.println(Director);
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre()
    {
        String movieFile = "ratedmovies_short.csv";
        String ratingFile = "ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        AllFilters af = new AllFilters();
        Filter yf = new YearAfterFilter(1980);
        Filter gf = new GenreFilter("Romance");
        af.addFilter(yf);
        af.addFilter(gf);
        
        
        
        ArrayList<Rating> AverageRatings = tr.getAverageRatingsByFilter(1,af);
        System.out.println("Movies found: " + AverageRatings.size());
        
        Collections.sort(AverageRatings);
        
        for(Rating r: AverageRatings)
        {
            String id = r.getItem();
            String title = MovieDatabase.getTitle(id);
            String Genre = MovieDatabase.getGenres(id);
            int Year = MovieDatabase.getYear(id);
            System.out.println(r.getValue() + "\t"+ Year +"\t"+title);
            System.out.println("\t"+Genre);
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes()
    {
        String movieFile = "ratedmovies_short.csv";
        String ratingFile = "ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        AllFilters af = new AllFilters();
        Filter df = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
        Filter mf = new MinutesFilter(30,170);
        af.addFilter(df);
        af.addFilter(mf);
        
        
        
        ArrayList<Rating> AverageRatings = tr.getAverageRatingsByFilter(1,af);
        System.out.println("Movies found: " + AverageRatings.size());
        
        Collections.sort(AverageRatings);
        
        for(Rating r: AverageRatings)
        {
            String id = r.getItem();
            String title = MovieDatabase.getTitle(id);
            String Directors = MovieDatabase.getDirector(id);
            int minutes = MovieDatabase.getMinutes(id);
            System.out.println(r.getValue() + "\t"+ "Time: " + minutes +"\t"+title);
            System.out.println("\t"+Directors);
        }
    }
}
