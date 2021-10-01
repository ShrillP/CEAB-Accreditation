/**
 * Author: Shrill Patel 
 * Revised: Mar 16, 2021
 * 
 * Description: An interface module for measures.
 */

package src;

public interface Measures {

	public double[] measures() throws UnsupportedOperationException;

	public double[] measures(IndicatorT ind) throws UnsupportedOperationException;

	public double[] measures(AttributeT att) throws UnsupportedOperationException;
	
}
