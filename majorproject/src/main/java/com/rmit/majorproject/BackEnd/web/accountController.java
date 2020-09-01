package com.rmit.majorproject.BackEnd.web;

import com.rmit.majorproject.BackEnd.model.Account;
import com.rmit.majorproject.BackEnd.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class accountController {
    @Autowired
    AccountService accountService;

    @PostMapping("")
    public ResponseEntity<?> createNewPerson(@Valid @RequestBody Account account,
                                             BindingResult result){
        if(result.hasErrors()){
            Map<String,String> errorMap =  new HashMap<>();
            for(FieldError error: result.getFieldErrors()){
                return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
            }

        }
        Account account1 = accountService.saveOrUpdateAccount(account);
        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
    }
}
