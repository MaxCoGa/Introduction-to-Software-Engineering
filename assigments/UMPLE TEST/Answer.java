/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/



// line 69 "Part2.ump"
public class Answer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Answer Attributes
  private int value;

  //Answer Associations
  private Question question;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Answer(int aValue, Question aQuestion)
  {
    value = aValue;
    boolean didAddQuestion = setQuestion(aQuestion);
    if (!didAddQuestion)
    {
      throw new RuntimeException("Unable to create answer due to question");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setValue(int aValue)
  {
    boolean wasSet = false;
    value = aValue;
    wasSet = true;
    return wasSet;
  }

  public int getValue()
  {
    return value;
  }

  public Question getQuestion()
  {
    return question;
  }

  public boolean setQuestion(Question aQuestion)
  {
    boolean wasSet = false;
    //Must provide question to answer
    if (aQuestion == null)
    {
      return wasSet;
    }

    if (question != null && question.numberOfAnswers() <= Question.minimumNumberOfAnswers())
    {
      return wasSet;
    }

    Question existingQuestion = question;
    question = aQuestion;
    if (existingQuestion != null && !existingQuestion.equals(aQuestion))
    {
      boolean didRemove = existingQuestion.removeAnswer(this);
      if (!didRemove)
      {
        question = existingQuestion;
        return wasSet;
      }
    }
    question.addAnswer(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Question placeholderQuestion = question;
    this.question = null;
    placeholderQuestion.removeAnswer(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "value" + ":" + getValue()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "question = "+(getQuestion()!=null?Integer.toHexString(System.identityHashCode(getQuestion())):"null");
  }
}