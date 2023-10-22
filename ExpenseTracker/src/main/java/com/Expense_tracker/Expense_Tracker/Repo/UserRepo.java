package com.Expense_tracker.Expense_Tracker.Repo;


import com.Expense_tracker.Expense_Tracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {


    User findFirstByEmail(String newEmail);

}
