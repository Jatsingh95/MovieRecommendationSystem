
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    
    private double getAverageByID(String id, int minimalRaters)
    {
        double total =0;
        double currVal=0;
        int countRaters=0;
        for(Rater rater: RaterDatabase.getRaters())
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
    
    private double dotProduct(Rater me, Rater r)
    {
        double answer=0;
        ArrayList<String> myRatings = me.getItemsRated();
        ArrayList<String> rRatings = r.getItemsRated();
        for(String s : myRatings)
        {
            if(rRatings.contains(s))
            {
                answer = answer + ((me.getRating(s)-5)*(r.getRating(s)-5));
            }
        }
        return answer;
    }
    
    private ArrayList<Rating> getSimilarities (String id)
    {
        ArrayList<Rating> Ratings = new ArrayList<Rating>();
        ArrayList<Rater> Raters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : Raters)
        {
            if(!r.getID().equals(id))
            {
                double dotProduct = dotProduct(me,r);
                if(dotProduct>0)
                {
                    Rating rting = new Rating(r.getID(),dotProduct);
                    Ratings.add(rting);
                }
            }
        }
        Collections.sort(Ratings, Collections.reverseOrder());
        return Ratings;
    }
    
    public ArrayList<Rating> getSimiliarRatings(String id, int numSimilarRaters, int minimalRaters)
    {
        ArrayList<Rating> wRatings = new ArrayList<Rating>();
        ArrayList<Rating> topRaters = getSimilarities(id);
        Filter af = new TrueFilter();
        ArrayList<String> movies = MovieDatabase.filterBy(af);
        
        for(String s : movies)
        {
            int countRaters = 0;
            double cumRating = 0;
            for(int i = 0 ; i<numSimilarRaters ; i++)
            {
                Rater r = RaterDatabase.getRater(topRaters.get(i).getItem());
                if(r.getRating(s)!=-1)
                {
                    countRaters++;
                    cumRating = cumRating + r.getRating(s)*topRaters.get(i).getValue();
                }
            }
            if(countRaters>=minimalRaters)
            {
                cumRating = cumRating/countRaters;
                Rating x = new Rating(s,cumRating);
                wRatings.add(x);
            }
        }
        
        Collections.sort(wRatings, Collections.reverseOrder());
        
        return wRatings;
    }
    
    public ArrayList<Rating> getSimiliarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter f)
    {
        ArrayList<Rating> wRatings = new ArrayList<Rating>();
        ArrayList<Rating> topRaters = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(f);
        
        for(String s : movies)
        {
            int countRaters = 0;
            double cumRating = 0;
            for(int i = 0 ; i<numSimilarRaters ; i++)
            {
                Rater r = RaterDatabase.getRater(topRaters.get(i).getItem());
                if(r.getRating(s)!=-1)
                {
                    countRaters++;
                    cumRating = cumRating + r.getRating(s)*topRaters.get(i).getValue();
                }
            }
            if(countRaters>=minimalRaters)
            {
                cumRating = cumRating/countRaters;
                Rating x = new Rating(s,cumRating);
                wRatings.add(x);
            }
        }
        
        Collections.sort(wRatings, Collections.reverseOrder());
        
        return wRatings;
    }
}
