package model;

import static org.junit.Assert.*;

public class BuddyInfoTest {

    BuddyInfo buddy;

    @org.junit.Before
    public void setUp() throws Exception {
        buddy = new BuddyInfo("Simon", "(613)-111-1111", "my bedroom");
    }

    @org.junit.Test
    public void testGetName() {
        assertEquals("Simon",buddy.getName());
    }

    @org.junit.Test
    public void testGetNumber() {
        assertEquals("(613)-111-1111", buddy.getPhoneNumber());
    }

    @org.junit.Test
    public void testGetAddress() {
        assertEquals("my bedroom", buddy.getAddress());
    }
}