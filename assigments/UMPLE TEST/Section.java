/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/


import java.util.*;

// line 36 "Part2.ump"
public class Section
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Section Attributes
  private String sectionLabel;

  //Section Associations
  private List<Student> students;
  private Professor professor;
  private Course course;
  private List<Question> questions;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Section(String aSectionLabel, Professor aProfessor, Course aCourse)
  {
    sectionLabel = aSectionLabel;
    students = new ArrayList<Student>();
    if (aProfessor == null || aProfessor.getSection() != null)
    {
      throw new RuntimeException("Unable to create Section due to aProfessor");
    }
    professor = aProfessor;
    boolean didAddCourse = setCourse(aCourse);
    if (!didAddCourse)
    {
      throw new RuntimeException("Unable to create section due to course");
    }
    questions = new ArrayList<Question>();
  }

  public Section(String aSectionLabel, String aUsernameForProfessor, String aPasswordForProfessor, int aIdForProfessor, String aRealNameForProfessor, TopHat aTopHatForProfessor, Course aCourse)
  {
    sectionLabel = aSectionLabel;
    students = new ArrayList<Student>();
    professor = new Professor(aUsernameForProfessor, aPasswordForProfessor, aIdForProfessor, aRealNameForProfessor, aTopHatForProfessor, this);
    boolean didAddCourse = setCourse(aCourse);
    if (!didAddCourse)
    {
      throw new RuntimeException("Unable to create section due to course");
    }
    questions = new ArrayList<Question>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSectionLabel(String aSectionLabel)
  {
    boolean wasSet = false;
    sectionLabel = aSectionLabel;
    wasSet = true;
    return wasSet;
  }

  public String getSectionLabel()
  {
    return sectionLabel;
  }

  public Student getStudent(int index)
  {
    Student aStudent = students.get(index);
    return aStudent;
  }

  public List<Student> getStudents()
  {
    List<Student> newStudents = Collections.unmodifiableList(students);
    return newStudents;
  }

  public int numberOfStudents()
  {
    int number = students.size();
    return number;
  }

  public boolean hasStudents()
  {
    boolean has = students.size() > 0;
    return has;
  }

  public int indexOfStudent(Student aStudent)
  {
    int index = students.indexOf(aStudent);
    return index;
  }

  public Professor getProfessor()
  {
    return professor;
  }

  public Course getCourse()
  {
    return course;
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

  public static int minimumNumberOfStudents()
  {
    return 0;
  }

  public Student addStudent(String aUsername, String aPassword, int aId, String aRealName, TopHat aTopHat, String aCellPhone)
  {
    return new Student(aUsername, aPassword, aId, aRealName, aTopHat, aCellPhone, this);
  }

  public boolean addStudent(Student aStudent)
  {
    boolean wasAdded = false;
    if (students.contains(aStudent)) { return false; }
    Section existingSection = aStudent.getSection();
    boolean isNewSection = existingSection != null && !this.equals(existingSection);
    if (isNewSection)
    {
      aStudent.setSection(this);
    }
    else
    {
      students.add(aStudent);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStudent(Student aStudent)
  {
    boolean wasRemoved = false;
    //Unable to remove aStudent, as it must always have a section
    if (!this.equals(aStudent.getSection()))
    {
      students.remove(aStudent);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addStudentAt(Student aStudent, int index)
  {  
    boolean wasAdded = false;
    if(addStudent(aStudent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStudents()) { index = numberOfStudents() - 1; }
      students.remove(aStudent);
      students.add(index, aStudent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStudentAt(Student aStudent, int index)
  {
    boolean wasAdded = false;
    if(students.contains(aStudent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStudents()) { index = numberOfStudents() - 1; }
      students.remove(aStudent);
      students.add(index, aStudent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStudentAt(aStudent, index);
    }
    return wasAdded;
  }

  public boolean setCourse(Course aCourse)
  {
    boolean wasSet = false;
    if (aCourse == null)
    {
      return wasSet;
    }

    Course existingCourse = course;
    course = aCourse;
    if (existingCourse != null && !existingCourse.equals(aCourse))
    {
      existingCourse.removeSection(this);
    }
    course.addSection(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfQuestions()
  {
    return 0;
  }

  public boolean addQuestion(Question aQuestion)
  {
    boolean wasAdded = false;
    if (questions.contains(aQuestion)) { return false; }
    questions.add(aQuestion);
    if (aQuestion.indexOfSection(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aQuestion.addSection(this);
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

    int oldIndex = questions.indexOf(aQuestion);
    questions.remove(oldIndex);
    if (aQuestion.indexOfSection(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aQuestion.removeSection(this);
      if (!wasRemoved)
      {
        questions.add(oldIndex,aQuestion);
      }
    }
    return wasRemoved;
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
    for(int i=students.size(); i > 0; i--)
    {
      Student aStudent = students.get(i - 1);
      aStudent.delete();
    }
    Professor existingProfessor = professor;
    professor = null;
    if (existingProfessor != null)
    {
      existingProfessor.delete();
    }
    Course placeholderCourse = course;
    this.course = null;
    placeholderCourse.removeSection(this);
    ArrayList<Question> copyOfQuestions = new ArrayList<Question>(questions);
    questions.clear();
    for(Question aQuestion : copyOfQuestions)
    {
      aQuestion.removeSection(this);
    }
  }

  // line 41 "Part2.ump"
   public void createQuestion(Professor arg0){
    
  }

  // line 44 "Part2.ump"
   public void answerQuestion(Student arg0, Question arg1, Answer arg2){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "sectionLabel" + ":" + getSectionLabel()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "professor = "+(getProfessor()!=null?Integer.toHexString(System.identityHashCode(getProfessor())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "course = "+(getCourse()!=null?Integer.toHexString(System.identityHashCode(getCourse())):"null");
  }
}