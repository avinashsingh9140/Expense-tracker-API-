package com.Expense_tracker.Expense_Tracker.Controller;

import com.Expense_tracker.Expense_Tracker.Model.DTO.AuthInput;
import com.Expense_tracker.Expense_Tracker.Model.Products;
import com.Expense_tracker.Expense_Tracker.Model.User;
import com.Expense_tracker.Expense_Tracker.Repo.ProductRepo;
import com.Expense_tracker.Expense_Tracker.Service.ProductService;
import com.Expense_tracker.Expense_Tracker.Service.UserService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    ProductService productService;

//    @Autowired
//    UserService userService;

    @Autowired
    ProductRepo productRepo;

    // add products
    @PostMapping("/Product")
    private String addProduct(@Valid @RequestBody Products newProducts, @RequestParam String tokenVal, @RequestParam String email){
        return productService.addProduct(newProducts, tokenVal, email);
    }

    //    Get Products
    @PostMapping("/ProductsByDate")
    public List<Products> getProductsByDate(@Valid @RequestBody AuthInput authInput, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return productService.getProductsByDate(date, authInput);
    }

    @GetMapping("/ProductsByDateNonAuth")
    public List<Products> getProductsByDateNonAuth(@Valid @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return productService.getProductsByDateNonAuth(date);
    }



//    // Auto delete products which is older than 3 months
//      @Scheduled(cron = "0 0 0 1 * ?") // Runs at midnight on the 1st day of every month
//       public void deleteOldProducts() {
//        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
//          productRepo.deleteByDateAddedBefore(threeMonthsAgo);
//}

}
