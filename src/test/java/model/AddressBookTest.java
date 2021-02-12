package model;

import static org.junit.Assert.*;

public class AddressBookTest {
    AddressBook book, book2;
    BuddyInfo buddy;

    @org.junit.Before
    public void setUp() throws Exception {
        book = new AddressBook();
        book2 = new AddressBook();
        buddy = new BuddyInfo("Simon", "(613)-111-1111", "my bedroom");
    }

    @org.junit.Test
    public void testAdd() {
        assertNotNull(book);
        assertEquals(0, book.size());
        book.addBuddy(buddy);
        assertEquals(1, book.size());
        assertEquals(0, book2.size()); //Ensure AddressBooks are separate
        book.addBuddy(buddy);
        book.addBuddy(buddy);
        assertEquals(3, book.size());
    }
}