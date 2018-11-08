/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/



// line 10 "Part2.ump"
public class Professor extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Professor Associations
  private Section section;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Professor(String aUsername, String aPassword, int aId, String aRealName, TopHat aTopHat, Section aSection)
  {
    super(aUsername, aPassword, aId, aRealName, aTopHat);
    if (aSection == null || aSection.getProfessor() != null)
    {
      throw new RuntimeException("Unable to create Professor due to aSection");
    }
    section = aSection;
  }

  public Professor(String aUsername, String aPassword, int aId, String aRealName, TopHat aTopHat, String aSectionLabelForSection, Course aCourseForSection)
  {
    super(aUsername, aPassword, aId, aRealName, aTopHat);
    section = new Section(aSectionLabelForSection, this, aCourseForSection);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Section getSection()
  {
    return section;
  }

  public void delete()
  {
    Section existingSection = section;
    section = null;
    if (existingSection != null)
    {
      existingSection.delete();
    }
    super.delete();
  }

}