package io.whitfin.dottie.joiner;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NotationJoinerTest {

    @Test
    public void testResettingJoiner() {
        NotationJoiner n1 = new NotationJoiner()
            .append("test");

        Assert.assertEquals(n1.toString(), n1.print());
        Assert.assertEquals(n1.print(), "test");

        NotationJoiner n2 = n1.reset();

        Assert.assertEquals(n2.toString(), n2.print());
        Assert.assertEquals(n2.print(), "");
    }

    @Test
    public void testAppendingAccessorSegments() {
        NotationJoiner n1 = new NotationJoiner()
            .append("Jasper")
            .append("Bob")
            .append("Charles");

        Assert.assertEquals(n1.toString(), n1.print());
        Assert.assertEquals(n1.print(), "Jasper.Bob.Charles");
    }

    @Test
    public void testAppendingIndexSegments() {
        NotationJoiner n1 = new NotationJoiner();

        for (int i = 1; i < 4; i++) {
            n1.append(i);
        }

        Assert.assertEquals(n1.toString(), n1.print());
        Assert.assertEquals(n1.print(), "[1][2][3]");
    }

    @Test
    public void testAppendingPropertySegments() {
        NotationJoiner n1 = new NotationJoiner()
            .append("1and1")
            .append("\"quote\"");

        Assert.assertEquals(n1.toString(), n1.print());
        Assert.assertEquals(n1.print(), "[\"1and1\"][\"\\\"quote\\\"\"]");
    }

    @Test
    public void testAppendingMixedSegments() {
        NotationJoiner n1 = new NotationJoiner()
            .append("a")
            .append("test")
            .append("with")
            .append("\"numbers\"")
            .append("1and2")
            .append("and3");

        Assert.assertEquals(n1.toString(), n1.print());
        Assert.assertEquals(n1.print(), "a.test.with[\"\\\"numbers\\\"\"][\"1and2\"].and3");
    }
}
