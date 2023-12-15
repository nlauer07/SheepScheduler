/*
 * Written by Nick Lauer
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SheepShearingScheduler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            String filePath;
            DynamicArray<Sheep> sheepList = new DynamicArray<>();
            MinHeap<Sheep> sheepHeap = new MinHeap<>();
            
            System.out.println("----------------------Sheep Shearing Scheduler----------------------");
            System.out.println("Enter the file name:");
            filePath = scanner.nextLine();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\t");
                    Sheep sheep = new Sheep(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    sheepList.add(sheep);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            sortSheepList(sheepList);

            int currentTime = 0;
            int index = 0;

            while (index < sheepList.size() || !sheepHeap.isEmpty()) {
                while (index < sheepList.size() && sheepList.get(index).getArrivalTime() <= currentTime) {
                    sheepHeap.add(sheepList.get(index));
                    index++;
                }

                if (!sheepHeap.isEmpty()) {
                    Sheep nextSheep = sheepHeap.remove();
                    System.out.println(nextSheep); // Shearing the sheep
                    currentTime = Math.max(currentTime, nextSheep.getArrivalTime()) + nextSheep.getShearingTime();
                } else if (index < sheepList.size()) {
                    currentTime = sheepList.get(index).getArrivalTime();
                }
            }

            System.out.println("Do you want to run it again? (yes/no)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                main(args);
            }

        } finally {
            scanner.close();
        }
    }

    private static void sortSheepList(DynamicArray<Sheep> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getArrivalTime() > list.get(j).getArrivalTime()) {
                    Sheep temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
}