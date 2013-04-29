package analysis;

import java.util.Date;

import database.Article;

import manager.Manager;
import manager.IPreferences;

/**
 * Implementation of IScorer interface that returns similarity based on the times that the articles were published.
 * @author Jamison Bradley
 */
public class DateScorer implements IScorer {
	
	private IPreferences pref;
	
	public DateScorer(){
		pref = Manager.getPreferences();
	}
	
	@Override
	public double score(Article aOne, Article aTwo){
		Date dOne = aOne.getDate();
		Date dTwo = aTwo.getDate();
		
		long diff = dOne.getTime() - dTwo.getTime();
		if (diff < 0){
			diff *= -1;
		}
		//convert time difference in ms to hours
		long timeDifHours = (diff / 1000) / 3600;
		
		//maximum number of hours that two articles can be matched to
		long maxHours = pref.getMaximumDateMatch() * 24;
		
		return 100 * (((double) (maxHours - timeDifHours)) / ((double) maxHours));
	}
	
}
