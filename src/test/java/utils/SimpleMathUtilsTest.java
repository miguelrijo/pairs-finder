package utils;

import org.junit.jupiter.api.Test;

import javax.lang.model.util.SimpleElementVisitor6;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMathUtilsTest {

    @Test
    void isNumberBetween() {
        assert(SimpleMathUtils.isNumberBetween(1,0,5));
       assertFalse(SimpleMathUtils.isNumberBetween(6,0,5));
    }

    @Test
    void getSeconds() {
        var oneMinute = SimpleMathUtils.getSeconds("00:01", "HH:mm");
        assertEquals(60,oneMinute);
    }
}