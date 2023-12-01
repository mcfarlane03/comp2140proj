package src;
public class Job
{

    protected static int jobCount = 0;
    private int jobID;

    private String jobDescript;
    private Driver driver;
    private Truck truck;
    private int load;
    private String destination;
    private String deptTime;
    private String retTime;
    
    
    public Job(String jobDescript, Driver driver, Truck truck, int load, String destination, String deptTime, String retTime) {
        this.jobID = ++jobCount;
        this.jobDescript = jobDescript;
        this.driver = driver;
        this.truck = truck;
        this.load = load;
        this.destination = destination;
        this.deptTime = deptTime;
        this.retTime = retTime;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobDescript() {
        return jobDescript;
    }

    public void setJobDescript(String jobDescript) {
        this.jobDescript = jobDescript;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeptTime() {
        return deptTime;
    }

    public void setDeptTime(String deptTime) {
        this.deptTime = deptTime;
    }

    public String getRetTime() {
        return retTime;
    }

    public void setRetTime(String retTime) {
        this.retTime = retTime;
    }

    @Override
    public String toString() {
        return jobID + "," + jobDescript + "," + driver.getID() + "," + truck.getID() + "," + load + "," + destination + "," + deptTime + "," + retTime;
    }
}
