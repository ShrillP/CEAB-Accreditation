/**
 * Author: Shrill Patel
 * Revised: 03/29/21
 * 
 * Description: Test class for ProgramT.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestProgramT {
	
	private ProgramT program;
	private LOsT[] outcomes = new LOsT[] {new LOsT("topic 1", 1, 5, 2, 2), new LOsT("topic 2", 5, 6, 7, 2),
			new LOsT("topic 3", 10, 20, 15, 5), new LOsT("topic 4", 25, 5, 10, 10),
			new LOsT("topic 5", 3, 2, 5, 15), new LOsT("topic 6", 4, 4, 1, 1)};

	@Before
	public void setUp() {
		CourseT math = new CourseT("Calculus", new IndicatorT[] {IndicatorT.math,
			IndicatorT.assumpt, IndicatorT.modelSelect});
		for(int i = 0; i < 3; i++) {
			math.addLO(IndicatorT.math, outcomes[i]);
			math.addLO(IndicatorT.assumpt, outcomes[i + 1]);
			math.addLO(IndicatorT.modelSelect, outcomes[i + 2]);
		}
		CourseT science = new CourseT("Science", new IndicatorT[] {IndicatorT.engInSoc, 
			IndicatorT.recogTheory, IndicatorT.estOutcomes, IndicatorT.tools}); 
		for(int i = 0; i < 3; i++) {
			science.addLO(IndicatorT.engInSoc, outcomes[i + 3]);
			science.addLO(IndicatorT.recogTheory, outcomes[i + 1]);
			science.addLO(IndicatorT.estOutcomes, outcomes[i + 2]);
		}
		CourseT programming = new CourseT("Programming", new IndicatorT[] {IndicatorT.desProcess,
			IndicatorT.ideaGeneration, IndicatorT.specEngKnow});
		for(int i = 0; i < 3; i++) {
			programming.addLO(IndicatorT.desProcess, outcomes[i]);
			programming.addLO(IndicatorT.ideaGeneration, outcomes[i]);
			programming.addLO(IndicatorT.specEngKnow, outcomes[i]);
		}
		program = new ProgramT();
		program.addAll(Arrays.asList(new CourseT[] {math, science, programming}));

	}

	@After
	public void tearDown() {
		program = null;
	}

	@Test (expected=UnsupportedOperationException.class)
	public void testMeasuresNoInput() {
		program.measures();
	}

	@Test (expected=UnsupportedOperationException.class)
	public void testMeasuresIndicatorInput() {
		program.measures(IndicatorT.math);
	}

	@Test
	public void testMeasuresAttributeInput1() {
		AttributeT att = new AttributeT("All Attributes", new IndicatorT[] {IndicatorT.assumpt,
			IndicatorT.engInSoc, IndicatorT.specEngKnow});
		double[] result = program.measures(att);
		double[] expected = new double[] {88.0/285.0, 73.0/285.0, 72.0/285.0, 52.0/285.0};
		assertTrue(Arrays.equals(result, expected));
	}

	@Test
	public void testMeasuresAttributeInput2() {
		AttributeT att = new AttributeT("Not Important to Program", new IndicatorT[] {IndicatorT.awarePEO,
			IndicatorT.suitableFund, IndicatorT.healthSafety});
		double[] result = program.measures(att);
		double[] expected = new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN};
		assertTrue(Arrays.equals(result, expected));
	}

}
