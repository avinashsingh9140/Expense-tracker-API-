package com.Expense_tracker.Expense_Tracker.Service;

import com.Expense_tracker.Expense_Tracker.Model.DTO.AuthInput;
import com.Expense_tracker.Expense_Tracker.Model.Token.AuthToken;
import com.Expense_tracker.Expense_Tracker.Repo.AuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    @Autowired
    AuthTokenRepo authTokenRepo;

    public void createToken(AuthToken token) {
        authTokenRepo.save(token);
    }

    public boolean authenticate(AuthInput authInput) {
        String email = authInput.getEmail();
        String tokenValue = authInput.getTokenValue();
        AuthToken token =  authTokenRepo.findFirstByTokenValue(tokenValue);
        if(token!=null)
        {
            return token.getUser().getEmail().equals(email);
        }else
        {
            return false;
        }
    }


    //    product add
    public boolean productAuthenticate(String email, String tokenVal) {
        AuthToken token =  authTokenRepo.findFirstByTokenValue(tokenVal);
        if(token!=null)
        {
            return token.getUser().getEmail().equals(email);
        }else
        {
            return false;
        }
    }

    public void deleteToken(String tokenValue) {
        AuthToken token =  authTokenRepo.findFirstByTokenValue(tokenValue);
        authTokenRepo.delete(token);
    }
}
