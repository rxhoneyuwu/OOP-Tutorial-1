import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ===== ACTIVITY 1 =====
        ArrayList<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== ADD TASKS ======");

        // Allow user to enter 3 tasks
        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter Task " + i + ": ");
            String task = scanner.nextLine();
            tasks.add(task);
        }

        // Display tasks
        System.out.println("\n===== TASK LIST ======");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }

        // ===== ACTIVITY 2 =====
        // Saving the task to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
            System.out.println("\nTasks saved successfully.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Read from file
        System.out.println("\n====== TASKS LOADED FROM FILE =======");
        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}