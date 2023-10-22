package com.Expense_tracker.Expense_Tracker.Service;

import com.Expense_tracker.Expense_Tracker.Model.DTO.AuthInput;
import com.Expense_tracker.Expense_Tracker.Model.DTO.SignIn_Authentication;
import com.Expense_tracker.Expense_Tracker.Model.Token.AuthToken;
import com.Expense_tracker.Expense_Tracker.Model.User;
import com.Expense_tracker.Expense_Tracker.Repo.UserRepo;
import com.Expense_tracker.Expense_Tracker.Service.Email_Sender.EmailService;
import com.Expense_tracker.Expense_Tracker.Service.PasswordEncrypt.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
     UserRepo userRepo;

    @Autowired
    AuthTokenService authTokenService;


    public String addUser(User newUser) {
        String newEmail = newUser.getEmail();

        User existingUser = userRepo.findFirstByEmail(newEmail);

        if(existingUser != null)
        {
            return existingUser.getEmail() + " Already in use try with new email!!!";
        }else {
            String signUpPassword = newUser.getPassword();

            try {
                String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

                newUser.setPassword(encryptedPassword);


                // patient table - save patient
                userRepo.save(newUser);
                return newUser.getUserName() + " Registered Successfully!!!";

            } catch (NoSuchAlgorithmException e) {

                return "Internal Server issues while saving password, try again later!!!";
            }
        }
    }

    public String userSignIn(SignIn_Authentication signInInput) {

        String email = signInInput.getUserEmail();

        User existingUser = userRepo.findFirstByEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        String password = signInInput.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                AuthToken token  = new AuthToken(existingUser);

                if(EmailService.sendEmail(email,"OTP for Login ", token.getTokenValue())) {
                    authTokenService.createToken(token);
                    return "OTP sent successfully to " + existingUser.getEmail();
                }
                else {
                    return "error while generating token!!!";
                }

            }
            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }


    }

    public String userSignOut(AuthInput authInput) {
        if(authTokenService.authenticate(authInput)) {
            String tokenValue = authInput.getTokenValue();
            authTokenService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }
}
