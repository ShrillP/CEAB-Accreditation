/**
 * Author: Shrill Patel 
 * Revised: Mar 16, 2021
 * 
 * Description: A template module for learning outcomes.
 */

package src;

/**
 * @brief A template module defining learning outcomes.
 * @details This module inherits the Measures class for measuring the learning outcomes.
 */
public class LOsT implements Measures {

	private String name;
	private int n_blw;
	private int n_mrg;
	private int n_mts;
	private int n_exc;

	/**
	 * @brief A constructor which initializes a learning outcome object
	 * @param topic Name of the topic for the learning outcome
	 * @param nblw A real number representing the students below the average
	 * @param nmrg A real number representing the students marginal with 
	 * respect to the average
	 * @param nmts A real number representing the students that meet the average
	 * @param nexc A real number representing the students that exceed expectations
	 */
	public LOsT(String topic, int nblw, int nmrg, int nmts, int nexc) {
		if(nblw < 0 || nmrg < 0 || nmts < 0 || nexc < 0)
			throw new IllegalArgumentException("Inputs should be natural numbers greater than zero.");
		if(nblw == 0 && nmrg == 0 && nmts == 0 && nexc == 0)
			throw new IllegalArgumentException("All inputs cannot be zero. Must be numbers greater than zero.");
		this.name = topic;
		this.n_blw = nblw;
		this.n_mrg = nmrg;
		this.n_mts = nmts;
		this.n_exc = nexc;
	}

 	/**
	 * @brief Gets the name of the learning outcome
	 * @return The name of the learning outcome
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @brief Checks if the name of 2 learning outcomes are the same
	 * @param o A learning outcome object
	 * @return True if both of their names are the same, otherwise false
	 */
	@Override
	public boolean equals(Object o) {
		LOsT object = (LOsT) o;
		return this.name == object.getName();	
	}

	/**
	 * Resource From: https://stackoverflow.com/questions/2265503/why-do-i-need-to-override-the-equals-and-hashcode-methods-in-java
	 */
	@Override
	public int hashCode() {
		final int p = 31;
		int out = 1;
		out = p * out + ((this.name == null) ? 0 : this.name.hashCode());
		return out;
	}

	public double[] measures() {
		double[] groups = {(double) this.n_blw, (double) this.n_mrg, (double) this.n_mts, (double) this.n_exc};
		if(!Norm.getNLOs())
			return groups;
		return Services.normal(groups);
	}

	public double[] measures(IndicatorT ind) {
		throw new UnsupportedOperationException("Requested operation is not supported.");
	}

	public double[] measures(AttributeT att) {
		throw new UnsupportedOperationException("Requested operation is not supported.");
	}
}
