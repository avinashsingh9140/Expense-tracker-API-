package com.Expense_tracker.Expense_Tracker.Service;

import com.Expense_tracker.Expense_Tracker.Model.DTO.AuthInput;
import com.Expense_tracker.Expense_Tracker.Model.Products;
import com.Expense_tracker.Expense_Tracker.Repo.AuthTokenRepo;
import com.Expense_tracker.Expense_Tracker.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;


    @Autowired
    AuthTokenService authTokenService;





    // product add
    public String addProduct(Products newProducts, String tokenVal, String email) {

        if (authTokenService.productAuthenticate(email, tokenVal)) {
            productRepo.save(newProducts);
            return "Product Added Successfully!";
        }
        return "Authentication failed. Invalid token or email.";
    }

    public List<Products> getProductsByDate(LocalDate date, AuthInput authInput) {
        if (authTokenService.authenticate(authInput)) {
           return  productRepo.findProductsByDate(date);
        }
        return null;
    }
    public List<Products> getProductsByDateNonAuth(LocalDate date){
        return productRepo.findProductsByDate(date);
    }


}
