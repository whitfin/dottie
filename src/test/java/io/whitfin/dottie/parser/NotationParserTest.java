package io.whitfin.dottie.parser;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NotationParserTest {

    @Test
    public void testCreatingParser() {
        new NotationParser();
    }

    @Test
    public void testCheckingAccessor() {
        String s1 = "accessor";
        String s2 = "valid123";
        String s3 = "invalid-accessor";
        String s4 = "1invalid";

        Assert.assertTrue(NotationParser.isAccessor(s1));
        Assert.assertTrue(NotationParser.isAccessor(s2));
        Assert.assertFalse(NotationParser.isAccessor(s3));
        Assert.assertFalse(NotationParser.isAccessor(s4));

        Assert.assertTrue(NotationParser.isSegment(s1));
        Assert.assertTrue(NotationParser.isSegment(s2));
        Assert.assertFalse(NotationParser.isSegment(s3));
        Assert.assertFalse(NotationParser.isSegment(s4));
    }

    @Test
    public void testCheckingIndex() {
        String s1 = "[123]";
        String s2 = "[123] ";
        String s3 = "[123 ]";

        Assert.assertTrue(NotationParser.isIndex(s1));
        Assert.assertFalse(NotationParser.isIndex(s2));
        Assert.assertFalse(NotationParser.isIndex(s3));

        Assert.assertTrue(NotationParser.isSegment(s1));
        Assert.assertFalse(NotationParser.isSegment(s2));
        Assert.assertFalse(NotationParser.isSegment(s3));
    }

    @Test
    public void testCheckingProperty() {
        String s1 = "[\"value\"]";
        String s2 = "['value']";
        String s3 = "[123]";
        String s4 = "value";

        Assert.assertTrue(NotationParser.isProperty(s1));
        Assert.assertTrue(NotationParser.isProperty(s2));
        Assert.assertFalse(NotationParser.isProperty(s3));
        Assert.assertFalse(NotationParser.isProperty(s4));

        Assert.assertTrue(NotationParser.isSegment(s1));
        Assert.assertTrue(NotationParser.isSegment(s2));
        Assert.assertTrue(NotationParser.isSegment(s3));
        Assert.assertTrue(NotationParser.isSegment(s4));
    }
}
