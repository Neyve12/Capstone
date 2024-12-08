console.log("Thymeleaf and JavaScript integration working!");

// Wait for the DOM to load
document.addEventListener('DOMContentLoaded', () => {
    console.log("DOM fully loaded and parsed");

    const form = document.getElementById('createClaimForm');
    console.log("Form element:", form);

    if (form) {
        console.log("Form found, attaching event listener...");

        form.addEventListener('submit', async function (event) {
            event.preventDefault(); // Prevent default form submission
            console.log("Submit button clicked");

            /// Validate and collect form data
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
});
