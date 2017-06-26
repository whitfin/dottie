package io.whitfin.dottie.splitter;

import io.whitfin.dottie.exception.NotationException;
import io.whitfin.dottie.segment.NotationSegment;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class NotationSplitterTest {

    @Test
    public void testCreatingSplitter() {
        new NotationSplitter();
    }

    @Test
    public void testSplittingSingleKey() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("test");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 1);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "test");
    }

    @Test
    public void testSplittingBasicKeys() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("test.test");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 2);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "test");

        NotationSegment secondKey = keys.get(1);

        Assert.assertNotNull(secondKey);
        Assert.assertTrue(secondKey.isProperty());
        Assert.assertEquals(secondKey.asProperty().value(), "test");
    }

    @Test
    public void testSplittingIndexKeys() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("[0]");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 1);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isIndex());
        Assert.assertEquals(firstKey.asIndex().value(), 0);
    }

    @Test
    public void testSplittingMultipleIndexKeys() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("[0][0]");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 2);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isIndex());
        Assert.assertEquals(firstKey.asIndex().value(), 0);

        NotationSegment secondKey = keys.get(1);

        Assert.assertNotNull(secondKey);
        Assert.assertTrue(secondKey.isIndex());
        Assert.assertEquals(secondKey.asIndex().value(), 0);
    }

    @Test
    public void testSplittingBasicKeysAfterIndexKeys() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("[0].test");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 2);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isIndex());
        Assert.assertEquals(firstKey.asIndex().value(), 0);

        NotationSegment secondKey = keys.get(1);

        Assert.assertNotNull(secondKey);
        Assert.assertTrue(secondKey.isProperty());
        Assert.assertEquals(secondKey.asProperty().value(), "test");
    }

    @Test
    public void testSplittingIndexKeysAfterBasicKeys() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("test[0]");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 2);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "test");

        NotationSegment secondKey = keys.get(1);

        Assert.assertNotNull(secondKey);
        Assert.assertTrue(secondKey.isIndex());
        Assert.assertEquals(secondKey.asIndex().value(), 0);
    }

    @Test
    public void testSplittingAnEmptyPropertyKey() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("test[\"\"]");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 2);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "test");

        NotationSegment secondKey = keys.get(1);

        Assert.assertNotNull(secondKey);
        Assert.assertTrue(secondKey.isProperty());
        Assert.assertEquals(secondKey.asProperty().value(), "");
    }

    @Test
    public void testSplittingPropertyKeyWithSingleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("['test']");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 1);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "test");
    }

    @Test
    public void testSplittingPropertyKeyWithDoubleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("[\"test\"]");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 1);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "test");
    }

    @Test
    public void testSplittingBasicKeysAfterPropertyKeysWithSingleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("['test'].test");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 2);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "test");

        NotationSegment secondKey = keys.get(1);

        Assert.assertNotNull(secondKey);
        Assert.assertTrue(secondKey.isProperty());
        Assert.assertEquals(secondKey.asProperty().value(), "test");
    }

    @Test
    public void testSplittingBasicKeysAfterPropertyKeysWithDoubleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("[\"test\"].test");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 2);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "test");

        NotationSegment secondKey = keys.get(1);

        Assert.assertNotNull(secondKey);
        Assert.assertTrue(secondKey.isProperty());
        Assert.assertEquals(secondKey.asProperty().value(), "test");
    }

    @Test
    public void testSplittingIndexKeysAfterPropertyKeysWithSingleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("['test'][0]");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 2);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "test");

        NotationSegment secondKey = keys.get(1);

        Assert.assertNotNull(secondKey);
        Assert.assertTrue(secondKey.isIndex());
        Assert.assertEquals(secondKey.asIndex().value(), 0);
    }

    @Test
    public void testSplittingIndexKeysAfterPropertyKeysWithDoubleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("[\"test\"][0]");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 2);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "test");

        NotationSegment secondKey = keys.get(1);

        Assert.assertNotNull(secondKey);
        Assert.assertTrue(secondKey.isIndex());
        Assert.assertEquals(secondKey.asIndex().value(), 0);
    }

    @Test
    public void testSplittingIntegerPropertiesWithSingleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("['0']");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 1);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "0");
    }

    @Test
    public void testSplittingIntegerPropertiesWithDoubleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("[\"0\"]");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 1);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "0");
    }

    @Test
    public void testSplittingSpecialKeysUsingSingleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("[']]][[[']");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 1);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "]]][[[");
    }

    @Test
    public void testSplittingSpecialKeysUsingDoubleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("[\"]]][[[\"]");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 1);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "]]][[[");
    }

    @Test
    public void testSplittingMismatchingSingleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("['te'st']");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 1);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "te'st");
    }

    @Test
    public void testSplittingMismatchingDoubleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("[\"te\"st\"]");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 1);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "te\"st");
    }

    @Test
    public void testSplittingDottedPropertyKeysWithSingleQuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("['test.test']");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 1);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "test.test");
    }

    @Test
    public void testSplittingDottedPropertyKeysWithDoubleuotes() throws Exception {
        List<NotationSegment> keys = NotationSplitter.split("[\"test.test\"]");

        Assert.assertNotNull(keys);
        Assert.assertEquals(keys.size(), 1);

        NotationSegment firstKey = keys.get(0);

        Assert.assertNotNull(firstKey);
        Assert.assertTrue(firstKey.isProperty());
        Assert.assertEquals(firstKey.asProperty().value(), "test.test");
    }

    @Test(expectedExceptions = NotationException.class, expectedExceptionsMessageRegExp = "Unable to parse 'test.1' at character '1', column 6!")
    public void throwErrorWhenProvidedInvalidKey() throws Exception {
        NotationSplitter.split("test.1");
    }

    @Test(expectedExceptions = NotationException.class, expectedExceptionsMessageRegExp = "Unable to parse 'test.\\['test']' at character '\\[', column 6!")
    public void throwErrorWhenProvidedInvalidBracketNotation() throws Exception {
        NotationSplitter.split("test.['test']");
    }

    @Test(expectedExceptions = NotationException.class, expectedExceptionsMessageRegExp = "Unable to parse 'test.\\[0]' at character '\\[', column 6!")
    public void throwErrorWhenProvidedInvalidArrayNotation() throws Exception {
        NotationSplitter.split("test.[0]");
    }

    @Test(expectedExceptions = NotationException.class, expectedExceptionsMessageRegExp = "Unable to parse 'test\\[test]' at character 't', column 6!")
    public void throwErrorWhenProvidedInvalidArrayIndexNotation() throws Exception {
        NotationSplitter.split("test[test]");
    }

    @Test(expectedExceptions = NotationException.class, expectedExceptionsMessageRegExp = "Unable to parse key with trailing dot!")
    public void throwErrorWhenProvidedTrailingDot() throws Exception {
        NotationSplitter.split("test.");
    }

    @Test(expectedExceptions = NotationException.class, expectedExceptionsMessageRegExp = "Unable to parse key with trailing bracket!")
    public void throwErrorWhenProvidedTrailingBracket() throws Exception {
        NotationSplitter.split("test[");
    }

    @Test(expectedExceptions = NotationException.class, expectedExceptionsMessageRegExp = "Unable to parse '\\['test]' at character '\\[', column 1!")
    public void throwErrorWhenProvidedUnmatchedQuotes() throws Exception {
        NotationSplitter.split("['test]");
    }

    @Test(expectedExceptions = NotationException.class, expectedExceptionsMessageRegExp = "Unable to parse 'test..test' at character '.', column 6!")
    public void throwErrorWhenProvidedSequentialDots() throws Exception {
        NotationSplitter.split("test..test");
    }
}
