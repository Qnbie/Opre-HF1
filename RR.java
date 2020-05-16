import java.util.ArrayList;
import java.util.List;


public class RR implements Scheduler {

    List<Task> tasks = new ArrayList<Task>();
    List<Task> done = new ArrayList<Task>();

    int runcount = 0;

    @Override
    public String Run() {
        for (int i = 1; i < tasks.size(); i++) {
            tasks.get(i).waitTime++;
        }

        tasks.get(0).cpu--;
        String name = tasks.get(0).name;
        runcount++;

        if (tasks.get(0).cpu == 0) {
            Task tmp = tasks.get(0);
            tasks.remove(0);
            done.add(tmp);
            runcount = 0;
        } else if (runcount == 2) {
            Task tmp = tasks.get(0);
            tasks.remove(0);
            tasks.add(tmp);
            runcount = 0;
        }
        return name;
    }

    @Override
    public void Put(Task task) {
        tasks.add(task);
    }

    @Override
    public boolean Ready() {
        if (tasks.size() != 0)
            return true;
        return false;
    }

    @Override
    public void Wait() {
        for (Task task : tasks) {
            task.waitTime++;
        }
        if(runcount != 0){
            Task tmp = tasks.get(0);
            tasks.remove(0);
            tasks.add(tmp);
            runcount = 0;
        }
    }

    @Override
    public List<Task> Result() {
        return done;
    }
    
}