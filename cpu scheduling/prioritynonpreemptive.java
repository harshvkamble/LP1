import java.util.*;

class process {
    int pid, arrivaltime, bursttime, completiontime, waitingtime, turnaroundtime, priority;
}

public class prioritynonpreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        process[] processes = new process[n];

        for (int i = 0; i < n; i++) {
            processes[i] = new process();
            System.out.println("\nEnter details for Process " + (i + 1));
            System.out.print("Process ID: ");
            processes[i].pid = sc.nextInt();
            System.out.print("Arrival Time: ");
            processes[i].arrivaltime = sc.nextInt();
            System.out.print("Burst Time: ");
            processes[i].bursttime = sc.nextInt();
            System.out.print("Priority (lower number = higher priority): ");
            processes[i].priority = sc.nextInt();
        }

        Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivaltime));

        int currenttime = 0;
        boolean[] iscompleted = new boolean[n];
        int completed = 0;
        double totaltat = 0, totalwt = 0;

        while (completed < n) {
            int idx = -1;
            int highestpriority = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (processes[i].arrivaltime <= currenttime && !iscompleted[i]) {
                    if (processes[i].priority < highestpriority) {
                        highestpriority = processes[i].priority;
                        idx = i;
                    } else if (processes[i].priority == highestpriority) {
                        if (idx == -1 || processes[i].arrivaltime < processes[idx].arrivaltime) {
                            idx = i;
                        }
                    }
                }
            }

            if (idx != -1) {
                process p = processes[idx];
                p.completiontime = currenttime + p.bursttime;
                p.turnaroundtime = p.completiontime - p.arrivaltime;
                p.waitingtime = p.turnaroundtime - p.bursttime;
                totaltat += p.turnaroundtime;
                totalwt += p.waitingtime;
                currenttime = p.completiontime;
                iscompleted[idx] = true;
                completed++;
            } else {
                currenttime++;
            }
        }

        Arrays.sort(processes, Comparator.comparingInt(p -> p.pid));

        System.out.println("\npid\tat\tbt\tpr\tct\ttat\twt");

        for (process p : processes) {
            System.out.println(p.pid + "\t" + p.arrivaltime + "\t" + p.bursttime + "\t" + p.priority + "\t"
                    + p.completiontime + "\t" + p.turnaroundtime + "\t" + p.waitingtime);
        }

        System.out.printf("\nAverage Turnaround Time: %.2f", totaltat / n);
        System.out.printf("\nAverage Waiting Time: %.2f\n", totalwt / n);

        sc.close();
    }
}
