package org.neidysvelasquez.claims_management_system;

import org.junit.jupiter.api.Test;
import org.neidysvelasquez.claims_management_system.controller.views.ViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClaimsManagementSystemApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    /**
     * Context Loading Test
     * Ensures the application context loads successfully.
     */
    @Test
    void contextLoads() {
        // This test will pass if the Spring context starts without errors.
    }

    /**
     * Integration Test: Test Home Page
     */
    @Test
    void testHomePage() {
        webTestClient.get().uri("/").exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    String body = response.getResponseBody();
                    assert body != null && body.contains("Welcome to the Claims Management System");
                });
    }

    /**
     * Integration Test: Test View Claims Page
     */
    @Test
    void testViewClaimsPage() {
        webTestClient.get().uri("/view-claims").exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    String body = response.getResponseBody();
                    assert body != null && body.contains("View Claims");
                });
    }
}

@WebMvcTest(ViewController.class)
class ViewControllerTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test Home Page Navigation
     */
    @Test
    void testHomePageNavigation() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    /**
     * Test View Claims Page Navigation
     */
    @Test
    void testViewClaimsPageNavigation() throws Exception {
        mockMvc.perform(get("/view-claims"))
                .andExpect(status().isOk())
                .andExpect(view().name("view_claims"));
    }

    /**
     * Test Create Claim Page Navigation
     */
    @Test
    void testCreateClaimPageNavigation() throws Exception {
        mockMvc.perform(get("/create-claim"))
                .andExpect(status().isOk())
                .andExpect(view().name("create_claim"));
    }

    /**
     * Test Claim Details Navigation
     */
    @Test
    void testClaimDetailsPageNavigation() throws Exception {
        mockMvc.perform(get("/claim/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("claim_details"));
    }

    /**
     * Test User Profile Page Navigation
     */
    @Test
    void testUserProfilePageNavigation() throws Exception {
        mockMvc.perform(get("/user-profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("user_profile"));
    }
}
