import java.io.*;
import java.net.*;
public class tcpc{
        public static void main(String[]args) throws Exception{
                Socket sock=new Socket("127.0.01",3000);
                System.out.println("enter filename ");
                BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
                String fname=keyRead.readLine();
                OutputStream ostream=sock.getOutputStream();
                PrintWriter pwrite=new PrintWriter(ostream,true);
                pwrite.println(fname);
                InputStream istream=sock.getInputStream();
                BufferedReader sockRead=new BufferedReader(new InputStreamReader(istream));
                String str;
                while((str=sockRead.readLine())!=null)
                        System.out.println(str);
                pwrite.close();
                sockRead.close();
                keyRead.close();
        }
}
