package com.rashid.springboot.webApp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TodoControllerJpa {
    public TodoControllerJpa(TodoRepository todoRepository) {

        super();

        this.todoRepository = todoRepository;
    }

    private TodoRepository todoRepository;

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap modelMap) {
        String username = getLoggedInUsername(modelMap);
        List<Todo> todos =  todoRepository.findByUserName(username);
        modelMap.addAttribute("todos",todos);

        return "listOfTodos";
    }
    @RequestMapping(value="add-todo",method = RequestMethod.GET)
    public String addTodo(ModelMap modelMap) {
        String username =  getLoggedInUsername(modelMap);
        Todo todo =new Todo(0, username, "Default Desc", LocalDate.now().plusYears(1), false);

        modelMap.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value="add-todo",method = RequestMethod.POST)
    public String  addNewTodo(ModelMap modelMap , @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String name = getLoggedInUsername(modelMap);
        todo.setUserName(name);
        todoRepository.save(todo);
        //todoService.addTodo(name,todo.getDescription(), todo.getTargetDate(),todo.isDone());
        return "redirect:/list-todos";
    }
    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {

        todoRepository.deleteById(id);
        return "redirect:/list-todos";
    }
    @RequestMapping(value="update-todo",method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {

        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo",todo);
        return "todo";
    }
    @RequestMapping(value="update-todo",method = RequestMethod.POST)
    public String  saveUpdateTodoPage(ModelMap modelMap , @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String name = getLoggedInUsername(modelMap);
        todo.setUserName(name);
        todoRepository.save(todo);
        return "redirect:/list-todos";
    }

    private String getLoggedInUsername(ModelMap model) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();

    }

}
