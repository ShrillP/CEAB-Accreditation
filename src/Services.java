/**
 * Author: Shrill Patel 
 * Revised: Mar 16, 2021
 * 
 * Description: A module providing services.
 */

package src;

/**
 * @brief A module that provides services.
 * @details This class provides a way to normalize data.
 */
public class Services {

	/**
	 * @brief A method that calculates the normal of the given data
	 * @betails The given data is normalized by dividing each element by the
	 * sum of all of the given sequence
	 * @param v A sequence of real numbers
	 * @return The name of the attribute
	 */
	public static double[] normal(double[] v) {
		double[] out = new double[v.length];
		double sum = 0;
		for(double num : v)
			sum += num;
		for(int i = 0; i < out.length; i++)
			out[i] = v[i] / sum;
		return out;
	}

}
