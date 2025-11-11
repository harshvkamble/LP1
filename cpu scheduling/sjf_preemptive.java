import java.util.Scanner;

public class sjf_preemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of processes:");
        int n = sc.nextInt();

        int[] pid = new int[n];
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] bt_remaining = new int[n];
        int[] ct = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];
        boolean[] iscompleted = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Process ID:");
            pid[i] = sc.nextInt();
            System.out.println("Enter Arrival Time for process " + pid[i] + ":");
            at[i] = sc.nextInt();
            System.out.println("Enter Burst Time for process " + pid[i] + ":");
            bt[i] = sc.nextInt();
            bt_remaining[i] = bt[i];
        }

        int completed = 0;
        int currenttime = 0;
        int minindex;
        int minremaining;

        while (completed < n) {
            minindex = -1;
            minremaining = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (at[i] <= currenttime && !iscompleted[i] && bt_remaining[i] < minremaining && bt_remaining[i] > 0) {
                    minremaining = bt_remaining[i];
                    minindex = i;
                }
            }

            if (minindex == -1) {
                currenttime++;
            } else {
                bt_remaining[minindex]--;
                currenttime++;

                if (bt_remaining[minindex] == 0) {
                    ct[minindex] = currenttime;
                    iscompleted[minindex] = true;
                    completed++;
                }
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

        System.out.println("\nSJF Preemptive Scheduling Result:");
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
