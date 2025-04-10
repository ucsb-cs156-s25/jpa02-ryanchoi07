package edu.ucsb.cs156.spring.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamTest {
    private Team teamInstance;

    @BeforeEach
    public void initialize() {
        teamInstance = new Team("test-team");
    }

    @Test
    public void getNameReturnsExpectedValue() {
        assertEquals("test-team", teamInstance.getName());
    }

    @Test
    public void toStringFormatsCorrectly() {
        assertEquals("Team(name=test-team, members=[])", teamInstance.toString());
    }

    @Test
    public void equalObjectsHaveIdenticalHashCodes() {
        Team firstTeam = new Team();
        firstTeam.setName("exampleTeam");
        firstTeam.addMember("memberOne");

        Team secondTeam = new Team();
        secondTeam.setName("exampleTeam");
        secondTeam.addMember("memberOne");

        assertEquals(firstTeam.hashCode(), secondTeam.hashCode());
    }

    @Test
    public void sameObjectIsEqualToItself() {
        assertTrue(teamInstance.equals(teamInstance));
    }

    @Test
    public void nullObjectComparisonReturnsFalse() {
        assertFalse(teamInstance.equals(null));
    }

    @Test
    public void differentTypeComparisonReturnsFalse() {
        assertFalse(teamInstance.equals("Not a team object"));
    }

    @Test
    public void objectsWithSameFieldsAreEqual() {
        Team firstTeam = new Team("sampleTeam");
        firstTeam.addMember("Person1");
        
        Team secondTeam = new Team("sampleTeam");
        secondTeam.addMember("Person1");

        assertTrue(firstTeam.equals(secondTeam));
        assertTrue(secondTeam.equals(firstTeam));
    }

    @Test
    public void differentMembersAreNotEqual() {
        Team firstTeam = new Team("sameTeamName");
        firstTeam.addMember("Person1");

        Team secondTeam = new Team("sameTeamName");
        secondTeam.addMember("Person2");

        assertFalse(firstTeam.equals(secondTeam));
    }

    @Test
    public void differentNamesAreNotEqual() {
        Team firstTeam = new Team("teamName1");
        firstTeam.addMember("sameMember");

        Team secondTeam = new Team("teamName2");
        secondTeam.addMember("sameMember");

        assertFalse(firstTeam.equals(secondTeam));
    }

    @Test
    public void completelyDifferentTeamsAreNotEqual() {
        Team firstTeam = new Team("teamA");
        firstTeam.addMember("memberA");

        Team secondTeam = new Team("teamB");
        secondTeam.addMember("memberB");

        assertFalse(firstTeam.equals(secondTeam));
    }

    @Test
    public void hashCodeImplementationVerification() {
        Team verificationTeam = new Team("test-team");
        
        int computedHash = verificationTeam.hashCode();
        
        int nameComponentHash = "test-team".hashCode();
        int membersComponentHash = new ArrayList<String>().hashCode();
        int manuallyCalculatedHash = nameComponentHash | membersComponentHash;
        
        assertEquals(manuallyCalculatedHash, computedHash);
        assertNotEquals(0, computedHash);
    }
}