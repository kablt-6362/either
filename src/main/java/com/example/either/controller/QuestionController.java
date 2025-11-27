package com.example.either.controller;

import com.example.either.entity.Answer;
import com.example.either.entity.Question;
import com.example.either.service.AnswerService;
import com.example.either.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;


    @GetMapping("")
    public String list(Model model){
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions",questions);
        return "list";
    }

    @GetMapping("/new")
    public String newlist(Model model){
        model.addAttribute("question",new Question());
        return "form";
    }

    @PostMapping("")
    public String create(@ModelAttribute Question question)
    {
        questionService.createQuestion(question);
        return "redirect:/questions";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id,
                         Model model){

        Question question = questionService.getById(id);
        model.addAttribute("question",question);
        model.addAttribute("answer",new Answer());
        return "detail";
    }

    @PostMapping("/{id}/answers")
    public String createAnswer(@PathVariable Long id, @ModelAttribute Answer answer){
        System.out.println(answer);
        answerService.createAnswer(id,answer);

        return "redirect:/questions/"+ id;
    }



}
