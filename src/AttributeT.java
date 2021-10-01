/**
 * Author: Shrill Patel 
 * Revised: Mar 13, 2021
 * 
 * Description: A template module that represents a attribute.
 */

package src;
import java.util.*;

/**
 * @brief A template module that represents attributes.
 * @details The attribute is represented by a name and a set of indicators.
 */
public class AttributeT {

	private String name;
	private IndicatorT[] s;

	/**
	 * @brief A constructor which initializes an attribute object 
	 * @param attribName Name of the attribute 
	 * @param indicators A sequence of indicators
	 */
	public AttributeT(String attribName, IndicatorT[] indicators) {
		HashSet<IndicatorT> indicatorSet = new HashSet<IndicatorT>();
		for(IndicatorT i : indicators)
			indicatorSet.add(i);

		this.name = attribName;
		this.s = indicatorSet.toArray(new IndicatorT[0]);
	}

 	/**
	 * @brief Gets the name of the attribute
	 * @return The name of the attribute
	 */
	public String getName() {
		return this.name;
	}

 	/**
	 * @brief Gets the indicators for the attribute
	 * @return The set of indicators for the attribute
	 */
 	public IndicatorT[] getIndicators() {
 		return this.s;
 	}

}
