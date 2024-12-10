console.log("Thymeleaf and JavaScript integration working!");

// Wait for the DOM to load
document.addEventListener("DOMContentLoaded", async () => {
    console.log("DOM fully loaded and parsed");

    const claimsContainer = document.getElementById("claimsContainer");

    // Fetch and display claims when the page loads
    if (claimsContainer) {
        console.log("Fetching claims on page load...");
        try {
            const response = await fetch("/api/claims/user/2"); // Adjust URL as needed
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            const claims = await response.json();
            console.log("JSON data received:", claims);
            displayClaims(claims);
        } catch (error) {
            console.error("There was a problem with the fetch operation:", error);
        }
    } else {
        console.error("Claims container not found in the DOM");
    }

    // Function to display claims in a table
    function displayClaims(claims) {
        claimsContainer.innerHTML = ""; // Clear previous data
        const table = document.createElement("table");
        table.innerHTML = `
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Created At</th>
                    <th>Actions</th>
                </tr>
            </thead>
        `;
        const tbody = document.createElement("tbody");
        claims.forEach((claim) => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${claim.id}</td>
                <td>${claim.description}</td>
                <td>${claim.status || "N/A"}</td>
                <td>${claim.createdAt ? new Date(...claim.createdAt).toLocaleString() : "N/A"}</td>
                <td>
                    <button onclick="openEditModal(${claim.id})">Update</button>
                </td>
            `;
            tbody.appendChild(row);
        });
        table.appendChild(tbody);
        claimsContainer.appendChild(table);
        console.log("Claims displayed in table");
    }
});

// Function to open the edit modal and fetch claim details
function openEditModal(claimId) {
    const modal = document.getElementById("editModal");
    modal.style.display = "block";

    // Fetch claim details for pre-filling the form
    fetch(/api/claims/${claimId})
.then((response) => response.json())
        .then((claim) => {
            document.getElementById("editDescription").value = claim.description;
            document.getElementById("editStatus").value = claim.status;
            document.getElementById("editClaimForm").onsubmit = function (event) {
                event.preventDefault();
                updateClaim(claimId);
            };
        })
        .catch((error) => console.error("Error fetching claim data:", error));
}

// Function to update the claim
async function updateClaim(claimId) {
    const description = document.getElementById("editDescription").value;
    const status = document.getElementById("editStatus").value;

    try {
        const response = await fetch(/api/claims/${claimId}, {
            method: "PUT",
                headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ description, status }),
        });

        if (response.ok) {
            alert("Claim updated successfully!");
            location.reload(); // Refresh the table
        } else {
            alert("Failed to update claim");
        }
    } catch (error) {
        console.error("Error updating claim:", error);
    }
}

// Function to close the edit modal
function closeEditModal() {
    const modal = document.getElementById("editModal");
    modal.style.display = "none";
}