package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.model.User;
import com.finance.finance_dashboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public User updateStatus(@PathVariable Long id,
                             @RequestParam boolean active) {
        return userService.updateStatus(id, active);
    }

}