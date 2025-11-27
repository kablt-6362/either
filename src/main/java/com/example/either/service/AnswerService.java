package com.example.either.service;

import com.example.either.entity.Answer;
import com.example.either.entity.Question;
import com.example.either.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {


    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    public Answer create(Answer newAnswer) {
        return answerRepository.save(newAnswer);
    }

    @Transactional
    public Answer createAnswer(Long id, Answer answer) {
        Question question = questionService.getById(id);
        question.addAnswer(answer);
        Answer saved = answerRepository.save(answer);
        return saved;
    }

    public List<Answer> getAllAnswer(Long id) {
        return answerRepository.findByQuestionId(id);

    }

    public int getAnswerTextA(Long id) {
        List<Answer> answers = answerRepository.findByQuestionId(id).stream().filter(answer -> answer.getAnswerText().equals("A")).toList();

        return answers.size();
    }

    public int getAnswerTextB(Long id) {
        List<Answer> answers = answerRepository.findByQuestionId(id).stream().filter(answer -> answer.getAnswerText().equals("B")).toList();

        return answers.size();

    }
}
