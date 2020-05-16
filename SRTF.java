import java.util.ArrayList;
import java.util.List;

public class SRTF implements Scheduler {

    List<Task> tasks = new ArrayList<Task>();
    List<Task> done = new ArrayList<Task>();

    @Override
    public String Run() {
        for (int i = 1; i < tasks.size(); i++) {
            tasks.get(i).waitTime++;
        }

        tasks.get(0).cpu--;
        String name = tasks.get(0).name;

        if(tasks.get(0).cpu == 0){
            Task tmp = tasks.get(0);
            tasks.remove(0);
            done.add(tmp);
        }

        return name;
    }

    @Override
    public void Put(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).cpu > task.cpu){
                tasks.add(i, task);
                return;
            }
        }
        tasks.add(task);
    }

    @Override
    public boolean Ready() {
        if(tasks.size() != 0) return true;
        return false;
    }

    @Override
    public void Wait() {
        for (Task task : tasks) {
            task.waitTime++;
        }
    }

    @Override
    public List<Task> Result() {
        return done;
    }

}