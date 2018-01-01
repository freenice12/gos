package com.example.demo.members;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends PagingAndSortingRepository<Membership, Long>{
}
