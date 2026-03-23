let queue = [];
let idCounter = 1;
let time = 0;

// urgency priority mapping
const priority = {
    "LOW": 1,
    "MEDIUM": 2,
    "HIGH": 3,
    "CRITICAL": 4
};

// Add Patient
function addPatient() {
    const name = document.getElementById("name").value;
    const urgency = document.getElementById("urgency").value;

    if (name === "") {
        alert("Enter patient name!");
        return;
    }

    const patient = {
        id: idCounter++,
        name: name,
        urgency: urgency,
        arrivalTime: time++
    };

    queue.push(patient);

    sortQueue();
    displayQueue();

    document.getElementById("name").value = "";
}

// Sort (Greedy Logic)
function sortQueue() {
    queue.sort((a, b) => {
        if (priority[b.urgency] !== priority[a.urgency]) {
            return priority[b.urgency] - priority[a.urgency];
        }
        return a.arrivalTime - b.arrivalTime;
    });
}

// Treat Patient
function treatPatient() {
    if (queue.length === 0) {
        alert("No patients waiting!");
        return;
    }

    const patient = queue.shift();
    alert("Treating: " + patient.name + " (" + patient.urgency + ")");

    displayQueue();
}

// Display Queue
function displayQueue() {
    const list = document.getElementById("queueList");
    list.innerHTML = "";

    queue.forEach((p, index) => {
        const li = document.createElement("li");
        li.innerText = `${index + 1}. ${p.name} (ID:${p.id} | ${p.urgency})`;
        list.appendChild(li);
    });
}