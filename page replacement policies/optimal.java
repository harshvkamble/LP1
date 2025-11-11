import java.io.*;

class optimal
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n, m, i, j, k, ptr = 0, hit = 0, fault = 0, search;
        boolean full = false;
        
        System.out.print("Enter no. of frames: ");
        n = Integer.parseInt(br.readLine());
        
        System.out.print("Enter length of reference string: ");
        m = Integer.parseInt(br.readLine());
        
        int frame[] = new int[n];
        int ref[] = new int[m];
        int mem[][] = new int[m][n];
        
        for(i = 0; i < n; i++)
            frame[i] = -1;
        
        System.out.println("Enter reference string: ");
        for(i = 0; i < m; i++)
            ref[i] = Integer.parseInt(br.readLine());
        
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
                    int index[] = new int[n];
                    boolean flag[] = new boolean[n];
                    
                    for(j = i + 1; j < m; j++)
                    {
                        for(k = 0; k < n; k++)
                        {
                            if((ref[j] == frame[k]) && (flag[k] == false))
                            {
                                index[k] = j;
                                flag[k] = true;
                                break;
                            }
                        }
                    }
                    
                    int max = index[0];
                    ptr = 0;
                    if(max == 0) max = 200;
                    
                    for(j = 0; j < n; j++)
                    {
                        if(index[j] == 0) index[j] = 200;
                        if(index[j] > max)
                        {
                            max = index[j];
                            ptr = j;
                        }
                    }
                }
                
                frame[ptr] = ref[i];
                fault++;
                
                if(!full)
                {
                    ptr++;
                    if(ptr == n)
                    {
                        ptr = 0;
                        full = true;
                    }
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
        
        System.out.println("Hits = " + hit);
        System.out.println("Faults = " + fault);
        System.out.println("Hit Ratio = " + (float)hit / m);
    }
}
