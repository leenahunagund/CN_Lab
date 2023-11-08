import java.util.Scanner;
import java.util.ArrayList;
class rsa{
        public static void main(String[]args){
                int p=17,q=23,n=p*q,phi,e,d;
                phi=(p-1)*(q-1);
                e=getcoprime(phi);
                d=getprivatekey(e,phi);
                Scanner sc=new Scanner(System.in);
                System.out.println("enter plain text");
                String plaintext=sc.nextLine();
                int ciphertext[]=encrypt(plaintext,e,n);
                System.out.print("ciphertext: ");
                for(int cipher:ciphertext)
                        System.out.print(cipher+" ");
                String decryptedtext=decrypt(ciphertext,d,n);
                System.out.println("\ndecrypted plain text: "+decryptedtext);
        }
        static int getcoprime(int phi){
                int e=2;
                while(e<phi &&gcd(e,phi)!=1)
                        e++;
                if(gcd(e,phi)==1)
                        return e;
                return 0;
        }
        static int getprivatekey(int e,int phi){
                int d=0,k=-1;
                while((d*e)%phi!=1)
                        d=(1+k++*phi)/e;
                return d;
        }
        static int[] encrypt(String plaintext,int e,int n){
                char chars[]=plaintext.toCharArray();
                int ciphertext[]=new int[chars.length];
                for (int i=0;i<chars.length;i++)
                        ciphertext[i]=modPow((int) chars[i],e,n);
                        return ciphertext;
        }
        static String decrypt(int ciphertext[],int d,int n){
                StringBuilder decryptedtext=new StringBuilder();
                for (int cipher:ciphertext){
                        int charvalue=modPow(cipher,d,n);
                        decryptedtext.append((char)charvalue);
                }
                return decryptedtext.toString();
        }
        static int gcd(int a,int b){
                if(b==0)
                        return a;
                return gcd(b,a%b);
        }
        static int modPow(int base,int exponent,int modulus){
                int result=1;
                base=base%modulus;
                while(exponent>0){
                        if(exponent%2==1){
                                result=(result*base)%modulus;
                        }
                        exponent=exponent>>1;
                        //equivalent to exponent 1=2
                        base=(base*base)%modulus;
                }
                return result;
        }
}
