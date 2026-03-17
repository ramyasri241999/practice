package com.epam.practice.controller;

import java.util.Map;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * RMM model -- Richardson maturity model is a model for RESTful web services. 
 * It defines four levels of maturity for RESTful web services, based on the level of adherence to REST principles. 
 * The levels are:
 * 1. Level 0: The service is not RESTful, it does not use HTTP methods and does not have a resource-oriented architecture.
 * 2. Level 1: The service uses HTTP methods, but does not have a resource-oriented architecture.
 * 3. Level 2: The service uses HTTP methods and has a resource-oriented architecture, but does not use hypermedia (HATEOAS).
 * 4. Level 3: The service uses HTTP methods, has a resource-oriented architecture, and uses hypermedia (HATEOAS).
 * Below are the examples of each level of maturity for RESTful web services.
 */

@RestController
@RequestMapping("/api0")  
public class RMMModelExample { 
	@PostMapping
		 public Object handleRequest(@RequestBody Map<String, Object> request) {// Level 0: Not RESTful - The swamp of POJOs (Plain Old Java Objects) and HTTP endpoints without any structure or adherence to REST principles.

		        String action = (String) request.get("action");

		        if ("getBook".equals(action)) {
		            Long id = Long.valueOf(request.get("id").toString());
		            return "Returning book with id " + id;
		        }

		        if ("createBook".equals(action)) {
		            String title = (String) request.get("title");
		            return "Book created: " + title;
		        }

		        if ("deleteBook".equals(action)) {
		            Long id = Long.valueOf(request.get("id").toString());
		            return "Deleted book " + id;
		        }

		        return "Invalid action";
		    }
	
	
	
	/*level 1: The service uses HTTP methods, but does not have a resource-oriented architecture. It is still not RESTful, but it is a step towards RESTful web services. 
	It is still a swamp of POJOs and HTTP endpoints, but it uses HTTP methods to perform actions on the resources.
	you can observe that we are using HTTP methods to perform actions on the resources, but we are not using a resource-oriented architecture. 
	We are still using a single endpoint to handle all the actions, and we are not using any structure to organize our endpoints.
	eg: post for all methods and inside post method we are checking which action to perform based on the request body. 
	This is not a good design, but it is a step towards RESTful web services.
	*/
	 @PostMapping("/get")
	    public String getBook(@RequestBody Map<String, Long> request) {
	        return "Returning book " + request.get("id");
	    }

	    @PostMapping("/create")
	    public String createBook(@RequestBody Map<String, String> request) {
	        return "Created book " + request.get("title");
	    }

	    @PostMapping("/delete")
	    public String deleteBook(@RequestBody Map<String, Long> request) {
	        return "Deleted book " + request.get("id");
	    }
	    
	    
	    /*level 2: The service uses HTTP methods and has a resource-oriented architecture, but does not use hypermedia (HATEOAS).
	     * In this level, we have a resource-oriented architecture, but we are not using hypermedia to navigate between resources.
	     * Here we use get, post , put and delete methods to perform actions on the resources, and we have a resource-oriented architecture, 
	     * but we are not using hypermedia to navigate between resources.
	     * majority of the RESTful web services are at this level of maturity.
	     */
	    @GetMapping("/{id}")
	    public String getBook(@PathVariable Long id) {
	        return "Returning book " + id;
	    }

	    @PostMapping
	    public String createBookl2(@RequestBody Map<String, String> request) {
	        return "Created book " + request.get("title");
	    }

	    @PutMapping("/{id}")
	    public String updateBook(@PathVariable Long id,
	                             @RequestBody Map<String, String> request) {
	        return "Updated book " + id;
	    }

	    @DeleteMapping("/{id}")
	    public String deleteBook(@PathVariable Long id) {
	        return "Deleted book " + id;
	    }
	    
	    /*
	     * Level 3: The service uses HTTP methods, has a resource-oriented architecture, and uses hypermedia (HATEOAS).
	     * In this level, we have a resource-oriented architecture and we are using hypermedia to navigate between resources.
	     */
	    @GetMapping("/{id}")
	    public EntityModel<Book> getBookl3(@PathVariable Long id) {

	        Book book = new Book(id, "Clean Code");

	        return EntityModel.of(book,
	                linkTo(methodOn(RMMModelExample.class).getBookl3(id)).withSelfRel(),
	                linkTo(methodOn(RMMModelExample.class).deleteBookl3(id)).withRel("delete"),
	                linkTo(methodOn(RMMModelExample.class).getAllBooks()).withRel("all-books")
	        );
	    }

	    @GetMapping
	    public String getAllBooks() {
	        return "Returning all books";
	    }

	    @DeleteMapping("/{id}")
	    public String deleteBookl3(@PathVariable Long id) {
	        return "Deleted book " + id;
	    }
	
	}


class Book{
	private Long id;
	private String title;
	
	public Book(Long id, String title) {
		this.id = id;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

