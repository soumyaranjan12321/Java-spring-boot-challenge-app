// java
package com.soumya.ChallengeApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChallengeServiceTest {
    private ChallengeService service;

    @BeforeEach
    void setUp() {
        service = new ChallengeService();
    }

    private Challenge build(Long id, String month, String description) {
        return new Challenge(id, month, description);
    }

    @Test
    void addChallenge_assignsId_and_returnsTrue() {
        Challenge c = build(null, "January", "Learn Java");
        boolean added = service.addChallenge(c);

        assertTrue(added);
        assertNotNull(c.getId());
        assertEquals(1L, c.getId());
        assertEquals(1, service.getAllChallenges().size());
    }

    @Test
    void addChallenge_withNullInput_returnsFalse() {
        boolean added = service.addChallenge(null);
        assertFalse(added);
        assertEquals(0, service.getAllChallenges().size());
    }

    @Test
    void addChallenge_multipleAdds_incrementsId() {
        Challenge c1 = build(null, "January", "A");
        Challenge c2 = build(null, "February", "B");
        service.addChallenge(c1);
        service.addChallenge(c2);

        assertEquals(1L, c1.getId());
        assertEquals(2L, c2.getId());
        assertEquals(2, service.getAllChallenges().size());
    }

    @Test
    void getAllChallenges_returnsAllAdded() {
        service.addChallenge(build(null, "January", "A"));
        service.addChallenge(build(null, "February", "B"));
        service.addChallenge(build(null, "March", "C"));

        List<Challenge> all = service.getAllChallenges();
        assertEquals(3, all.size());
    }

    @Test
    void getChallenge_byMonth_isCaseInsensitive() {
        service.addChallenge(build(null, "January", "A"));
        service.addChallenge(build(null, "january", "B"));
        service.addChallenge(build(null, "February", "C"));

        List<Challenge> matches = service.getChallenge("JANUARY");
        assertEquals(2, matches.size());
        for (Challenge ch : matches) {
            assertTrue(ch.getMonth().equalsIgnoreCase("january"));
        }
    }

    @Test
    void getChallenge_noMatches_returnsEmpty() {
        service.addChallenge(build(null, "January", "A"));
        List<Challenge> matches = service.getChallenge("December");
        assertTrue(matches.isEmpty());
    }

    @Test
    void getChallenge_emptyService_returnsEmpty() {
        List<Challenge> matches = service.getChallenge("January");
        assertTrue(matches.isEmpty());
    }

    @Test
    void updateChallenge_existingId_updatesAndReturnsTrue() {
        Challenge c = build(null, "April", "Old description");
        service.addChallenge(c);
        Long id = c.getId();

        Challenge updated = build(null, "April", "Updated description");
        boolean result = service.updateChallenge(id, updated);

        assertTrue(result);
        List<Challenge> all = service.getAllChallenges();
        assertEquals(1, all.size());
        assertEquals("Updated description", all.get(0).getDescription());
    }

    @Test
    void updateChallenge_existingId_updatesMonth() {
        Challenge c = build(null, "April", "Description");
        service.addChallenge(c);
        Long id = c.getId();

        Challenge updated = build(null, "May", "Description");
        boolean result = service.updateChallenge(id, updated);

        assertTrue(result);
        assertEquals("May", service.getAllChallenges().get(0).getMonth());
    }

    @Test
    void updateChallenge_nonExistingId_returnsFalse() {
        Challenge updated = build(null, "May", "X");
        boolean result = service.updateChallenge(999L, updated);
        assertFalse(result);
    }

    @Test
    void deleteChallenge_existingId_removesAndReturnsTrue() {
        Challenge c1 = build(null, "June", "A");
        Challenge c2 = build(null, "July", "B");
        service.addChallenge(c1);
        service.addChallenge(c2);

        boolean deleted = service.deleteChallenge(c1.getId());
        assertTrue(deleted);
        assertEquals(1, service.getAllChallenges().size());
        assertEquals(c2.getId(), service.getAllChallenges().get(0).getId());
    }

    @Test
    void deleteChallenge_nonExistingId_returnsFalse() {
        assertFalse(service.deleteChallenge(12345L));
    }

    @Test
    void deleteChallenge_multipleDeletes() {
        Challenge c1 = build(null, "June", "A");
        Challenge c2 = build(null, "July", "B");
        Challenge c3 = build(null, "August", "C");
        service.addChallenge(c1);
        service.addChallenge(c2);
        service.addChallenge(c3);

        assertTrue(service.deleteChallenge(c1.getId()));
        assertTrue(service.deleteChallenge(c3.getId()));
        assertEquals(1, service.getAllChallenges().size());
        assertEquals(c2.getId(), service.getAllChallenges().get(0).getId());
    }
}
