package io.whitfin.dottie.exception;

import org.testng.Assert;
import org.testng.annotations.Test;

@SuppressWarnings("ThrowableNotThrown")
public class NotationExceptionTest {

    @Test
    public void testDefaultMessage() {
        String message = "This is an exception";
        NotationException ex = new NotationException(message);

        Assert.assertEquals(ex.getMessage(), message);
    }

    @Test
    public void testPositionalMessage() {
        String key = "test1key";
        String message = "Unable to parse '" + key + "' at character '1', column 5!";
        NotationException ex = new NotationException(key, '1', 4);

        Assert.assertEquals(ex.getMessage(), message);
    }
}
