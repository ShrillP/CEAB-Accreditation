/**
 * Author: Shrill Patel 
 * Revised: Mar 16, 2021
 * 
 * Description: This is a CourseT class used to define course engineers take.
 */

package src;
import java.util.*;

/**
 * @brief A template module representing a course that an engineer must take.
 * @details A course is made up of indicators which are composed of learning
 * outcomes that are measured for each student. These learning outcomes are 
 * course specific and the attributes are measured by CEAB and the Faculty of
 * Engineering. This class assumes that CourseT will not be used until the
 * indicators have be populated with learning outcomes. addLO must be called
 * at least once for each indicator that makes up a course. If the learning 
 * outcomes are removed, it will be assumed that not all of them will be
 * removed before the Course is used for an aggregation calculation.
 */
public class CourseT implements Measures {

	private String name;
	private HashMap<IndicatorT, HashSet<LOsT>> m;

	/**
	 * @brief A constructor which initializes a CourseT object
	 * @param courseName A string to give a name to a course
	 * @param indicators A sequence of indicators for the course
	 */
	public CourseT(String courseName, IndicatorT[] indicators) {
		HashMap<IndicatorT, HashSet<LOsT>> map = new HashMap<IndicatorT, HashSet<LOsT>>();
		for(IndicatorT indicator : indicators) {
			map.put(indicator, new HashSet<LOsT>());
		}
		this.name = courseName;
		this.m = map;
	}

	/**
	 * @brief A getter for the course name
	 * @return The string representing the name of the course
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @brief A getter for all the indicators for the course 
	 * @return A sequence of all the indicators that make up the course
	 */
	public IndicatorT[] getIndicators() {
		return this.m.keySet().toArray(new IndicatorT[0]);
	}

	/**
	 * @brief A getter for the learning outcomes for a given indicator
	 * @details Returns an empty list of learning outcomes if the passed
	 * in indicator is not in the hash map
	 * @param indicator A indicator of the course 
	 * @return A sequence of learning outcomes related to the indicator
	 */
	public LOsT[] getLOs(IndicatorT indicator) {
		if(!this.m.containsKey(indicator))
			return new LOsT[0];
		return this.m.get(indicator).toArray(new LOsT[0]);
	}

	/**
	 * @brief A method to add a learning outcome to an indicator for the course
	 * @details Uses the overridden equals and hash code methods to insert a 
	 * learning outcome with its appropriate indicator. Only adds the outcome if 
	 * the passed in indicator exists within the hash map.
	 * @param indicator A indicator of the course 
	 * @param outcome A learning outcome corresponding with the indicator
	 */
	public void addLO(IndicatorT indicator, LOsT outcome) {
		if(this.m.containsKey(indicator))
			this.m.get(indicator).add(outcome);
	}

	/**
	 * @brief A method to delete a learning outcome from an indicator
	 * @details Uses the overridden equals and hash code methods to make sure
	 * that the correct outcome is being deleted from the set. This makes sure
	 * that the passed in outcome is deleted by value instead of reference
	 * (if the objects are the same). Only deletes the outcome if the passed
	 * in indicator exists within the hash map.
	 * @param indicator A indicator of the course
	 * @param outcome A learning outcome corresponding with the indicator
	 */
	public void delLO(IndicatorT indicator, LOsT outcome) {
		if(this.m.containsKey(indicator))
			this.m.get(indicator).remove(outcome);
	}

	/**
	 * @brief Verifies all learning outcomes exist with its appropriate indicator
	 * @param indicator A indicator for the course
	 * @param outcomes A sequence of learning outcomes
	 * @return True if all learning outcomes exist for an indicator, otherwise false
	 */
	public boolean member(IndicatorT indicator, LOsT[] outcomes) {
		if(this.m.containsKey(indicator)) {
			for(LOsT outcome : outcomes) {
				if(!this.m.get(indicator).contains(outcome))
					return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * @brief A measures method for measuring learning outcomes for students
	 * @throws UnsupportedOperationException if this measures method is used as
	 * it is not supported by this module
	 */
	public double[] measures() {
		throw new UnsupportedOperationException("Requested operation is not supported.");
	}

	/**
	 * @brief A measures method for measuring learning outcomes for students based off an indicator
	 * @param ind A indicator for the course
	 * @return A sequence of doubles representing how well students are doing (4 categories)
	 */
	public double[] measures(IndicatorT ind) {
		LOsT[] outcomes = this.getLOs(ind);
		double[] measureInd = new double[4];
		if(outcomes.length == 0)
			return measureInd;
		for(LOsT outcome : this.getLOs(ind)) {
			for(int i = 0; i < measureInd.length; i++)
				measureInd[i] += outcome.measures()[i];
		}
		if(Norm.getNInd())
			return Services.normal(measureInd);
		else
			return measureInd;
	}

	/**
	 * @brief A measures method for measuring learning outcomes for students based off an attribute
	 * @param att A attribute for the course
	 * @return A sequence of doubles representing how well students are doing (4 categories)
	 */
	public double[] measures(AttributeT att) {
		IndicatorT[] indicators = att.getIndicators();
		double[] measureInd = new double[4];
		if(indicators.length == 0)
			return measureInd;
		for(IndicatorT indicator : indicators) {
			double[] temp = this.measures(indicator);
			for(int i = 0; i < measureInd.length; i++)
				measureInd[i] += temp[i];
		}
		if(Norm.getNAtt())
			return Services.normal(measureInd);
		else
			return measureInd;
	}

}
