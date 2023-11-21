public class Truck
{
    private String truckdesc, ttype, ID;
    private int wclass;

    public Truck(String truckdesc, String ttype, String ID, int wclass)
    {
        this.truckdesc = truckdesc;
        this.ttype = ttype;
        this.ID = ID;
        this.wclass = wclass;
    }

    public String getTruckdesc()
    {
        return this.truckdesc;
    }

    protected void setTruckdesc(String truckdesc)
    {
        this.truckdesc = truckdesc;
    }

    public String getTtype()
    {
        return this.ttype;
    }

    protected void setTtype(String ttype)
    {
        this.ttype = ttype;
    }

    public String getID()
    {
        return this.ID;
    }

    protected void setID(String ID)
    {
        this.ID = ID;
    }

    public int getWclass()
    {
        return this.wclass;
    }

    protected void setWclass(int wclass)
    {
        this.wclass = wclass;
    }

    public String toString() 
    {
        return getTruckdesc() + " " + getTtype()  + " " + getID() + " " + getWclass();
    }

    public boolean compareTo(Truck other) {
        return other.getID().equals(this.getID());
    }
}
