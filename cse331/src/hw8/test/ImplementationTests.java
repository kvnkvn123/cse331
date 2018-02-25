package hw8.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import hw5.test.CheckAsserts;
import hw5.test.WeightedPathTest;

/**
 * ImplementationTests is a test suite used to encapsulate all
 * tests specific to your implementation of this problem set.
 *
 * For instance, unit tests for your individual methods would
 * go here.
 */

@RunWith(Suite.class)
@SuiteClasses({CheckAsserts.class, BuildingTest.class, PointTest.class, CampusPathModelTest.class, WeightedPathTest.class /* list classes here */ })

public final class ImplementationTests
{
    //this class is a placeholder for the suite, so it has no members.
}

