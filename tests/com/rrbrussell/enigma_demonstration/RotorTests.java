package com.rrbrussell.enigma_demonstration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RotorITest.class, RotorIITest.class, RotorIIITest.class,
	RotorIVTest.class, RotorVTest.class})
public class RotorTests {

}
