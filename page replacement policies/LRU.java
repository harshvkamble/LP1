import java.io.*;

class LRU
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int frame[], ref[], mem[][];
        int i, j, k, m, n, pointer = 0, fault = 0, hit = 0, search;
        boolean full = false;
        
        System.out.print("Enter no. of frames: ");
        n = Integer.parseInt(br.readLine());
        
        System.out.print("Enter length of reference string: ");
        m = Integer.parseInt(br.readLine());
        
        frame = new int[n];
        ref = new int[m];
        mem = new int[m][n];
        
        for(i = 0; i < n; i++)
            frame[i] = -1;
        
        System.out.println("Enter reference string: ");
        for(i = 0; i < m; i++)
            ref[i] = Integer.parseInt(br.readLine());
        
        int recent[] = new int[n]; 
        
        for(i = 0; i < m; i++)
        {
            search = -1;
            for(j = 0; j < n; j++)
            {
                if(frame[j] == ref[i])
                {
                    hit++;
                    search = j;
                    break;
                }
            }
            
            if(search == -1)
            {
                if(full)
                {
                    int min = i;
                    int replace = 0;
                    for(j = 0; j < n; j++)
                    {
                        int lastUse = -1;
                        for(k = i - 1; k >= 0; k--)
                        {
                            if(frame[j] == ref[k])
                            {
                                lastUse = k;
                                break;
                            }
                        }
                        if(lastUse < min)
                        {
                            min = lastUse;
                            replace = j;
                        }
                    }
                    pointer = replace;
                }
                frame[pointer] = ref[i];
                fault++;
                pointer++;
                if(pointer == n)
                {
                    pointer = 0;
                    full = true;
                }
            }
            
            for(j = 0; j < n; j++)
                mem[i][j] = frame[j];
        }
        
        System.out.println("\nMemory Layout:");
        for(j = 0; j < n; j++)
        {
            for(i = 0; i < m; i++)
                System.out.print(mem[i][j] + "\t");
            System.out.println();
        }
        
        System.out.println("\nHits = " + hit);
        System.out.println("Faults = " + fault);
        System.out.println("Hit Ratio = " + (float)hit / m);
    }
}
