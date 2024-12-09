console.log("Thymeleaf and JavaScript integration working!");

// Wait for the DOM to load
document.addEventListener('DOMContentLoaded', () => {
    console.log("DOM fully loaded and parsed");

    const viewClaimsLink = document.getElementById('viewClaimsLink');
    const claimsContainer = document.getElementById('claimsContainer');
    console.log("Link element:", viewClaimsLink);
    console.log("Container element:", claimsContainer);

    viewClaimsLink.addEventListener('click', async (event) => {
        event.preventDefault(); // Prevent the default link behavior
        console.log("Link clicked");
        try {
            const response = await fetch('/api/claims/user/2'); // Adjust URL as needed
            console.log("Fetch response:", response);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const jsonData = await response.json();
            console.log("JSON data received:", jsonData);
            displayClaims(jsonData);
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
        }
    });

    function displayClaims(claims) {
        claimsContainer.innerHTML = ''; // Clear previous data
        const table = document.createElement('table');
        table.innerHTML = `
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Created At</th>
                </tr>
            </thead>
        `;
        const tbody = document.createElement('tbody');
        claims.forEach(claim => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${claim.id}</td>
                <td>${claim.description}</td>
                <td>${claim.status || 'N/A'}</td>
                <td>${claim.createdAt ? new Date(...claim.createdAt).toLocaleString() : 'N/A'}</td>
            `;
            tbody.appendChild(row);
        });
        table.appendChild(tbody);
        claimsContainer.appendChild(table);
        console.log("Claims displayed in table");
    }

    const form = document.getElementById('createClaimForm');
    console.log("Form element:", form);

    if (form) {
        console.log("Form found, attaching event listener...");

        form.addEventListener('submit', async function (event) {
            event.preventDefault(); // Prevent default form submission
            console.log("Submit button clicked");

            // Validate and collect form data
            const userIdElement = document.getElementById('userId');
            const userNameElement = document.getElementById('userName');
            const userPhoneElement = document.getElementById('userPhone');
            const userEmailElement = document.getElementById('userEmail');
            const descriptionElement = document.getElementById('description');

            console.log("Form fields:", {
                userIdElement,
                userNameElement,
                userPhoneElement,
                userEmailElement,
                descriptionElement,
            });

            if (
                !userIdElement ||
                !userNameElement ||
                !userPhoneElement ||
                !userEmailElement ||
                !descriptionElement
            ) {
                console.error("One or more form fields are missing!");
                return;
            }

            const formData = {
                userId: userIdElement.value,
                userName: userNameElement.value,
                userPhone: userPhoneElement.value,
                userEmail: userEmailElement.value,
                description: descriptionElement.value,
            };

            console.log("Form data prepared:", formData);

            // Send the POST request
            try {
                const response = await fetch('/api/claims', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(formData),
                });

                if (!response.ok) {
                    const errorText = await response.text();
                    console.error('Server Error:', errorText);
                    throw new Error(errorText);
                }

                const result = await response.json();
                alert('Claim submitted successfully! Claim ID: ' + result.id);
                console.log('Success:', result);
            } catch (error) {
                alert('Error submitting claim: ' + error.message);
                console.error('Error Details:', error);
            }
        });
    } else {
        console.error("Form with id 'createClaimForm' not found in the DOM");
    }

    // JSON data for table
    const jsonData = [
        {
            "id": 2,
            "description": "Updated claim description",
            "status": "Pending",
            "claimDocuments": [],
            "statusHistories": [],
            "createdAt": null
        },
        {
            "id": 19,
            "description": "broken luggage",
            "status": null,
            "claimDocuments": [],
            "statusHistories": [],
            "createdAt": [2024, 12, 8, 22, 9, 18, 17371000]
        },
        {
            "id": 20,
            "description": "medical leave",
            "status": null,
            "claimDocuments": [],
            "statusHistories": [],
            "createdAt": [2024, 12, 8, 22, 56, 953807000]
        },
        {
            "id": 21,
            "description": "none",
            "status": null,
            "claimDocuments": [],
            "statusHistories": [],
            "createdAt": null
        }
    ];

    const tableBody = document.querySelector('#claimsTable tbody');

    jsonData.forEach(claim => {
        const row = document.createElement('tr');

        row.innerHTML = `
        <td>${claim.id}</td>
        <td>${claim.description}</td>
        <td>${claim.status || 'N/A'}</td>
        <td>${claim.createdAt ? new Date(...claim.createdAt).toLocaleString() : 'N/A'}</td>
      `;

        tableBody.appendChild(row);
    });
});document.addEventListener('DOMContentLoaded', () => {
    console.log("Update Claim page loaded");

    const updateForm = document.getElementById('updateClaimForm');

    if (updateForm) {
        console.log("Update form found, attaching event listener...");

        updateForm.addEventListener('submit', async function (event) {
            event.preventDefault(); // Prevent default form submission
            console.log("Update button clicked");

            // Validate and collect form data
            const claimIdElement = document.getElementById('updateClaimId');
            const descriptionElement = document.getElementById('updateDescription');
            const statusElement = document.getElementById('updateStatus');

            console.log("Update form fields:", {
                claimIdElement,
                descriptionElement,
                statusElement,
            });

            if (!claimIdElement || !descriptionElement || !statusElement) {
                console.error("One or more update form fields are missing!");
                return;
            }

            const updateData = {
                description: descriptionElement.value,
                status: statusElement.value,
            };

            console.log("Update form data prepared:", updateData);

            // Send the PUT request
            try {
                const response = await fetch(`/api/claims/${claimIdElement.value}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(updateData),
                });

                if (!response.ok) {
                    const errorText = await response.text();
                    console.error('Server Error:', errorText);
                    throw new Error(errorText);
                }

                const result = await response.json();
                alert('Claim updated successfully!');
                console.log('Success:', result);
            } catch (error) {
                alert('Error updating claim: ' + error.message);
                console.error('Error Details:', error);
            }
        });
    } else {
        console.error("Form with id 'updateClaimForm' not found in the DOM");
    }
});
// Updated JavaScript
// ...
function displayClaims(claims) {
    claimsContainer.innerHTML = ''; // Clear previous data
    const table = document.createElement('table');
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
    const tbody = document.createElement('tbody');
    claims.forEach(claim => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${claim.id}</td>
            <td>${claim.description}</td>
            <td>${claim.status || 'N/A'}</td>
            <td>${claim.createdAt ? new Date(...claim
