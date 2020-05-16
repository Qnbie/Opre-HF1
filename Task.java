
public class Task implements Comparable<Task>{
    String name;
    int prior;
    int start;
    int cpu;
    int waitTime = 0;

    public Task(String name, String prior, String start, String cpu){
        this.name = name;
        this.prior = Integer.parseInt(prior);
        this.start = Integer.parseInt(start);
        this.cpu = Integer.parseInt(cpu);
    }

    @Override
    public String toString() {
        return(name + ":" + waitTime);
    }

    @Override
    public int compareTo(Task o) {
        return this.name.compareTo(o.name);
    }

    
}