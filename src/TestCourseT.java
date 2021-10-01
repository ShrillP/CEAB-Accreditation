/**
 * Author: Shrill Patel
 * Revised: 03/28/21
 * 
 * Description: Test class for CourseT.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestCourseT {

	private CourseT c1, c2, c3, c4, c5;
	private LOsT[] outcomes = new LOsT[] {new LOsT("topic 1", 1, 5, 2, 2), new LOsT("topic 2", 5, 6, 7, 2),
			new LOsT("topic 3", 10, 20, 15, 5), new LOsT("topic 4", 25, 5, 10, 10),
			new LOsT("topic 5", 3, 2, 5, 15), new LOsT("topic 6", 4, 4, 1, 1)};

	@Before
	public void setUp() {
		Norm.setNorms(false, false, false);
		c1 = new CourseT("Course 1", new IndicatorT[0]);
		c2 = new CourseT("Course 2", new IndicatorT[] {IndicatorT.standards});
		c3 = new CourseT("Course 3", new IndicatorT[] {IndicatorT.math, IndicatorT.desPrinciples,
			IndicatorT.math, IndicatorT.openEnded});
	}

	@After
	public void tearDown() {
		c1 = c2 = c3 = null;
	}

	@Test
    public void testGetName1() {
        assertTrue(c1.getName().equals("Course 1"));
    }

    @Test
    public void testGetName2() {
        assertTrue(c2.getName().equals("Course 2"));
    }

    @Test
    public void testGetName3() {
        assertTrue(c3.getName().equals("Course 3"));
    }

    @Test
    public void testGetIndicators1() {
    	HashSet<IndicatorT> out = new HashSet<IndicatorT>(Arrays.asList(c1.getIndicators()));
    	assertTrue(out.isEmpty());
    }

    @Test
    public void testGetIndicators2() {
    	HashSet<IndicatorT> out = new HashSet<IndicatorT>(Arrays.asList(c2.getIndicators()));
    	assertTrue(out.contains(IndicatorT.standards));
    }

    @Test
    public void testGetIndicators3() {
    	HashSet<IndicatorT> out = new HashSet<IndicatorT>(Arrays.asList(c3.getIndicators()));
    	assertTrue(out.contains(IndicatorT.math) && out.contains(IndicatorT.desPrinciples)
    		&& out.contains(IndicatorT.openEnded));
    }

    @Test
    public void testAddLO1() {
    	LOsT outcome = new LOsT("Test Learning Outcome 1", 1, 2, 3, 4);
    	c1.addLO(IndicatorT.math, outcome);
    	assertTrue(c1.getIndicators().length == 0);
    }

    @Test
    public void testAddLOandGetLOs2() {
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[1]);
    	HashSet<LOsT> result = new HashSet<LOsT>(Arrays.asList(c2.getLOs(IndicatorT.standards)));
    	assertTrue(result.contains(outcomes[0]) && result.contains(outcomes[1]) && result.size() == 2);
    }

    @Test
    public void testAddLOandGetLOs3() {
    	c3.addLO(IndicatorT.math, outcomes[0]);
    	c3.addLO(IndicatorT.math, outcomes[1]);
    	c3.addLO(IndicatorT.openEnded, outcomes[2]);
    	c3.addLO(IndicatorT.openEnded, outcomes[3]);
    	c3.addLO(IndicatorT.desPrinciples, outcomes[4]);
    	c3.addLO(IndicatorT.desPrinciples, outcomes[5]);
    	for(IndicatorT ind : c3.getIndicators()) {
    		LOsT[] result = c3.getLOs(ind);
    		HashSet<LOsT> out = new HashSet<LOsT>(Arrays.asList(result));
    		for(LOsT lo : result)
    			assertTrue(out.contains(lo));
    	}
    }

    @Test
    public void testAddLOandGetLOs4() {
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[1]);
    	c2.addLO(IndicatorT.standards, outcomes[1]);
    	HashSet<LOsT> result = new HashSet<LOsT>(Arrays.asList(c2.getLOs(IndicatorT.standards)));
    	assertTrue(result.contains(outcomes[0]) && result.contains(outcomes[1]) && result.size() == 2);
    }

    @Test
    public void testGetLOsConditional1() {
    	LOsT[] out = c1.getLOs(IndicatorT.specEngKnow);
    	assertTrue(out.length == 0);
    }

    @Test
    public void testGetLOsConditional2() {
    	LOsT[] out = c3.getLOs(IndicatorT.tools);
    	assertTrue(out.length == 0);
    }

    @Test
    public void testDelLO1() {
    	c1.delLO(IndicatorT.assumpt, outcomes[0]);
    	IndicatorT[] out = c1.getIndicators();
    	assertTrue(out.length == 0);
    }

    @Test
    public void testDelLO2() {
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[1]);
    	c2.delLO(IndicatorT.standards, outcomes[1]);
    	LOsT[] out = c2.getLOs(IndicatorT.standards);
    	assertTrue(out.length == 1 && out[0] == outcomes[0]);
    }

    @Test
    public void testDelLO3() {
    	HashSet<LOsT> out = new HashSet<LOsT>();
    	c3.addLO(IndicatorT.math, outcomes[0]);
    	c3.addLO(IndicatorT.math, outcomes[1]);
    	c3.addLO(IndicatorT.openEnded, outcomes[2]);
    	c3.addLO(IndicatorT.openEnded, outcomes[3]);
    	c3.delLO(IndicatorT.math, outcomes[0]);
    	c3.delLO(IndicatorT.openEnded, outcomes[3]);
    	out.addAll(Arrays.asList(c3.getLOs(IndicatorT.math)));
    	out.addAll(Arrays.asList(c3.getLOs(IndicatorT.openEnded)));
    	assertTrue(out.size() == 2 && out.contains(outcomes[1]) && out.contains(outcomes[2]));
    }

    @Test
    public void testDelLO4() {
    	HashSet<LOsT> out = new HashSet<LOsT>();
    	c3.addLO(IndicatorT.math, outcomes[0]);
    	c3.addLO(IndicatorT.math, outcomes[1]);
    	c3.addLO(IndicatorT.openEnded, outcomes[2]);
    	c3.addLO(IndicatorT.openEnded, outcomes[3]);
    	c3.delLO(IndicatorT.math, outcomes[0]);
    	c3.delLO(IndicatorT.openEnded, outcomes[3]);
    	out.addAll(Arrays.asList(c3.getLOs(IndicatorT.math)));
    	out.addAll(Arrays.asList(c3.getLOs(IndicatorT.openEnded)));
    	assertTrue(out.size() == 2 && out.contains(outcomes[1]) && out.contains(outcomes[2]));
    }

    @Test
    public void testDelLOInvalidIndicator1() {
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[1]);
    	c2.delLO(IndicatorT.tools, outcomes[4]);
    	HashSet<LOsT> out = new HashSet<LOsT>(Arrays.asList(c2.getLOs(IndicatorT.standards)));
    	assertTrue(out.size() == 2 && out.contains(outcomes[0]) && out.contains(outcomes[1]));
    }

    @Test
    public void testDelLOInvalidIndicator2() {
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[1]);
    	c2.delLO(IndicatorT.tools, outcomes[0]);
    	HashSet<LOsT> out = new HashSet<LOsT>(Arrays.asList(c2.getLOs(IndicatorT.standards)));
    	assertTrue(out.size() == 2 && out.contains(outcomes[0]) && out.contains(outcomes[1]));
    }

    @Test
    public void testMemberInvalidIndicator() {
    	assertTrue(!c1.member(IndicatorT.desPrinciples, outcomes));
    }

    @Test
    public void testMemberInvalidLearningOutcome() {
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[1]);
    	c2.addLO(IndicatorT.standards, outcomes[2]);
    	c2.addLO(IndicatorT.standards, outcomes[3]);
    	LOsT[] seqOuts = new LOsT[] {outcomes[0], outcomes[1], outcomes[2], outcomes[3], outcomes[4]};
    	assertTrue(!c2.member(IndicatorT.standards, seqOuts));
    }

    @Test
    public void testMemberInvalidSeqOutcomes() {
    	c3.addLO(IndicatorT.math, outcomes[0]);
    	c3.addLO(IndicatorT.math, outcomes[1]);
    	c3.addLO(IndicatorT.openEnded, outcomes[2]);
    	c3.addLO(IndicatorT.openEnded, outcomes[3]);
    	c3.addLO(IndicatorT.openEnded, outcomes[4]);
    	LOsT[] seqOuts = new LOsT[] {outcomes[0], outcomes[1], outcomes[5]};
    	assertTrue(!c3.member(IndicatorT.openEnded, seqOuts));
    }

    @Test
    public void testMember1() {
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[1]);
    	c2.addLO(IndicatorT.standards, outcomes[2]);
    	LOsT[] seqOuts = new LOsT[] {outcomes[0], outcomes[1], outcomes[2]};
    	assertTrue(c2.member(IndicatorT.standards, seqOuts));
    }

    @Test
    public void testMember2() {
    	c3.addLO(IndicatorT.math, outcomes[0]);
    	c3.addLO(IndicatorT.math, outcomes[1]);
    	c3.addLO(IndicatorT.openEnded, outcomes[2]);
    	c3.addLO(IndicatorT.openEnded, outcomes[3]);
    	LOsT[] seqOuts1 = new LOsT[] {outcomes[0], outcomes[1]};
    	LOsT[] seqOuts2 = new LOsT[] {outcomes[2], outcomes[3]};
    	assertTrue(c3.member(IndicatorT.math, seqOuts1) && c3.member(IndicatorT.openEnded, seqOuts2));
    }

    @Test (expected=UnsupportedOperationException.class)
    public void testMeasures() {
    	c1.measures();
    }

    @Test
    public void testMeasuresIndicatorInput1() {
    	double[] result = c1.measures(IndicatorT.tools);
    	double[] expected = new double[] {0.0, 0.0, 0.0, 0.0};
    	assertTrue(Arrays.equals(result, expected));
    }

    @Test
    public void testMeasuresIndicatorInput2() {
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[1]);
    	double[] result = c2.measures(IndicatorT.standards);
    	double[] expected = new double[] {6.0, 11.0, 9.0, 4.0};
    	assertTrue(Arrays.equals(result, expected));
    }

    @Test
    public void testMeasuresIndicatorInput3() {
    	Norm.setNInd(true);
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[1]);
    	double[] result = c2.measures(IndicatorT.standards);
    	double[] expected = new double[] {6.0/30, 11.0/30, 9.0/30, 4.0/30};
    	assertTrue(Arrays.equals(result, expected));
    }

    @Test
    public void testMeasuresIndicatorInput4() {
    	c3.addLO(IndicatorT.math, outcomes[3]);
    	c3.addLO(IndicatorT.math, outcomes[4]);
    	c3.addLO(IndicatorT.math, outcomes[5]);
    	double[] result = c3.measures(IndicatorT.math);
    	double[] expected = new double[] {32.0, 11.0, 16.0, 26.0};
    	assertTrue(Arrays.equals(result, expected));
    }

    @Test
    public void testMeasuresIndicatorInput5() {
    	Norm.setNInd(true);
    	c3.addLO(IndicatorT.desPrinciples, outcomes[3]);
    	c3.addLO(IndicatorT.desPrinciples, outcomes[4]);
    	c3.addLO(IndicatorT.desPrinciples, outcomes[5]);
    	double[] result = c3.measures(IndicatorT.desPrinciples);
    	double[] expected = new double[] {32.0/85, 11.0/85, 16.0/85, 26.0/85};
    	assertTrue(Arrays.equals(result, expected));
    }

    @Test
    public void testMeasuresAttributeInput1() {
    	AttributeT att = new AttributeT("Attribute 1", new IndicatorT[0]);
    	double[] out = c1.measures(att);
    	double[] expected = new double[] {0.0, 0.0, 0.0, 0.0};
    	assertTrue(Arrays.equals(out, expected));
    }

    @Test
    public void testMeasuresAttributeInput2() { 
    	Norm.setNInd(false);
    	Norm.setNAtt(false);
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[5]);
    	AttributeT att = new AttributeT("Attribute 1", new IndicatorT[] {IndicatorT.standards, 
    			IndicatorT.math, IndicatorT.tools, IndicatorT.specEngKnow, IndicatorT.desProcess});
    	double[] out = c2.measures(att);
    	double[] expected = new double[] {5.0, 9.0, 3.0, 3.0};
    	assertTrue(Arrays.equals(out, expected));
    }

    @Test
    public void testMeasuresAttributeInput3() { 
    	Norm.setNInd(true);
    	Norm.setNAtt(false);
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[5]);
    	AttributeT att = new AttributeT("Attribute 1", new IndicatorT[] {IndicatorT.standards, 
    			IndicatorT.math, IndicatorT.tools, IndicatorT.specEngKnow, IndicatorT.desProcess});
    	double[] out = c2.measures(att);
    	double[] expected = new double[] {5.0/20.0, 9.0/20.0, 3.0/20.0, 3.0/20.0};
    	assertTrue(Arrays.equals(out, expected));
    }

    @Test
    public void testMeasuresAttributeInput4() { 
    	Norm.setNInd(true);
    	Norm.setNAtt(true);
    	c2.addLO(IndicatorT.standards, outcomes[0]);
    	c2.addLO(IndicatorT.standards, outcomes[5]);
    	AttributeT att = new AttributeT("Attribute 1", new IndicatorT[] {IndicatorT.standards, 
    			IndicatorT.math, IndicatorT.tools, IndicatorT.specEngKnow, IndicatorT.desProcess});
    	double[] out = c2.measures(att);
    	double[] expected = new double[] {0.25/1.0, 0.45/1.0, 0.15/1.0, 0.15/1.0};
    	assertTrue(Arrays.equals(out, expected));
    }

    @Test
    public void testMeasuresAttributeInput5() { 
    	Norm.setNInd(false);
    	Norm.setNAtt(true);
    	c3.addLO(IndicatorT.math, outcomes[0]);
    	c3.addLO(IndicatorT.math, outcomes[5]);
    	c3.addLO(IndicatorT.desPrinciples, outcomes[1]);
    	c3.addLO(IndicatorT.desPrinciples, outcomes[4]);
    	c3.addLO(IndicatorT.openEnded, outcomes[2]);
    	c3.addLO(IndicatorT.openEnded, outcomes[3]);
    	AttributeT att = new AttributeT("Attribute 1", new IndicatorT[] {IndicatorT.math, 
    			IndicatorT.desPrinciples, IndicatorT.openEnded});
    	double[] out = c3.measures(att);
    	double[] expected = new double[] {48.0/165.0, 42.0/165.0, 40.0/165.0, 35.0/165.0};
    	assertTrue(Arrays.equals(out, expected));
    }
    
}
