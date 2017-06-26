package io.whitfin.dottie.segment;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NotationPropertyTest {

    @Test
    public void testNotationPropertyUsage() {
        NotationProperty prop1 = new NotationProperty("");
        NotationProperty prop2 = new NotationProperty("0");
        NotationProperty prop3 = new NotationProperty("my-test");
        NotationProperty prop4 = new NotationProperty("'test'");
        NotationProperty prop5 = new NotationProperty("\"test\"");

        Assert.assertEquals(prop1.escape(), "[\"\"]");
        Assert.assertEquals(prop2.escape(), "[\"0\"]");
        Assert.assertEquals(prop3.escape(), "[\"my-test\"]");
        Assert.assertEquals(prop4.escape(), "[\"'test'\"]");
        Assert.assertEquals(prop5.escape(), "[\"\\\"test\\\"\"]");
    }
}
