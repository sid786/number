package com.hcl.interviews;

import static com.hcl.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing stories.  
 * You will notice the TimeConverter has no implementation ... (hint)
 */
@RunWith(MockitoJUnitRunner.class)

public class NumberNamesFixture {
	
	//@Mock
	NumberNames  numberNamesImpl = new NumberNamesImpl();
	 private long input;
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void numberNamesAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("NumberNames.story")
                .run();
    }

    @When("the input is $input")
    public void whenInputIs(long input) {
    	 	this.input = input;
        //assertEquals("fourty-three million, one hundred twelve thousand, six hundred nine",numberNamesImpl.int2Text(input));
    }

    @Then("the output will be $")
    public void thenTheOutputShouldLookLike(String theExpectedOutput) {
    		String str = numberNamesImpl.int2Text(this.input);
    		assertEquals(str,theExpectedOutput);
    }
}
