
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings()
    {
        String movieFile = "ratedmovies_short.csv";
        String ratingFile = "ratings_short.csv";
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize(ratingFile);
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        ArrayList<Rating> AverageRatings = fr.getAverageRatings(1);
        System.out.println("Movies found: " + AverageRatings.size());
        
        Collections.sort(AverageRatings);
        
        for(Rating r: AverageRatings)
        {
            String id = r.getItem();
            String title = MovieDatabase.getTitle(id);
            System.out.println(r.getValue() + "\t" + title);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre()
    {
        String movieFile = "ratedmovies_short.csv";
        String ratingFile = "ratings_short.csv";
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize(ratingFile);
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        AllFilters af = new AllFilters();
        Filter yf = new YearAfterFilter(1980);
        Filter gf = new GenreFilter("Romance");
        af.addFilter(yf);
        af.addFilter(gf);
        
        
        
        ArrayList<Rating> AverageRatings = fr.getAverageRatingsByFilter(1,af);
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
    
    public void printSimilarRatings()
    {
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize(ratingFile);
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        ArrayList<Rating> wRatings = fr.getSimiliarRatings("65",20,5);
        System.out.println(MovieDatabase.getTitle(wRatings.get(0).getItem()));
    }
    
    public void printSimilarRatingsByGenre()
    {
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize(ratingFile);
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        Filter f = new GenreFilter("Action");
        ArrayList<Rating> wRatings = fr.getSimiliarRatingsByFilter("65",20,5,f);
        System.out.println(MovieDatabase.getTitle(wRatings.get(0).getItem()));
    }
    
    public void printSimilarRatingsByDirector()
    {
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize(ratingFile);
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        Filter f = new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
        ArrayList<Rating> wRatings = fr.getSimiliarRatingsByFilter("1034",10,3,f);
        System.out.println(MovieDatabase.getTitle(wRatings.get(0).getItem()));
    }
    
    public void printSimilarRatingsByGenreAndMinutes()
    {
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize(ratingFile);
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        Filter gf = new GenreFilter("Adventure");
        Filter mf = new MinutesFilter(100,200);
        AllFilters f = new AllFilters();
        f.addFilter(gf);
        f.addFilter(mf);
        ArrayList<Rating> wRatings = fr.getSimiliarRatingsByFilter("65",10,5,f);
        System.out.println(MovieDatabase.getTitle(wRatings.get(0).getItem()));
    }
    
    public void printSimilarRatingsByYearAndMinutes()
    {
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize(ratingFile);
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        Filter gf = new YearAfterFilter(2000);
        Filter mf = new MinutesFilter(80,100);
        AllFilters f = new AllFilters();
        f.addFilter(gf);
        f.addFilter(mf);
        ArrayList<Rating> wRatings = fr.getSimiliarRatingsByFilter("65",10,5,f);
        System.out.println(MovieDatabase.getTitle(wRatings.get(0).getItem()));
    }
}
