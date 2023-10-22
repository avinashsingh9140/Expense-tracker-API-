package com.Expense_tracker.Expense_Tracker.Model.DTO;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthInput {

    @Email
    private String email;
    private String tokenValue;
}
