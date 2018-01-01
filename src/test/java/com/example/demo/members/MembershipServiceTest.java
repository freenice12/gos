package com.example.demo.members;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MembershipServiceTest {

    private MembershipRepository membershipRepository = mock(MembershipRepository.class);

    @Test
    public void test_Membership_Create() {
        // Given
        MembershipDTO.Create c = new MembershipDTO.Create();
        String email = "create@gmail.com";
        c.setEmail(email);
        String pwd = "create";
        c.setPassword(pwd);
        Membership mockMember = new Membership();
        mockMember.setEmail(email);
        mockMember.setPassword(pwd);
        when(membershipRepository.save(Membership.createWith(c))).thenReturn(mockMember);

        // When
        MembershipService membershipService = new MembershipService(membershipRepository);
        Membership membership = membershipService.createMembership(c);

        // Then
        assertTrue(membership.getEmail().equals(c.getEmail()));
        assertTrue(membership.getPassword().equals(c.getPassword()));
    }

}