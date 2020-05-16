import java.util.List;

interface Scheduler {
    public String Run();
    public void Put(Task task);
    public boolean Ready();
    public void Wait();
    public List<Task> Result();
}