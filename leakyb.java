import java.util.ArrayList;
import java.util.Scanner;
public class leakyb{
          public static void main(String[] args){
                  int n;
                  Scanner sc = new Scanner(System.in);
                  System.out.println("enter the number of packets:");
                  n=sc.nextInt();
                  System.out.println("enter the bucket size:");
                  int bsize=sc.nextInt();
                  System.out.println("enter the output rate:");
                  int out_rate = sc.nextInt();
                  int[] packets = new int[n];
                  System.out.println("enter packet sizes in order");
                  int i;
                  for(i=0;i<n;i++)
                          packets[i]=sc.nextInt();
                  ArrayList<Integer> buffer = new ArrayList<Integer>(n);
                  for(i=0;i<n;i++)
                          if(i<=bsize)
                                  buffer.add(packets[i]);
                  System.out.println("sent data:");
                  while(!buffer.isEmpty()){
                          if(buffer.get(0)<=out_rate){
                                  System.out.println("Data sent:"+buffer.get(0));
                                 System.out.println("hi"+buffer.get(0));
                                 buffer.remove(0);
                                 continue;
                          }
                          else if(buffer.get(0)>out_rate){
                                  System.out.println("Data sent:"+(out_rate));
                                  buffer.set(0,buffer.get(0)-out_rate);
                          }
                  }
          }
}
