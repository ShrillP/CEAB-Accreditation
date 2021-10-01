/**
 * Author: Shrill Patel 
 * Revised: Mar 24, 2021
 * 
 * Description: A class used to define a program.
 */

package src;
import java.util.*;

/**
 * @brief This is a template module used to create a program out of various
 * courses.
 * @details An assumption made here is that the set of courses will not be
 * empty and at least one course will have non-zero measures for each attribute.
 */
public class ProgramT extends HashSet<CourseT> implements Measures {

	private HashSet<CourseT> s = this;

	/**
	 * @brief A measures method for measuring learning outcomes for students
	 * @throws UnsupportedOperationException if this measures method is used as
	 * it is not supported by this module
	 */
	public double[] measures() {
		throw new UnsupportedOperationException("Requested operation is not supported.");
	}

	/**
	 * @brief A measures method for measuring learning outcomes for students based
	 * off of an indicator of the course
	 * @param ind A indicator for the course
	 * @throws UnsupportedOperationException if this measures method is used as
	 * it is not supported by this module
	 */
	public double[] measures(IndicatorT ind) {
		throw new UnsupportedOperationException("Requested operation is not supported.");
	}

	/**
	 * @brief A measures method for measuring learning outcomes for students based off an attribute
	 * @param att A attribute for the course
	 * @return A sequence of doubles representing how well students are doing (4 categories)
	 */
	public double[] measures(AttributeT att) {
		double[] total = new double[4];
		for(CourseT c : s) {
			double[] temp = c.measures(att);
			for(int i = 0; i < total.length; i++)
				total[i] += temp[i];
		}
		return Services.normal(total);
	}

}
