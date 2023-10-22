package com.Expense_tracker.Expense_Tracker.Repo;

import com.Expense_tracker.Expense_Tracker.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Products, Integer> {
    List<Products> findProductsByDate(LocalDate date);

}
