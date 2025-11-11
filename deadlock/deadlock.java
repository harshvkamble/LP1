import java.util.Scanner;

public class deadlock {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of processes: ");
        int p = sc.nextInt();

        System.out.println("Enter number of resources: ");
        int r = sc.nextInt();

        int[] processid = new int[p];
        int[][] max = new int[p][r];
        int[][] alloc = new int[p][r];  
        int[][] need = new int[p][r];
        int[] avail = new int[r];
        boolean[] finish = new boolean[p];
        int[] safe = new int[p];  

        System.out.println("Enter process IDs:");
        for (int i = 0; i < p; i++) {
            processid[i] = sc.nextInt();
        }

  
        System.out.println("Enter the maximum resources for each process:");
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < r; j++) {
                max[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter the allocated resources for each process:");
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < r; j++) {
                alloc[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter the available resources:");
        for (int j = 0; j < r; j++) {
            avail[j] = sc.nextInt();
        }


        for (int i = 0; i < p; i++) {
            for (int j = 0; j < r; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }

        int count = 0;
        boolean deadlock = false;

        while (count < p) {
            boolean found = false;

            for (int i = 0; i < p; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < r; j++) {
                        if (need[i][j] > avail[j]) {
                            break;
                        }
                    }

                    if (j == r) {
                        
                        for (int k = 0; k < r; k++) {
                            avail[k] += alloc[i][k];
                        }
                        finish[i] = true;
                        safe[count++] = processid[i];
                        found = true;
                    }
                }
            }

            if (!found) {
                deadlock = true;
                break;
            }
        }

        if (deadlock) {
            System.out.println("Deadlock has occurred. No safe sequence.");
        } else {
            System.out.print("System is in a SAFE STATE.\nSafe sequence is: ");
            for (int i = 0; i < p; i++) {
                System.out.print("P" + safe[i]);
                if (i != p - 1) System.out.print(" -> ");
            }
            System.out.println();
        }
    }
}
