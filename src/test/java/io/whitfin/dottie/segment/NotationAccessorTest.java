package io.whitfin.dottie.segment;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NotationAccessorTest {

    @Test
    public void testNotationAccessorUsage() {
        String value = "accessor";
        NotationAccessor accessor = new NotationAccessor(value);

        Assert.assertFalse(accessor.isIndex());
        Assert.assertTrue(accessor.isProperty());
        Assert.assertEquals(accessor.escape(), value);
    }
}
