package org.example.miniproject1.Model;

public class Quiz {
    private int quizId;
    private String question_text;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correct_answer;
    public Quiz(int quizId, String correct_answer, String optionD, String optionC, String optionB, String optionA, String question_text) {
        this.quizId = quizId;
        this.correct_answer = correct_answer;
        this.optionD = optionD;
        this.optionC = optionC;
        this.optionB = optionB;
        this.optionA = optionA;
        this.question_text = question_text;
    }

    public Quiz() {


    }

    public Quiz(String correctAnswer, String optionA, String optionB, String optionC, String optionD, String questionText) {
        this.correct_answer = correctAnswer;
        this.optionD = optionD;
        this.optionC = optionC;
        this.optionB = optionB;
        this.optionA = optionA;
        this.question_text = questionText;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }



}

