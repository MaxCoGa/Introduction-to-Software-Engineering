/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/


import java.util.*;

// line 48 "Part2.ump"
public class Question
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Question Attributes
  private String status;

  //Question Associations
  private List<Section> sections;
  private List<Grade> grades;
  private List<Answer> answers;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Question(String aStatus)
  {
    status = aStatus;
    sections = new ArrayList<Section>();
    grades = new ArrayList<Grade>();
    answers = new ArrayList<Answer>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStatus(String aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public String getStatus()
  {
    return status;
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

  public Answer getAnswer(int index)
  {
    Answer aAnswer = answers.get(index);
    return aAnswer;
  }

  public List<Answer> getAnswers()
  {
    List<Answer> newAnswers = Collections.unmodifiableList(answers);
    return newAnswers;
  }

  public int numberOfAnswers()
  {
    int number = answers.size();
    return number;
  }

  public boolean hasAnswers()
  {
    boolean has = answers.size() > 0;
    return has;
  }

  public int indexOfAnswer(Answer aAnswer)
  {
    int index = answers.indexOf(aAnswer);
    return index;
  }

  public static int minimumNumberOfSections()
  {
    return 0;
  }

  public boolean addSection(Section aSection)
  {
    boolean wasAdded = false;
    if (sections.contains(aSection)) { return false; }
    sections.add(aSection);
    if (aSection.indexOfQuestion(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSection.addQuestion(this);
      if (!wasAdded)
      {
        sections.remove(aSection);
      }
    }
    return wasAdded;
  }

  public boolean removeSection(Section aSection)
  {
    boolean wasRemoved = false;
    if (!sections.contains(aSection))
    {
      return wasRemoved;
    }

    int oldIndex = sections.indexOf(aSection);
    sections.remove(oldIndex);
    if (aSection.indexOfQuestion(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSection.removeQuestion(this);
      if (!wasRemoved)
      {
        sections.add(oldIndex,aSection);
      }
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

  public static int minimumNumberOfGrades()
  {
    return 0;
  }

  public boolean addGrade(Grade aGrade)
  {
    boolean wasAdded = false;
    if (grades.contains(aGrade)) { return false; }
    grades.add(aGrade);
    if (aGrade.indexOfQuestion(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aGrade.addQuestion(this);
      if (!wasAdded)
      {
        grades.remove(aGrade);
      }
    }
    return wasAdded;
  }

  public boolean removeGrade(Grade aGrade)
  {
    boolean wasRemoved = false;
    if (!grades.contains(aGrade))
    {
      return wasRemoved;
    }

    int oldIndex = grades.indexOf(aGrade);
    grades.remove(oldIndex);
    if (aGrade.indexOfQuestion(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aGrade.removeQuestion(this);
      if (!wasRemoved)
      {
        grades.add(oldIndex,aGrade);
      }
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

  public boolean isNumberOfAnswersValid()
  {
    boolean isValid = numberOfAnswers() >= minimumNumberOfAnswers();
    return isValid;
  }

  public static int minimumNumberOfAnswers()
  {
    return 1;
  }

  public Answer addAnswer(int aValue)
  {
    Answer aNewAnswer = new Answer(aValue, this);
    return aNewAnswer;
  }

  public boolean addAnswer(Answer aAnswer)
  {
    boolean wasAdded = false;
    if (answers.contains(aAnswer)) { return false; }
    Question existingQuestion = aAnswer.getQuestion();
    boolean isNewQuestion = existingQuestion != null && !this.equals(existingQuestion);

    if (isNewQuestion && existingQuestion.numberOfAnswers() <= minimumNumberOfAnswers())
    {
      return wasAdded;
    }
    if (isNewQuestion)
    {
      aAnswer.setQuestion(this);
    }
    else
    {
      answers.add(aAnswer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAnswer(Answer aAnswer)
  {
    boolean wasRemoved = false;
    //Unable to remove aAnswer, as it must always have a question
    if (this.equals(aAnswer.getQuestion()))
    {
      return wasRemoved;
    }

    //question already at minimum (1)
    if (numberOfAnswers() <= minimumNumberOfAnswers())
    {
      return wasRemoved;
    }

    answers.remove(aAnswer);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addAnswerAt(Answer aAnswer, int index)
  {  
    boolean wasAdded = false;
    if(addAnswer(aAnswer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAnswers()) { index = numberOfAnswers() - 1; }
      answers.remove(aAnswer);
      answers.add(index, aAnswer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAnswerAt(Answer aAnswer, int index)
  {
    boolean wasAdded = false;
    if(answers.contains(aAnswer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAnswers()) { index = numberOfAnswers() - 1; }
      answers.remove(aAnswer);
      answers.add(index, aAnswer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAnswerAt(aAnswer, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Section> copyOfSections = new ArrayList<Section>(sections);
    sections.clear();
    for(Section aSection : copyOfSections)
    {
      aSection.removeQuestion(this);
    }
    ArrayList<Grade> copyOfGrades = new ArrayList<Grade>(grades);
    grades.clear();
    for(Grade aGrade : copyOfGrades)
    {
      if (aGrade.numberOfQuestions() <= Grade.minimumNumberOfQuestions())
      {
        aGrade.delete();
      }
      else
      {
        aGrade.removeQuestion(this);
      }
    }
    for(int i=answers.size(); i > 0; i--)
    {
      Answer aAnswer = answers.get(i - 1);
      aAnswer.delete();
    }
  }

  // line 53 "Part2.ump"
   public void setActive(Boolean arg0){
    
  }

  // line 56 "Part2.ump"
   public void addAnswer(Answer arg0){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "status" + ":" + getStatus()+ "]";
  }
}