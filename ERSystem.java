import java.util.*;

// ENUM for Urgency Levels
enum Urgency {
    LOW, MEDIUM, HIGH, CRITICAL
}

// Patient Class
class Patient {
    int id;
    String name;
    Urgency urgency;
    int arrivalTime;

    public Patient(int id, String name, Urgency urgency, int arrivalTime) {
        this.id = id;
        this.name = name;
        this.urgency = urgency;
        this.arrivalTime = arrivalTime;
    }
}

// Comparator (Greedy Logic)
class PatientComparator implements Comparator<Patient> {
    public int compare(Patient p1, Patient p2) {
        if (p1.urgency != p2.urgency) {
            return p2.urgency.ordinal() - p1.urgency.ordinal();
        }
        return p1.arrivalTime - p2.arrivalTime;
    }
}

// Emergency Room System
class EmergencyRoom {
    PriorityQueue<Patient> queue = new PriorityQueue<>(new PatientComparator());
    int time = 0;
    int idCounter = 1;

    // Add Patient
    public void addPatient(String name, Urgency urgency) {
        Patient p = new Patient(idCounter++, name, urgency, time++);
        queue.add(p);

        System.out.println("\n✅ Patient Registered Successfully!");
        System.out.println("🆔 ID: " + p.id);
        System.out.println("👤 Name: " + p.name);
        System.out.println("⚠️ Condition: " + p.urgency);

        showStatus();
    }

    // Treat Next Patient
    public void treatPatient() {
        if (queue.isEmpty()) {
            System.out.println("\n😴 No patients waiting.");
            return;
        }

        Patient p = queue.poll();
        System.out.println("\n👨‍⚕️ Now Treating:");
        System.out.println("🆔 ID: " + p.id + " | 👤 " + p.name + " | ⚠️ " + p.urgency);
    }

    // Show Status (IMPORTANT)
    public void showStatus() {
        System.out.println("\n📊 CURRENT STATUS");
        System.out.println("-------------------------");

        if (queue.isEmpty()) {
            System.out.println("👨‍⚕️ Doctor: Available");
            System.out.println("📭 Waiting List: Empty");
            return;
        }

        System.out.println("👨‍⚕️ Doctor: Available");
        System.out.println("📋 Waiting List (Priority Order):");

        // Sort queue for correct display
        List<Patient> temp = new ArrayList<>(queue);
        temp.sort(new PatientComparator());

        int i = 1;
        for (Patient p : temp) {
            System.out.println(i++ + ". " + p.name +
                    " (ID:" + p.id + " | " + p.urgency + ")");
        }
    }
}

// Main Class
public class ERSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmergencyRoom er = new EmergencyRoom();

        while (true) {

            System.out.println("\n====================================");
            System.out.println("🏥 EMERGENCY ROOM MANAGEMENT SYSTEM");
            System.out.println("====================================");

            System.out.println("\nChoose an option:");
            System.out.println("1️⃣  Add Patient");
            System.out.println("2️⃣  Start Treatment");
            System.out.println("3️⃣  Exit");

            System.out.print("\n👉 Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("\n--- ADD NEW PATIENT ---");

                    System.out.print("Enter Patient Name: ");
                    String name = sc.nextLine();

                    System.out.println("\nSelect Condition (Urgency Level):");
                    System.out.println("1. 🟢 LOW       (Minor)");
                    System.out.println("2. 🟡 MEDIUM    (Normal)");
                    System.out.println("3. 🟠 HIGH      (Serious)");
                    System.out.println("4. 🔴 CRITICAL  (Emergency)");

                    System.out.print("\n👉 Enter choice: ");
                    int u = sc.nextInt();

                    Urgency urgency;
                    switch (u) {
                        case 1: urgency = Urgency.LOW; break;
                        case 2: urgency = Urgency.MEDIUM; break;
                        case 3: urgency = Urgency.HIGH; break;
                        case 4: urgency = Urgency.CRITICAL; break;
                        default: urgency = Urgency.LOW;
                    }

                    er.addPatient(name, urgency);
                    break;

                case 2:
                    er.treatPatient();
                    break;

                case 3:
                    System.out.println("\n👋 Exiting system...");
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}