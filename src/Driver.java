package src;
public class Driver implements Comparable<Driver> 
{
    private String fname, sname, gender, tphone, email, ltype;
    private int age, ID, lclass;

    public Driver(String fname, String sname, String gender, String tphone, String email, String ltype, int age, int ID, int lclass)
    {
        this.fname = fname;
        this.sname = sname;
        this.gender = gender;
        this.tphone = tphone;
        this.email = email;
        this.ltype = ltype;
        this.age = age;
        this.ID = ID;
        this.lclass = lclass;
    }

    public String getFname()
    {
        return this.fname;
    }

    protected void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getSname()
    {
        return this.sname;
    }

    protected void setSname(String sname)
    {
        this.sname = sname;
    }
    
    public String getGender()
    {
        return this.gender;
    }

    protected void setGender(String gender)
    {
        this.gender = gender;
    }
    
    public String getTphone()
    {
        return this.tphone;
    }

    protected void setTphone(String tphone)
    {
        this.tphone = tphone;
    }
    
    public String getEmail()
    {
        return this.email;
    }

    protected void setEmail(String email)
    {
        this.email = email;
    }

    public String getLtype()
    {
        return this.ltype;
    }

    protected void setLtype(String ltype)
    {
        this.ltype = ltype;
    }
    
    public int getAge()
    {
        return this.age;
    }

    protected void setAge(int age)
    {
        this.age = age;
    }

    public int getID()
    {
        return this.ID;
    }

    protected void setID(int ID)
    {
        this.ID = ID;
    }

    public int getLclass()
    {
        return this.lclass;
    }

    protected void setLclass(int lclass)
    {
        this.lclass = lclass;
    }

    public String toString() 
    {
        return getFname() + "," + getSname() + "," + getGender() + "," + getTphone() + "," + getEmail() + "," + getLtype() + "," + getAge()
                + "," + getID() + "," + getLclass();
    }

    @Override
    public int compareTo(Driver other) {
        return other.getID() - this.getID();
    }

}
