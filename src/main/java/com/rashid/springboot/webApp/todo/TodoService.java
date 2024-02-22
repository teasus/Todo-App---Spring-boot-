package com.rashid.springboot.webApp.todo;



import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "ahmed","Get AWS Certified 1",
                LocalDate.now().plusYears(1), false ));
        todos.add(new Todo(++todosCount, "ahmed","Learn DevOps 1",
                LocalDate.now().plusYears(2), false ));
        todos.add(new Todo(++todosCount, "ahmed","Learn Full Stack Development 1",
                LocalDate.now().plusYears(3), false ));
    }


    public List<Todo> getByUsername(String username) {

        System.out.println("username and size " + todos.size());
        Predicate<? super Todo> predicate = todo -> todo.getUserName().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo (String username , String description , LocalDate targetDate,boolean done){
        Todo todo = new Todo(++todosCount,username,description,targetDate,done);
        todos.add(todo);
    }
    public void deleteById(int id) {
        //todo.getId() == id
        // todo -> todo.getId() == id
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findByID(int id){
        Predicate <? super Todo> predicate = todo ->todo.getId()==id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }
    public void update(@Valid Todo todo){
        deleteById(todo.getId());
        todos.add(todo);



    }



}
