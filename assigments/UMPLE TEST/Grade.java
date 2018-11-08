/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/


import java.util.*;

// line 60 "Part2.ump"
public class Grade
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Grade Associations
  private Student student;
  private List<Question> questions;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Grade(Student aStudent, Question... allQuestions)
  {
    boolean didAddStudent = setStudent(aStudent);
    if (!didAddStudent)
    {
      throw new RuntimeException("Unable to create grade due to student");
    }
    questions = new ArrayList<Question>();
    boolean didAddQuestions = setQuestions(allQuestions);
    if (!didAddQuestions)
    {
      throw new RuntimeException("Unable to create Grade, must have at least 1 questions");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Student getStudent()
  {
    return student;
  }

  public Question getQuestion(int index)
  {
    Question aQuestion = questions.get(index);
    return aQuestion;
  }

  public List<Question> getQuestions()
  {
    List<Question> newQuestions = Collections.unmodifiableList(questions);
    return newQuestions;
  }

  public int numberOfQuestions()
  {
    int number = questions.size();
    return number;
  }

  public boolean hasQuestions()
  {
    boolean has = questions.size() > 0;
    return has;
  }

  public int indexOfQuestion(Question aQuestion)
  {
    int index = questions.indexOf(aQuestion);
    return index;
  }

  public boolean setStudent(Student aStudent)
  {
    boolean wasSet = false;
    if (aStudent == null)
    {
      return wasSet;
    }

    Student existingStudent = student;
    student = aStudent;
    if (existingStudent != null && !existingStudent.equals(aStudent))
    {
      existingStudent.removeGrade(this);
    }
    student.addGrade(this);
    wasSet = true;
    return wasSet;
  }

  public boolean isNumberOfQuestionsValid()
  {
    boolean isValid = numberOfQuestions() >= minimumNumberOfQuestions();
    return isValid;
  }

  public static int minimumNumberOfQuestions()
  {
    return 1;
  }

  public boolean addQuestion(Question aQuestion)
  {
    boolean wasAdded = false;
    if (questions.contains(aQuestion)) { return false; }
    questions.add(aQuestion);
    if (aQuestion.indexOfGrade(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aQuestion.addGrade(this);
      if (!wasAdded)
      {
        questions.remove(aQuestion);
      }
    }
    return wasAdded;
  }

  public boolean removeQuestion(Question aQuestion)
  {
    boolean wasRemoved = false;
    if (!questions.contains(aQuestion))
    {
      return wasRemoved;
    }

    if (numberOfQuestions() <= minimumNumberOfQuestions())
    {
      return wasRemoved;
    }

    int oldIndex = questions.indexOf(aQuestion);
    questions.remove(oldIndex);
    if (aQuestion.indexOfGrade(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aQuestion.removeGrade(this);
      if (!wasRemoved)
      {
        questions.add(oldIndex,aQuestion);
      }
    }
    return wasRemoved;
  }

  public boolean setQuestions(Question... newQuestions)
  {
    boolean wasSet = false;
    ArrayList<Question> verifiedQuestions = new ArrayList<Question>();
    for (Question aQuestion : newQuestions)
    {
      if (verifiedQuestions.contains(aQuestion))
      {
        continue;
      }
      verifiedQuestions.add(aQuestion);
    }

    if (verifiedQuestions.size() != newQuestions.length || verifiedQuestions.size() < minimumNumberOfQuestions())
    {
      return wasSet;
    }

    ArrayList<Question> oldQuestions = new ArrayList<Question>(questions);
    questions.clear();
    for (Question aNewQuestion : verifiedQuestions)
    {
      questions.add(aNewQuestion);
      if (oldQuestions.contains(aNewQuestion))
      {
        oldQuestions.remove(aNewQuestion);
      }
      else
      {
        aNewQuestion.addGrade(this);
      }
    }

    for (Question anOldQuestion : oldQuestions)
    {
      anOldQuestion.removeGrade(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addQuestionAt(Question aQuestion, int index)
  {  
    boolean wasAdded = false;
    if(addQuestion(aQuestion))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfQuestions()) { index = numberOfQuestions() - 1; }
      questions.remove(aQuestion);
      questions.add(index, aQuestion);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveQuestionAt(Question aQuestion, int index)
  {
    boolean wasAdded = false;
    if(questions.contains(aQuestion))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfQuestions()) { index = numberOfQuestions() - 1; }
      questions.remove(aQuestion);
      questions.add(index, aQuestion);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addQuestionAt(aQuestion, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Student placeholderStudent = student;
    this.student = null;
    placeholderStudent.removeGrade(this);
    ArrayList<Question> copyOfQuestions = new ArrayList<Question>(questions);
    questions.clear();
    for(Question aQuestion : copyOfQuestions)
    {
      aQuestion.removeGrade(this);
    }
  }

  // line 64 "Part2.ump"
   public Integer calculateTotalGrade(){
    
  }

}