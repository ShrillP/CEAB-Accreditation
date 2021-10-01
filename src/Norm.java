/**
 * Author: Shrill Patel 
 * Revised: Mar 16, 2021
 * 
 * Description: An abstract object for the norm aggregation algorithm.
 */

package src;

/**
 * @brief A configuration module that implements a norm aggregation algorithm.
 * @details Normalizes learning outcomes, indicators, and attributes.
 */
public class Norm {

	private static boolean normLOs;
	private static boolean normInd;
	private static boolean normAtt;

	/**
	 * @brief Sets all of the norm values
	 * @param nLOs A boolean for learning outcomes
	 * @param nInd A boolean for indicators 
	 * @param nAtt A boolean for attributes 
	 */
	public static void setNorms(boolean nLOs, boolean nInd, boolean nAtt) {
		normLOs = nLOs;
		normInd = nInd;
		normAtt = nAtt;
	}

	/**
	 * @brief Gets the norm of the learning outcomes
	 * @return A boolean representing the current state of the learning outcomes
	 */
	public static boolean getNLOs() {
		return normLOs;
	}

	/**
	 * @brief Gets the norm of the indicators
	 * @return A boolean representing the current state of the indicators
	 */
	public static boolean getNInd() {
		return normInd;
	}

	/**
	 * @brief Gets the norm of the attributes
	 * @return A boolean representing the current state of the attributes
	 */
	public static boolean getNAtt() {
		return normAtt;
	}

	/**
	 * @brief Sets the norm of the learning outcomes
	 * @param nLOs A boolean representing the new state of the learning outcomes
	 */
	public static void setNLOs(boolean nLOs) {
		normLOs = nLOs;
	}

	/**
	 * @brief Sets the norm of the indicators
	 * @param nInd A boolean representing the new state of the indicators
	 */
	public static void setNInd(boolean nInd) {
		normInd = nInd;
	}

	/**
	 * @brief Sets the norm of the attributes
	 * @param nAtt A boolean representing the new state of the attributes
	 */
	public static void setNAtt(boolean nAtt) {
		normAtt = nAtt;
	}

}
