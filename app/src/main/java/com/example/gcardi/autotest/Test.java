package com.example.gcardi.autotest;

import java.util.List;

public class Test {

    private Integer question;
    private Integer image_id;
    private List<Integer> answers;
    private Integer rightAnswer;

    public Test(Integer qusetion, Integer image_id, List<Integer> answers, Integer rightAnswer) {
        this.question = qusetion;
        this.image_id = image_id;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
    }

    public Integer getQuestion() {
        return question;
    }

    public void setQuestion(Integer question) {
        this.question = question;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
