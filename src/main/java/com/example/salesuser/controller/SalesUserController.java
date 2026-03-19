package com.example.salesuser.controller;

import com.example.salesuser.entity.SalesUser;
import com.example.salesuser.service.SalesUserService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class SalesUserController {

    @EJB
    private SalesUserService salesUserService;

    private SalesUser user = new SalesUser();
    private Long selectedUserId;
    private List<SalesUser> users;

    // Action methods
    public String createUser() {
        try {
            salesUserService.createUser(user);
            // Reset form
            user = new SalesUser();
            // Refresh list
            users = null;
            // Stay on same page
            return null;
        } catch (Exception e) {
            // Handle error (in real app, add faces message)
            e.printStackTrace();
            return null;
        }
    }

    public String updateUser() {
        try {
            salesUserService.updateUser(user);
            // Reset form
            user = new SalesUser();
            selectedUserId = null;
            // Refresh list
            users = null;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteUser(Long userId) {
        try {
            salesUserService.deleteUser(userId);
            // If deleting the currently edited user, reset form
            if (selectedUserId != null && selectedUserId.equals(userId)) {
                user = new SalesUser();
                selectedUserId = null;
            }
            // Refresh list
            users = null;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loadUserForEdit() {
        if (selectedUserId != null) {
            user = salesUserService.getUser(selectedUserId);
            if (user == null) {
                user = new SalesUser();
            } else {
                // Clear password field for security and to avoid displaying hash
                user.setPassword("");
            }
        } else {
            user = new SalesUser();
        }
    }

    // Getters and Setters
    public SalesUser getUser() {
        return user;
    }

    public void setUser(SalesUser user) {
        this.user = user;
    }

    public Long getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(Long selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public List<SalesUser> getUsers() {
        if (users == null) {
            users = salesUserService.getAllUsers();
        }
        return users;
    }

    // Helper properties for UI
    public boolean isEditMode() {
        return selectedUserId != null;
    }

    public String getFormTitle() {
        return isEditMode() ? "Edit Sales User" : "Create New Sales User";
    }

    public String getSubmitButtonLabel() {
        return isEditMode() ? "Update" : "Create";
    }

    public String getFormAction() {
        return isEditMode() ? "updateUser" : "createUser";
    }
}