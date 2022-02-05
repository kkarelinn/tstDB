package com.tst.tstdb.controllers;

import com.tst.tstdb.models.Question;
import com.tst.tstdb.models.User;
import com.tst.tstdb.models.repository.QuestionsRep;
import com.tst.tstdb.models.repository.UsersRep;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Validated
@RequestMapping("/launch")

public class LaunchController {
    private final QuestionsRep questionsRep;
    private final UsersRep usersRep;
    private List<Question> list=null;
    private List<Integer> questionsIDsWas = new ArrayList<>();
    private int count;
    private User currentUser;
    private int MAX_COUNT = 5;

    public LaunchController(QuestionsRep questionsRep, UsersRep usersRep) {
        this.questionsRep = questionsRep;
        this.usersRep = usersRep;
    }

    @GetMapping()
    public String start1(@NotBlank @Valid @RequestParam("name")  String name) {


//           if (bindingResult.hasErrors())
//            return "index";
        list = questionsRep.findAll();
        currentUser = new User(name, 0);
        if (usersRep.findByName(currentUser.getName()) == null) {
            usersRep.save(currentUser);}
        return "redirect:/launch/la";
    }

    @GetMapping("/la")
    public String start(Model model) {
        int queID = 0;

        queID = getRandomID(questionsIDsWas);
        count++;
        System.out.println("Заданных вопросов " + count + ", вопрос номер " + queID);
        model.addAttribute("user", currentUser);
        model.addAttribute("question", list.get(queID));
        return "test";


    }

    @PostMapping("/action")
    public String action(@RequestParam("idQ") int idQ, @RequestParam("answer") String answer, Model model) //, @RequestParam("name") String name)
    {
        System.out.println("ID вопроса" + idQ);
        System.out.println("ответ студента " + answer);
        Question current = questionsRep.findById(idQ);
        String rightAnswer = current.getAnswer();
        if (!answer.isEmpty() && answer.equals(rightAnswer))
            currentUser.setMaxTotalPoints(currentUser.getMaxTotalPoints() + current.getPoints());

        if (count < MAX_COUNT) return "redirect:/launch/la";
        else {
                User userT = usersRep.findByName(currentUser.getName());
                userT.setMaxTotalPoints(currentUser.getMaxTotalPoints());
                usersRep.save(userT);
            return "redirect:/launch/result";
        }

    }

    @GetMapping("/result")
    public String resultL(Model model) {
        model.addAttribute("user", currentUser);
        model.addAttribute("users", usersRep.findAll());

        return "result";
    }

    @GetMapping("/final")
    public String finalL() {
        count = 0;
        currentUser = null;
        questionsIDsWas = new ArrayList<>();
        return "index";
    }

    private int getRandomID(List<Integer> questionsIDsWas) {
        while (true) {
            int ret = (int) (1 + Math.random() * (list.size() - 1));
            if (!questionsIDsWas.contains(ret)) {
                questionsIDsWas.add(ret);
                return ret;
            }
        }
    }

    @ExceptionHandler
   @GetMapping("/error")
    public String handle(ConstraintViolationException exception, Model model) {
String catchCause = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage).findFirst().get();
//        return error();
        model.addAttribute("catch", catchCause);

        return "index";
    }

    private Map error(Object message) {
        return Collections.singletonMap("error", message);
    }


}
