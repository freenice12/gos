package com.example.demo.members.abs;

import com.example.demo.DemoApplicationTests;
import com.example.demo.members.Membership;
import com.example.demo.members.MembershipDTO;
import com.example.demo.members.MembershipRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class AbstractTimeEntityTest extends DemoApplicationTests {

    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    public void test_Check_Abstract_Date_Entity() {
        // Given
        MembershipDTO.Create c = new MembershipDTO.Create();
        String email = "abstract@what.me";
        c.setEmail(email);
        membershipRepository.save(Membership.createWith(c));

        // When
        Iterable<Membership> allMemberships = membershipRepository.findAll();
        Membership firstMember = allMemberships.iterator().next();

        // Then
        assertTrue(firstMember.getEmail().equals(email));
        assertTrue(firstMember.getCreatedDate().isEqual(firstMember.getLastModifiedDate()));
    }

}