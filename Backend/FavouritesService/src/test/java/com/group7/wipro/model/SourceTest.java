package com.group7.wipro.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SourceTest {
    private static Source source;
    @BeforeAll
    public static void setUp() throws Exception {
        source = new Source(null,"Insidesport.in");
    }
    @Test
    public void testSourceIdShouldNotBeEmpty() {
        assertTrue(source.getId()==null, "Article source id should be null");
    }
    @Test
    public void testSourceNameShouldNotBeEmpty() {
        assertTrue(source.getName().equals("Insidesport.in"), "Article source name should be Insidesport.in");
    }
}