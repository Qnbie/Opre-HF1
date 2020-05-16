import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static List<Task> tasks = new ArrayList<Task>();
    static List<String> order = new ArrayList<String>();
    static List<Task> done = new ArrayList<Task>();

    static int runCnt = 0;

    public static void main(String[] args) {

        Scheduler rr = new RR();
        Scheduler srtf = new SRTF();

        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()){
            String tmp = s.nextLine();
            String[] tmpArr = tmp.split(",");
            if(tmpArr.length == 4){
                tasks.add(new Task(tmpArr[0], tmpArr[1], tmpArr[2], tmpArr[3]));
            }
        }
        s.close();
        
        while (tasks.size() != 0 || rr.Ready() || srtf.Ready()) {
            
                while (tasks.size() != 0 && tasks.get(0).start == runCnt) {
                    if (tasks.get(0).prior == 0) {
                        srtf.Put(tasks.get(0));
                        tasks.remove(0);
                    } else if (tasks.get(0).prior == 1) {
                        rr.Put(tasks.get(0));
                        tasks.remove(0);
                    }
                }
            
            

            if (srtf.Ready()) {
                putOrder(srtf.Run());
                rr.Wait();
            } else if (rr.Ready())
                putOrder(rr.Run());

            runCnt++;
        }

        for (String string : order) {
            System.out.print(string);
        }
        System.out.println();

        for (Task task : rr.Result()) {
            done.add(task);
        }
        for (Task task : srtf.Result()) {
            done.add(task);
        }
        Collections.sort(done);

        for (int i = 0; i < done.size(); i++) {
            System.out.print(done.get(i).toString());
            if(i != done.size()-1) System.out.print(',');
        }
        System.out.println();
    }

    public static void putOrder(String taskName) {
        if (order.size() == 0)
            order.add(taskName);
        else if (!order.get(order.size() - 1).equals(taskName))
            order.add(taskName);
    }
}