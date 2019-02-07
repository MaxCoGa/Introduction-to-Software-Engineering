/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/


import java.util.*;

// line 16 "Part2.ump"
public class Student extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Student Attributes
  private String cellPhone;

  //Student Associations
  private Section section;
  private List<Grade> grades;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Student(String aUsername, String aPassword, int aId, String aRealName, TopHat aTopHat, String aCellPhone, Section aSection)
  {
    super(aUsername, aPassword, aId, aRealName, aTopHat);
    cellPhone = aCellPhone;
    boolean didAddSection = setSection(aSection);
    if (!didAddSection)
    {
      throw new RuntimeException("Unable to create student due to section");
    }
    grades = new ArrayList<Grade>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCellPhone(String aCellPhone)
  {
    boolean wasSet = false;
    cellPhone = aCellPhone;
    wasSet = true;
    return wasSet;
  }

  public String getCellPhone()
  {
    return cellPhone;
  }

  public Section getSection()
  {
    return section;
  }

  public Grade getGrade(int index)
  {
    Grade aGrade = grades.get(index);
    return aGrade;
  }

  public List<Grade> getGrades()
  {
    List<Grade> newGrades = Collections.unmodifiableList(grades);
    return newGrades;
  }

  public int numberOfGrades()
  {
    int number = grades.size();
    return number;
  }

  public boolean hasGrades()
  {
    boolean has = grades.size() > 0;
    return has;
  }

  public int indexOfGrade(Grade aGrade)
  {
    int index = grades.indexOf(aGrade);
    return index;
  }

  public boolean setSection(Section aSection)
  {
    boolean wasSet = false;
    if (aSection == null)
    {
      return wasSet;
    }

    Section existingSection = section;
    section = aSection;
    if (existingSection != null && !existingSection.equals(aSection))
    {
      existingSection.removeStudent(this);
    }
    section.addStudent(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfGrades()
  {
    return 0;
  }

  public Grade addGrade(Question... allQuestions)
  {
    return new Grade(this, allQuestions);
  }

  public boolean addGrade(Grade aGrade)
  {
    boolean wasAdded = false;
    if (grades.contains(aGrade)) { return false; }
    Student existingStudent = aGrade.getStudent();
    boolean isNewStudent = existingStudent != null && !this.equals(existingStudent);
    if (isNewStudent)
    {
      aGrade.setStudent(this);
    }
    else
    {
      grades.add(aGrade);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGrade(Grade aGrade)
  {
    boolean wasRemoved = false;
    //Unable to remove aGrade, as it must always have a student
    if (!this.equals(aGrade.getStudent()))
    {
      grades.remove(aGrade);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addGradeAt(Grade aGrade, int index)
  {  
    boolean wasAdded = false;
    if(addGrade(aGrade))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGrades()) { index = numberOfGrades() - 1; }
      grades.remove(aGrade);
      grades.add(index, aGrade);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGradeAt(Grade aGrade, int index)
  {
    boolean wasAdded = false;
    if(grades.contains(aGrade))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGrades()) { index = numberOfGrades() - 1; }
      grades.remove(aGrade);
      grades.add(index, aGrade);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGradeAt(aGrade, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Section placeholderSection = section;
    this.section = null;
    placeholderSection.removeStudent(this);
    for(int i=grades.size(); i > 0; i--)
    {
      Grade aGrade = grades.get(i - 1);
      aGrade.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "cellPhone" + ":" + getCellPhone()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "section = "+(getSection()!=null?Integer.toHexString(System.identityHashCode(getSection())):"null");
  }
}