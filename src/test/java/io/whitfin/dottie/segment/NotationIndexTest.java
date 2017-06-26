package io.whitfin.dottie.segment;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NotationIndexTest {

    @Test
    public void testNotationIndexUsage() {
        NotationIndex index = new NotationIndex(123);

        Assert.assertTrue(index.isIndex());
        Assert.assertFalse(index.isProperty());
        Assert.assertEquals(index.escape(), "[123]");
    }
}
