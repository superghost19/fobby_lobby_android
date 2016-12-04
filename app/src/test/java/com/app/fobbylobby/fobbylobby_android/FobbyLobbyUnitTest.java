package com.app.fobbylobby.fobbylobby_android;

import com.app.fobbylobby.fobbylobby_android.models.Agent;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class FobbyLobbyUnitTest {
    @Test
    public void testAgentInitialization() throws Exception {
        Agent a1 = new Agent("test agent", "123 W Chicago St", 123.4, 234.5, 5);
        assertEquals("test agent", a1.getName());
        assertEquals("123 W Chicago St", a1.getAddress());
        assertEquals(123.4, a1.getLatitute(), 0);
        assertEquals(234.5, a1.getLongitute(), 0);
        assertEquals(5, a1.getRating());
    }
}