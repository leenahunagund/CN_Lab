import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;
public class frameSort{
        public static void main(String[]args){
                List<int[]>frame=new ArrayList<>();
                System.out.println("Enter no. of frames:");
                Scanner sc= new Scanner(System.in);
                int n =sc.nextInt();
                int seqNum=1;
                for(int i=0;i<n;i++){
                        Random random=new Random();
                        seqNum=random.nextInt(1000);
                        System.out.printf("Enter data  for %d frame>> ",i+1);
                        int data=sc.nextInt();
                        frame.add(new int []{seqNum,data});
                }
                System.out.println("\n\nBefore sorting>> ");
                for(int[] i:frame){
                        System.out.printf("seqNum-->%d, Data-->%d\n",i[0],i[1]);
                }
                frame=sortFrame(frame);
                System.out.println("\n\nAfter sorting>> ");
                for(int [] i :frame){
                        System.out.printf("seqNum-->%d,Data-->%d\n",i[0],i[1]);
                }
        }
        public static List<int[]>sortFrame(List<int[]>frame){
                Collections.sort(frame,(a , b) -> Integer.compare(a[0] , b[0]));
                return frame;
        }
}
