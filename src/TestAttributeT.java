/**
 * Author: Shrill Patel
 * Revised: 25/03/21
 * 
 * Description: Test class for AttributeT.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestAttributeT {

	private AttributeT att1, att2, att3, att4;
	private IndicatorT [] ind1,ind2, ind3, ind4;

	@Before
	public void setUp() {
		ind1 = new IndicatorT[0];
		ind2 = new IndicatorT[] {IndicatorT.ideaGeneration};
		ind3 = new IndicatorT[] {IndicatorT.assumpt, IndicatorT.desProcess, IndicatorT.assumpt};
		ind4 = new IndicatorT[] {IndicatorT.tools, IndicatorT.awarePEO, IndicatorT.math, IndicatorT.specEngKnow};
		att1 = new AttributeT("", ind1);
		att2 = new AttributeT("Attribute 2", ind2);
		att3 = new AttributeT("Attribute 3", ind3);
		att4 = new AttributeT("Attribute 4", ind4);
	}

    @After
    public void tearDown() {
        ind1 = ind2 = ind3 = ind4 = null;
        att1 = att2 = att3 = att4 = null; 
    }

    @Test
    public void testEmptyGetName1() {
        assertTrue(att1.getName().equals(""));
    }

    @Test
    public void testEmptyGetIndicators1() {
    	assertTrue(att1.getIndicators().length == 0);
    }

    @Test
    public void testGetName2() {
    	assertTrue(att2.getName().equals("Attribute 2"));
    }

    @Test
    public void testGetIndicators2() {
    	IndicatorT[] result = att2.getIndicators();
    	assertTrue(result[0] == IndicatorT.ideaGeneration);
    } 

    @Test
    public void testGetName3() {
    	assertTrue(att3.getName().equals("Attribute 3"));
    }

    @Test
    public void testGetIndicatorsRepeats3() {
        HashSet<IndicatorT> out = new HashSet<IndicatorT>(Arrays.asList(att3.getIndicators()));
    	assertTrue(out.contains(IndicatorT.desProcess) && out.contains(IndicatorT.assumpt));
    }

    @Test
    public void testGetName4() {
    	assertTrue(att4.getName().equals("Attribute 4"));
    }

    @Test
    public void testGetIndicators4() {
        HashSet<IndicatorT> out = new HashSet<IndicatorT>(Arrays.asList(att4.getIndicators()));
        assertTrue(out.contains(IndicatorT.awarePEO) && out.contains(IndicatorT.tools)
            && out.contains(IndicatorT.math) && out.contains(IndicatorT.specEngKnow));
    }

}
