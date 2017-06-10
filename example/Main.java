//  javac -cp ../target/cidekiq-0.1.0-SNAPSHOT-standalone.jar Main.java
//  java -cp "./:../target/cidekiq-0.1.0-SNAPSHOT-standalone.jar:" Main
import space.yeo.cidekiq;
public class Main {

  public static void main(String[] args) {
    String [] a = new String[2];
    a[0] = "hey";
    a[1] = "12";

    //Pitfall,have to use Interget/Float 
    Integer [] v = new Integer[3];
    v[0] = 100;
    v[1] = 200;
    v[1] = 300;

    Double [] f = new Double[1];
    f[0] = 10.4;

    System.out.println("enqueue");
    cidekiq.schedule(System.currentTimeMillis() / 1000 + 300, "low", "SidekiqWorker", v);

    System.out.println(cidekiq.enqueue("default", "SidekiqWorker", a));
  }
}
