/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/



// line 1 "Part2.ump"
public class Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private String username;
  private String password;
  private int id;
  private String realName;

  //Account Associations
  private TopHat topHat;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(String aUsername, String aPassword, int aId, String aRealName, TopHat aTopHat)
  {
    username = aUsername;
    password = aPassword;
    id = aId;
    realName = aRealName;
    boolean didAddTopHat = setTopHat(aTopHat);
    if (!didAddTopHat)
    {
      throw new RuntimeException("Unable to create account due to topHat");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setRealName(String aRealName)
  {
    boolean wasSet = false;
    realName = aRealName;
    wasSet = true;
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public int getId()
  {
    return id;
  }

  public String getRealName()
  {
    return realName;
  }

  public TopHat getTopHat()
  {
    return topHat;
  }

  public boolean setTopHat(TopHat aTopHat)
  {
    boolean wasSet = false;
    if (aTopHat == null)
    {
      return wasSet;
    }

    TopHat existingTopHat = topHat;
    topHat = aTopHat;
    if (existingTopHat != null && !existingTopHat.equals(aTopHat))
    {
      existingTopHat.removeAccount(this);
    }
    topHat.addAccount(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    TopHat placeholderTopHat = topHat;
    this.topHat = null;
    placeholderTopHat.removeAccount(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "id" + ":" + getId()+ "," +
            "realName" + ":" + getRealName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "topHat = "+(getTopHat()!=null?Integer.toHexString(System.identityHashCode(getTopHat())):"null");
  }
}