package com.example.demo.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MembershipService {
    private MembershipRepository membershipRepository;

    @Autowired
    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Transactional
    public Membership createMembership(MembershipDTO.Create c) {
        return membershipRepository.save(Membership.createWith(c));
    }
}
