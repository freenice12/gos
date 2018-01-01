package com.example.demo.members;

import org.junit.Test;

import static org.junit.Assert.*;

public class MembershipDTOTest {

    @Test
    public void testCreateDTO() {
        // Given
        MembershipDTO.Create dtoC = new MembershipDTO.Create();

        // When
        dtoC.setEmail("firstC@email.com");
        dtoC.setPassword("dtoc");
        dtoC.setAddress("nowhere");
        dtoC.setAdmin(true);

        // Then
        assertTrue(dtoC.getEmail().equals("firstC@email.com"));
        assertTrue(dtoC.getPassword().equals("dtoc"));
        assertTrue(dtoC.getAddress().equals("nowhere"));
        assertTrue(dtoC.isAdmin());
    }

}