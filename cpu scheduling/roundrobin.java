5import java.util.Scanner;
public class roundrobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, qt, count = 0, i;
        int at[] = new int[10];
        int bt[] = new int[10];
        int rem[] = new int[10];
        int wt[] = new int[10];
        int tat[] = new int[10];
        int ct[] = new int[10];
        int time = 0;
        float avgwt = 0, avgtat = 0;

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();

        System.out.println("Enter Arrival Time and Burst Time:");
        for (i = 0; i < n; i++) {
            System.out.print("P" + (i+1) + " Arrival Time: ");
            at[i] = sc.nextInt();
            System.out.print("P" + (i+1) + " Burst Time: ");
            bt[i] = sc.nextInt();
            rem[i] = bt[i];
        }

        System.out.print("Enter Quantum Time: ");
        qt = sc.nextInt();

        while (true) {
            boolean done = true;
            for (i = 0; i < n; i++) {
                if (rem[i] > 0) {
                    done = false;
                    if (rem[i] > qt) {
                        time += qt;
                        rem[i] -= qt;
                    } else {
                        time += rem[i];
                        rem[i] = 0;
                        ct[i] = time;
                    }
                }
            }
            if (done) break;
        }

        for (i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            avgwt += wt[i];
            avgtat += tat[i];
        }

        System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
        for (i = 0; i < n; i++) {
            System.out.println("P" + (i+1) + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        System.out.println("Average Waiting Time: " + (avgwt / n));
        System.out.println("Average Turnaround Time: " + (avgtat / n));
        sc.close();
    }
}
