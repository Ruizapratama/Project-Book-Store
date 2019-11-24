package com.bookstore.BookStore.Project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.BookStore.Project.model.Book;
import com.bookstore.BookStore.Project.repository.BookRepository;

@RestController
@RequestMapping("/api/price")
public class CalculationPriceBookController {

	@Autowired
	BookRepository bookRepository;
	
	//Calculation Price Books
	@PutMapping("/update_price")
	public HashMap<String, Object> updatePrice(){
	
		List<Book> bookPrice = bookRepository.findAll();
			
		for (Book book : bookPrice) {	
			double basicPrice, baseProduction, ratePrice;
			Date releaseDate = book.getReleaseDate();
			int releaseYear = releaseDate.getYear();
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			
			String bookReleaseCountry = book.getPublisher().getCountryName(), ID = "Indonesia";
			double finalPrice, taxRate;
			
			if (releaseYear == currentYear) {
				ratePrice = 1.5;
			} else {
				ratePrice = 1.3;
			}
			
			baseProduction = book.getPublisher().getGrade().getBookBaseProduction();
			basicPrice = ratePrice * baseProduction;
			
			if (bookReleaseCountry.equalsIgnoreCase(ID)) {
				taxRate = 0.05;
			} else {
				taxRate = 0.1;
			}
			
			finalPrice = basicPrice + (basicPrice * taxRate);
			book.setBookPrice(finalPrice);
			
			bookRepository.save(book);
		}
		
		HashMap<String, Object> messageCalculation = new HashMap<String, Object>();
		messageCalculation.put("Status", 200);
		messageCalculation.put("Message", "Book Price Calculation Is Success");
		
		return messageCalculation;
	}
	
}
