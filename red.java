import java.util.Random;
public class red{
        private static final int queue_size=10;
        private static final int max_packets=20;
        private static final double max_probability=0.7;
        private static final double min_probability=0.3;
        //generates random number between min&max
        private static double randDouble(double min,double max){
                Random rand= new Random();
                return min+(max-min)*rand.nextDouble();
        }
        public static void main(String[]args){
                int queuesize=0;
                double dropprobability=min_probability;
                for(int i=0;i<max_packets;i++){
                        double x=randDouble(0,1);
                        System.out.println(String.format("%.2f",x));
                        if(queuesize==queue_size){
                                //queue is full , drop packets
                                System.out.println("packet dropped(queue full)");
                                dropprobability=min_probability;
                        }
                        else if(x<dropprobability){
                                //randomly drops the packet based on the current drop probability

                                System.out.println("packet dropped(random)");
                                dropprobability+=(max_probability-min_probability)/(max_packets-2);
                        }
                        else{
                                //accept the packet
                                System.out.println("packet accepted");
                                queuesize++;
                                dropprobability=min_probability;
                        }
                }
        }
}
