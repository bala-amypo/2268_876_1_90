package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final UserService userService;
    private final TicketService ticketService;
    private final TicketCategoryService categoryService;
    private final DuplicateRuleService ruleService;
    private final DuplicateDetectionService detectionService;

    public ApiController(UserService userService,
                         TicketService ticketService,
                         TicketCategoryService categoryService,
                         DuplicateRuleService ruleService,
                         DuplicateDetectionService detectionService) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.categoryService = categoryService;
        this.ruleService = ruleService;
        this.detectionService = detectionService;
    }

    // Users
    @PostMapping("/users")
    public ResponseEntity<User> register(@RequestBody User user){
        return ResponseEntity.ok(userService.registerUser(user));
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Categories
    @PostMapping("/categories")
    public ResponseEntity<TicketCategory> createCategory(@RequestBody TicketCategory c){
        return ResponseEntity.ok(categoryService.createCategory(c));
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<TicketCategory> getCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    // Tickets
    @PostMapping("/tickets")
    public ResponseEntity<Ticket> createTicket(@RequestParam Long userId,
                                               @RequestParam Long categoryId,
                                               @RequestBody Ticket t){
        return ResponseEntity.ok(ticketService.createTicket(userId, categoryId, t));
    }
    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.getTicket(id));
    }
    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> allTickets(){
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
    @GetMapping("/tickets/by-user/{userId}")
    public ResponseEntity<List<Ticket>> ticketsByUser(@PathVariable Long userId){
        return ResponseEntity.ok(ticketService.getTicketsByUser(userId));
    }

    // Rules
    @PostMapping("/rules")
    public ResponseEntity<DuplicateRule> createRule(@RequestBody DuplicateRule r){
        return ResponseEntity.ok(ruleService.createRule(r));
    }
    @GetMapping("/rules/{id}")
    public ResponseEntity<DuplicateRule> getRule(@PathVariable Long id){
        return ResponseEntity.ok(ruleService.getRule(id));
    }

    // Duplicate detection logs
    @GetMapping("/tickets/{ticketId}/duplicates")
    public ResponseEntity<List<DuplicateDetectionLog>> detect(@PathVariable Long ticketId){
        return ResponseEntity.ok(detectionService.detectDuplicates(ticketId));
    }
    @GetMapping("/tickets/{ticketId}/logs")
    public ResponseEntity<List<DuplicateDetectionLog>> logs(@PathVariable Long ticketId){
        return ResponseEntity.ok(detectionService.getLogsForTicket(ticketId));
    }
}
