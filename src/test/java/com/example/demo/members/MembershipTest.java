package com.example.demo.members;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MembershipTest {

    @Test
    public void testCreateMembership() {
        // Given
        Membership ms = new Membership();

        // When
        ms.setEmail("first@email.com");
        ms.setUsername("first user name");
        LocalDateTime joinedDate = LocalDateTime.now();
        ms.setJoined(joinedDate);
        ms.setUpdated(joinedDate.plusDays(1L));
        ms.setAdmin(false);

        // Then
        assertTrue(ms.getId() == null);
        assertTrue(ms.getPassword() == null);
        assertTrue(ms.getEmail().equals("first@email.com"));
        assertTrue(ms.getUsername().equals("first user name"));
        assertTrue(ms.getJoined().equals(joinedDate));
        assertTrue(ms.getUpdated().equals(joinedDate.plusDays(1L)));
        assertFalse(ms.isAdmin());
    }

    @Test
    public void testCreateMembershipWithDTO() {
        // Given
        MembershipDTO.Create c = new MembershipDTO.Create();
        c.setEmail("dto@email.com");
        c.setPassword("dto");

        // When
        Membership newMs = Membership.createWith(c);

        // Then
        assertTrue(newMs.getEmail().equals(c.getEmail()));
        assertTrue(newMs.getPassword().equals(c.getPassword()));
        assertTrue(newMs.getAddress() == null);
        assertFalse(newMs.isAdmin());
    }

}