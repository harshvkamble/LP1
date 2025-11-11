import java.util.*;

class process {
    int pid;
    int arrivaltime;
    int bursttime;
    int remainingtime;
    int priority;
    int completiontime;
    int waitingtime;
    int turnaroundtime;
    boolean iscompleted = false;

    public process(int pid, int arrivaltime, int bursttime, int priority) {
        this.pid = pid;
        this.arrivaltime = arrivaltime;
        this.bursttime = bursttime;
        this.remainingtime = bursttime;
        this.priority = priority;
    }
}

public class prioritypreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        List<process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time, Burst Time, and Priority for Process " + (i + 1) + ":");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            int pr = sc.nextInt();
            processes.add(new process(i + 1, at, bt, pr));
        }

        int currenttime = 0, completed = 0;
        double totaltat = 0, totalwt = 0;

        while (completed != n) {
            process current = null;
            int highestpriority = Integer.MAX_VALUE;

            for (process p : processes) {
                if (p.arrivaltime <= currenttime && p.remainingtime > 0) {
                    if (p.priority < highestpriority) {
                        highestpriority = p.priority;
                        current = p;
                    } else if (p.priority == highestpriority) {
                        if (current == null || p.arrivaltime < current.arrivaltime) {
                            current = p;
                        }
                    }
                }
            }

            if (current != null) {
                current.remainingtime--;
                currenttime++;
                if (current.remainingtime == 0) {
                    current.completiontime = currenttime;
                    current.turnaroundtime = current.completiontime - current.arrivaltime;
                    current.waitingtime = current.turnaroundtime - current.bursttime;
                    current.iscompleted = true;
                    totaltat += current.turnaroundtime;
                    totalwt += current.waitingtime;
                    completed++;
                }
            } else {
                currenttime++;
            }
        }

        System.out.println("\npid\tat\tbt\tpr\tct\ttat\twt");

        for (process p : processes) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\t%d\n",
                p.pid, p.arrivaltime, p.bursttime, p.priority,
                p.completiontime, p.turnaroundtime, p.waitingtime);
        }

        System.out.printf("\nAverage Turnaround Time: %.2f\n", totaltat / n);
        System.out.printf("Average Waiting Time: %.2f\n", totalwt / n);

        sc.close();
    }
}
