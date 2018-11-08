/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/


import java.util.*;

// line 26 "Part2.ump"
public class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private String courseCode;

  //Course Associations
  private TopHat topHat;
  private List<Section> sections;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Course(String aCourseCode, TopHat aTopHat)
  {
    courseCode = aCourseCode;
    boolean didAddTopHat = setTopHat(aTopHat);
    if (!didAddTopHat)
    {
      throw new RuntimeException("Unable to create course due to topHat");
    }
    sections = new ArrayList<Section>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCourseCode(String aCourseCode)
  {
    boolean wasSet = false;
    courseCode = aCourseCode;
    wasSet = true;
    return wasSet;
  }

  public String getCourseCode()
  {
    return courseCode;
  }

  public TopHat getTopHat()
  {
    return topHat;
  }

  public Section getSection(int index)
  {
    Section aSection = sections.get(index);
    return aSection;
  }

  public List<Section> getSections()
  {
    List<Section> newSections = Collections.unmodifiableList(sections);
    return newSections;
  }

  public int numberOfSections()
  {
    int number = sections.size();
    return number;
  }

  public boolean hasSections()
  {
    boolean has = sections.size() > 0;
    return has;
  }

  public int indexOfSection(Section aSection)
  {
    int index = sections.indexOf(aSection);
    return index;
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
      existingTopHat.removeCourse(this);
    }
    topHat.addCourse(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfSections()
  {
    return 0;
  }

  public Section addSection(String aSectionLabel, Professor aProfessor)
  {
    return new Section(aSectionLabel, aProfessor, this);
  }

  public boolean addSection(Section aSection)
  {
    boolean wasAdded = false;
    if (sections.contains(aSection)) { return false; }
    Course existingCourse = aSection.getCourse();
    boolean isNewCourse = existingCourse != null && !this.equals(existingCourse);
    if (isNewCourse)
    {
      aSection.setCourse(this);
    }
    else
    {
      sections.add(aSection);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSection(Section aSection)
  {
    boolean wasRemoved = false;
    //Unable to remove aSection, as it must always have a course
    if (!this.equals(aSection.getCourse()))
    {
      sections.remove(aSection);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSectionAt(Section aSection, int index)
  {  
    boolean wasAdded = false;
    if(addSection(aSection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSections()) { index = numberOfSections() - 1; }
      sections.remove(aSection);
      sections.add(index, aSection);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSectionAt(Section aSection, int index)
  {
    boolean wasAdded = false;
    if(sections.contains(aSection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSections()) { index = numberOfSections() - 1; }
      sections.remove(aSection);
      sections.add(index, aSection);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSectionAt(aSection, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    TopHat placeholderTopHat = topHat;
    this.topHat = null;
    placeholderTopHat.removeCourse(this);
    for(int i=sections.size(); i > 0; i--)
    {
      Section aSection = sections.get(i - 1);
      aSection.delete();
    }
  }

  // line 31 "Part2.ump"
   public void setSection(Section arg0){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "courseCode" + ":" + getCourseCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "topHat = "+(getTopHat()!=null?Integer.toHexString(System.identityHashCode(getTopHat())):"null");
  }
}