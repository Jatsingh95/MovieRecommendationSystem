
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
    
    public ArrayList<String> getItemsToRate ()
    {
        ArrayList<String> items = new ArrayList<String>();
        
        //String movieFile = "ratedmoviesfull.csv";
        //String ratingFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        
        //RaterDatabase.initialize(ratingFile);
        //MovieDatabase.initialize(movieFile);

        
        ArrayList<Rating> AverageRatings = fr.getAverageRatings(15);

        
        Collections.sort(AverageRatings,Collections.reverseOrder());
        
        for(Rating r: AverageRatings)
        {
            String id = r.getItem();
            items.add(id);
            if(items.size()>=5)
            {
                break;
            }
        }
        
        return items;
    }
   
    public void printRecommendationsFor (String webRaterID)
    {
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> wRatings = fr.getSimiliarRatings(webRaterID, 10, 5);
        System.out.println("<table>");
        int i = 0;
        for(Rating r : wRatings)
        {
            System.out.println("<tr><td class=\"movieTitle\">"+ MovieDatabase.getTitle(r.getItem()) + "</td></tr>");
            i++;
            if(i>=5){break;}
        }
        System.out.println("</table>");
    }

}
