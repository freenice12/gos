package com.example.demo.members;

import com.example.demo.commons.errors.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MembershipController {

    private MembershipService membershipService;

    @Autowired
    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping("/memberships")
    public ResponseEntity createMembership(@RequestBody @Valid MembershipDTO.Create create,
                                           BindingResult result) {

        if (result.hasErrors()) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("잘못된 요청입니다. (" + result.getFieldError().getField() + " : " + result.getFieldError().getDefaultMessage() + ")");
            errorResponse.setCode("bad.request");
            // TODO BindingResult 안에 들어있는 에러 정보 사용하기.
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
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
