/**
 * Author: Shrill Patel
 * Revised: 25/03/21
 * 
 * Description: A testing suit to test the learning outcomes class.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestLOsT
{

	private IndicatorT ind;
	private AttributeT att;
	private LOsT loExcep1, loExcep2, lo1, lo2, lo3;

	@Before
	public void setUp() {
		Norm.setNorms(true, true, true);
		ind = IndicatorT.recogTheory;
		att = new AttributeT("Attribute 1", new IndicatorT[] {IndicatorT.math, IndicatorT.tools});
		lo1 = new LOsT("Learning Outcome 1", 1, 5, 2, 2);
		lo2 = new LOsT("Learning Outcome 2", 5, 6, 7, 2);
		lo3 = new LOsT("Learning Outcome 3", 10, 20, 15, 5);
	} 

	@After
	public void tearDown() {
		ind = null;
		att = null;
		loExcep1 = loExcep2 = lo1 = lo2 = lo3 = null;
	}

    @Test (expected=IllegalArgumentException.class)
    public void testConstructorException1() {
        loExcep1 = new LOsT("Learning Outcome 1", 1, -1, 2, -2);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testConstructorException2() {
        loExcep2 = new LOsT("Learning Outcome 2", 0, 0, 0, 0);
    }

    @Test
    public void testGetName1() {
    	assertTrue(lo1.getName().equals("Learning Outcome 1"));
    }

    @Test
    public void testGetName2() {
    	assertTrue(lo2.getName().equals("Learning Outcome 2"));
    }

    @Test
    public void testGetName3() {
    	assertTrue(lo3.getName().equals("Learning Outcome 3"));
    }

    @Test
    public void testEquals1() {
    	loExcep1 = new LOsT("Learning Outcome 1", 1, 2, 3, 4);
    	assertTrue(lo1.equals(loExcep1));
    }

    @Test
    public void testEquals2() {
    	loExcep2 = new LOsT("Learning Outcome 2", 5, 6, 7, 8);
    	assertTrue(lo2.equals(loExcep2));
    }

    @Test
    public void testEquals3() {
    	assertTrue(!lo3.equals(lo1));
    }

    @Test
    public void testEquals4() {
        loExcep1 = new LOsT("Learning Outcome 1", 1, 5, 2, 2);
        assertTrue(lo1.equals(loExcep1));
    }

    @Test
    public void testEquals5() {
        loExcep2 = new LOsT("Learning Outcome 2", 5, 6, 7, 2);
        assertTrue(lo2.equals(loExcep2));
    }

    @Test
    public void testMeasuresNoInput1() {
    	Norm.setNInd(false);
    	double[] expected = {0.1, 0.5, 0.2, 0.2};
    	assertTrue(Arrays.equals(expected, lo1.measures()));
    }

    @Test
    public void testMeasuresNoInput2() {
    	Norm.setNAtt(false);
    	double[] expected = {0.25, 0.3, 0.35, 0.1};
    	assertTrue(Arrays.equals(expected, lo2.measures()));
    }

    @Test
    public void testMeasuresNoInput3() {
    	Norm.setNLOs(false);
    	double[] expected = {10.0, 20.0, 15.0, 5.0};
    	assertTrue(Arrays.equals(expected, lo3.measures()));
    }

    @Test (expected=UnsupportedOperationException.class)
    public void testMeasuresIndicatorInput() {
    	lo1.measures(ind);
    }

    @Test (expected=UnsupportedOperationException.class)
    public void testMeasuresAttributeInput() {
    	lo2.measures(att);
    }

}
