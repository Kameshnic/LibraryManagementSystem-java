package org.example.miniproject1.Model;

import java.util.List;

public class QuizQuestion {
    private String question;
    private List<String> answers;
    private String correctAnswer;

    public QuizQuestion(String question, List<String> answers, String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
