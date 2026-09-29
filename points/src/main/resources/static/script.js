const API_BASE_URL = "http://localhost:8080";


// ==========================================
// LOAD CUSTOMER DATA
// ==========================================

async function loadCustomer() {

    try {

        const response = await fetch(`${API_BASE_URL}/customers`);

        if (!response.ok) {
            throw new Error("Failed to load customer data");
        }

        const customers = await response.json();

        if (customers.length === 0) {
            throw new Error("No customers found");
        }

        // We are using Rahul's customer ID = 9
        const customer = customers.find(
            customer => customer.customerId === 9
        );

        if (!customer) {
            throw new Error("Customer 9 not found");
        }

        document.getElementById("customerName").textContent =
            customer.customerName;

        document.getElementById("customerEmail").textContent =
            customer.email;

        document.getElementById("availablePoints").textContent =
            customer.availablePoints;

        document.getElementById("lifetimePoints").textContent =
            customer.lifetimePoints;

        document.getElementById("customerTier").textContent =
            customer.tier.tierName;

        document.getElementById("discountPercentage").textContent =
            customer.tier.discountPercentage + "%";

        // Update tier progress

const lifetimePoints = customer.lifetimePoints;

const progressText =
    document.getElementById("progressText");

const progressFill =
    document.getElementById("progressFill");

progressText.textContent =
    lifetimePoints.toLocaleString() + " / 5000";

const progress =
    Math.min((lifetimePoints / 5000) * 100, 100);

progressFill.style.width = progress + "%";

    }
    catch (error) {

        console.error("Customer Error:", error);

        document.getElementById("customerName").textContent =
            "Unable to load customer";

    }
}


// ==========================================
// LOAD PURCHASE HISTORY
// ==========================================

async function loadPurchases() {

    try {

        const response = await fetch(`${API_BASE_URL}/purchases`);

        if (!response.ok) {
            throw new Error("Failed to load purchases");
        }

        const purchases = await response.json();

        const table = document.getElementById("purchaseTable");

        table.innerHTML = "";

        purchases.forEach(purchase => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${purchase.purchaseId}</td>
                <td>₹${purchase.purchaseAmount}</td>
                <td>${purchase.pointsEarned}</td>
                <td>${purchase.discountPercentage}%</td>
            `;

            table.appendChild(row);

        });

    }
    catch (error) {

        console.error("Purchase Error:", error);

        document.getElementById("purchaseTable").innerHTML = `
            <tr>
                <td colspan="4">Unable to load purchases</td>
            </tr>
        `;

    }
}


// ==========================================
// LOAD POINTS LEDGER
// ==========================================

async function loadLedger() {

    try {

        const response =
            await fetch(`${API_BASE_URL}/points-ledger`);

        if (!response.ok) {
            throw new Error("Failed to load ledger");
        }

        const ledger = await response.json();

        const table = document.getElementById("ledgerTable");

        table.innerHTML = "";

        ledger.forEach(entry => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${entry.pointsLedgerId}</td>
                <td>${entry.points > 0 ? "+" : ""}${entry.points}</td>
                <td>${entry.transactionType}</td>
            `;

            table.appendChild(row);

        });

    }
    catch (error) {

        console.error("Ledger Error:", error);

        document.getElementById("ledgerTable").innerHTML = `
            <tr>
                <td colspan="3">Unable to load points history</td>
            </tr>
        `;

    }
}


// ==========================================
// REDEEM POINTS
// ==========================================

async function redeemPoints() {

    const pointsInput =
        document.getElementById("redeemPoints");

    const points = Number(pointsInput.value);

    const message =
        document.getElementById("redeemMessage");


    // Check input

    if (!points || points <= 0) {

        message.textContent =
            "Please enter a valid number of points.";

        return;
    }


    try {

        const response = await fetch(
            `${API_BASE_URL}/points-ledger/redeem/9/${points}`,
            {
                method: "POST"
            }
        );


        if (!response.ok) {

            const errorText = await response.text();

            throw new Error(errorText);

        }


        message.textContent =
            `${points} points redeemed successfully!`;

        pointsInput.value = "";


        // Refresh dashboard

        await loadCustomer();

        await loadLedger();

    }
    catch (error) {

        console.error("Redeem Error:", error);

        message.textContent =
            error.message ||
            "Unable to redeem points.";

    }
}


// ==========================================
// LOAD EVERYTHING
// ==========================================

async function loadDashboard() {

    await loadCustomer();

    await loadPurchases();

    await loadLedger();

}


// Run when page opens

loadDashboard();