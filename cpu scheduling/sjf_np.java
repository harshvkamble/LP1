import java.util.Scanner;

public class sjf_np {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("How many processes you want to schedule?");
        int n = sc.nextInt();

        int[] pid = new int[n];
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] ct = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];
        boolean[] completed = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Process ID:");
            pid[i] = sc.nextInt();
            System.out.println("Enter Arrival Time for process " + pid[i] + ":");
            at[i] = sc.nextInt();
            System.out.println("Enter Burst Time for process " + pid[i] + ":");
            bt[i] = sc.nextInt();
        }

        int currenttime = 0;
        int completedcount = 0;

        while (completedcount < n) {
            int minbt = Integer.MAX_VALUE;
            int selected = -1;

            for (int i = 0; i < n; i++) {
                if (!completed[i] && at[i] <= currenttime && bt[i] < minbt) {
                    minbt = bt[i];
                    selected = i;
                }
            }

            if (selected == -1) {
                currenttime++;
            } else {
                ct[selected] = currenttime + bt[selected];
                currenttime = ct[selected];
                completed[selected] = true;
                completedcount++;
            }
        }

        int totaltat = 0;
        int totalwt = 0;

        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            totaltat += tat[i];
            totalwt += wt[i];
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (pid[j] > pid[j + 1]) {
                    int temp;

                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;

                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    temp = ct[j];
                    ct[j] = ct[j + 1];
                    ct[j + 1] = temp;

                    temp = tat[j];
                    tat[j] = tat[j + 1];
                    tat[j + 1] = temp;

                    temp = wt[j];
                    wt[j] = wt[j + 1];
                    wt[j + 1] = temp;
                }
            }
        }

        System.out.println("\nSJF Non-Preemptive Scheduling Result:");
        System.out.println("pid\tat\tbt\tct\ttat\twt");

        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        double avgtat = (double) totaltat / n;
        double avgwt = (double) totalwt / n;

        System.out.printf("\nAverage Turnaround Time: %.2f", avgtat);
        System.out.printf("\nAverage Waiting Time: %.2f\n", avgwt);

        sc.close();
    }
}
