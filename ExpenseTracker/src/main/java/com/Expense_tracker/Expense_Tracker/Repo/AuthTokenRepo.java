package com.Expense_tracker.Expense_Tracker.Repo;

import com.Expense_tracker.Expense_Tracker.Model.Token.AuthToken;
import com.Expense_tracker.Expense_Tracker.Model.User;
import com.Expense_tracker.Expense_Tracker.Service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface AuthTokenRepo extends JpaRepository<AuthToken, Long> {
    AuthToken findFirstByTokenValue(String tokenValue);


 //   String getTokenByEmail(String email);

}

