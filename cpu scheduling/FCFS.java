4
import java.util.*;

public class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("How many processes you want to schedule?");
        int n = sc.nextInt();

        int temp;
        int[] pid = new int[n];
        int[] AT = new int[n];
        int[] BT = new int[n];
        int[] CT = new int[n];
        int[] TAT = new int[n];
        int[] WT = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter process id:");
            pid[i] = sc.nextInt();
            System.out.println("Enter Arrival Time for process " + pid[i] + ":");
            AT[i] = sc.nextInt();
            System.out.println("Enter Burst Time for process " + pid[i] + ":");
            BT[i] = sc.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (AT[j] > AT[j + 1]) {
                    temp = AT[j];
                    AT[j] = AT[j + 1];
                    AT[j + 1] = temp;

                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;

                    temp = BT[j];
                    BT[j] = BT[j + 1];
                    BT[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                CT[i] = AT[i] + BT[i];
            } else {
                if (CT[i - 1] < AT[i]) {
                    CT[i] = AT[i] + BT[i];
                } else {
                    CT[i] = CT[i - 1] + BT[i];
                }
            }
        }

        int totalTAT = 0;
        int totalWT = 0;

        for (int i = 0; i < n; i++) {
            TAT[i] = CT[i] - AT[i];
            WT[i] = TAT[i] - BT[i];
            totalTAT += TAT[i];
            totalWT += WT[i];
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (pid[j] > pid[j + 1]) {
                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;

                    temp = AT[j];
                    AT[j] = AT[j + 1];
                    AT[j + 1] = temp;

                    temp = BT[j];
                    BT[j] = BT[j + 1];
                    BT[j + 1] = temp;

                    temp = CT[j];
                    CT[j] = CT[j + 1];
                    CT[j + 1] = temp;

                    temp = TAT[j];
                    TAT[j] = TAT[j + 1];
                    TAT[j + 1] = temp;

                    temp = WT[j];
                    WT[j] = WT[j + 1];
                    WT[j + 1] = temp;
                }
            }
        }

        System.out.println("\nFinal Output (Sorted by Process ID):");
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + AT[i] + "\t" + BT[i] + "\t" + CT[i] + "\t" + TAT[i] + "\t" + WT[i]);
        }

        double avgTAT = (double) totalTAT / n;
        double avgWT = (double) totalWT / n;

        System.out.printf("\nAverage Turnaround Time: %.2f", avgTAT);
        System.out.printf("\nAverage Waiting Time: %.2f\n", avgWT);

        sc.close();
    }
}
