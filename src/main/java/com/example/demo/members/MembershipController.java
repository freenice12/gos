package com.example.demo.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MembershipController {

    private MembershipService membershipService;

    @Autowired
    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping("/memberships")
    public ResponseEntity createMembership(@RequestBody MembershipDTO.Create create,
                                           BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(new RuntimeException("something wrong!"), HttpStatus.BAD_REQUEST);
        }
        Membership m = membershipService.createMembership(create);
        System.out.println(m);
        return new ResponseEntity(m, HttpStatus.CREATED);
    }

    @GetMapping("/memberships")
    public ResponseEntity getMemberships() {
        return new ResponseEntity(new Membership(), HttpStatus.OK);
    }
}
