package org.neidysvelasquez.claims_management_system.controller.views;

import org.neidysvelasquez.claims_management_system.model.Claims;
import org.neidysvelasquez.claims_management_system.model.User;
import org.neidysvelasquez.claims_management_system.service.ClaimsService;
import org.neidysvelasquez.claims_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * Controller for handling Thymeleaf views in the Claims Management System.
 */
@Controller
public class ViewController {

    private final ClaimsService claimsService;
    private final UserService userService;

    @Autowired
    public ViewController(ClaimsService claimsService, UserService userService) {
        this.claimsService = claimsService;
        this.userService = userService;
    }

    /**
     * Home Page
     *
     * @return the name of the home view template.
     */
    @GetMapping("/")
    public String home() {
        return "home"; // Maps to home.html
    }

    /**
     * Create Claim Page
     *
     * @return the name of the create claim view template.
     */
    @GetMapping("/create-claim")
    public String createClaimForm(Model model) {
        model.addAttribute("claim", new Claims());
        return "create_claim"; // Maps to create_claim.html
    }


    @PostMapping("create-claim")
    public String createClaim(@ModelAttribute("claim") Claims claims, RedirectAttributes redirectAttributes) {
        claimsService.createClaim(claims);
        redirectAttributes.addFlashAttribute("message", "Claim created successfully!");
        return "redirect:/home";
    }

    /**
     * View All Claims
     *
     * @param model the model to hold claim data for the view.
     * @return the name of the view claims template.
     */
    @GetMapping("/view-claims")
    public String viewClaims(Model model) {
        model.addAttribute("claims", claimsService.getAllClaims());
        return "view_claims"; // Maps to view_claims.html
    }

    /**
     * Claim Details Page
     *
     * @param id    the ID of the claim to view.
     * @param model the model to hold claim data for the view.
     * @return the name of the claim details view template.
     */
    @GetMapping("/claim/{id}")
    public String claimDetails(@PathVariable Long id, Model model) {
        Optional<Claims> claim =  Optional.ofNullable(claimsService.getClaimById(id));
        if (claim.isPresent()) {
            model.addAttribute("claim", claim.get());
        } else {
            model.addAttribute("error", "Claim not found");
        }
        return "claim_details"; // Maps to claim_details.html
    }

    /**
     * Upload Document Page
     *
     * @return the name of the upload document view template.
     */
    @GetMapping("/upload-document")
    public String uploadDocumentForm() {
        return "upload_document"; // Maps to upload_document.html
    }

    @GetMapping("/user-profile")
    public String userProfile(Model model) {
        User user = userService.getUserById(1L);  // Directly get User, or null if not found

        if (user != null) {
            model.addAttribute("name", user.getName());
            model.addAttribute("email", user.getEmail());
        } else {
            model.addAttribute("name", "Unknown User");
            model.addAttribute("email", "No Email Found");
        }

        return "user_profile"; // Maps to user_profile.html
    }

        @GetMapping("/update/{id}")
        public String showUpdateForm(@PathVariable Long id, Model model) {
        Claims claim = claimsService.getClaimById(id);
        model.addAttribute("claim", claim);
        return "update_claim"; // Points to the Thymeleaf template
    }
    }
