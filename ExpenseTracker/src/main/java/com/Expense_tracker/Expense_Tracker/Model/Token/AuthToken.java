package com.Expense_tracker.Expense_Tracker.Model.Token;

import com.Expense_tracker.Expense_Tracker.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Entity
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationDateTime;

    //mapping
    @OneToOne
    @JoinColumn(name = "fk_user_Id")
    User user;

    //create a parameterized constructor which takes user as an argument
    public AuthToken(User user)
    {
        this.user = user;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationDateTime = LocalDateTime.now();
    }




}
