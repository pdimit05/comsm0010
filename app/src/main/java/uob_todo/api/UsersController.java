package uob_todo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uob_todo.api.exceptions.BadRequestException;
import uob_todo.api.exceptions.NotFoundException;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {

    private final UsersRepository usersSource;

    @Value("${todoapp.penalty.factor}")
    private int penaltyFactor;

    /*
    This function runs a cpu/memory penalty in order to generate some fake load. This is used to drive metrics
    examples and demonstrate auto-scaling of pods/containers.
     */
    private void runPenaltyIfRequired() {
        if (penaltyFactor > 0) {
            new SCryptPasswordEncoder(1 << penaltyFactor, 8, 1, 32, 64).encode("");
        }
    }

    @Autowired
    public UsersController(UsersRepository usersSource){
        this.usersSource = usersSource;
    }

    @GetMapping("/{id}")
    public Users getUsers(@PathVariable("id") Long id) throws Exception {
        this.runPenaltyIfRequired();
        return usersSource.findById(id).orElseThrow(() -> new NotFoundException("item not found"));
    }

    @GetMapping()
    public Iterable<Users> listUsers() {
        this.runPenaltyIfRequired();
        return usersSource.findAll();
    }

    @PostMapping()
    public Users createUser(@RequestBody Users user) throws Exception {
        this.runPenaltyIfRequired();
        if (user.getUsername().equals("")) {
            throw new BadRequestException("empty 'username'");
        }
        return usersSource.save(user);
    }

    // @PutMapping("/{id}")
    // public TodoItem updateTodo(@PathVariable("id") Long id, @RequestBody TodoItem item) throws Exception {
    //     this.runPenaltyIfRequired();
    //     if (item.getTitle().equals("")) {
    //         throw new BadRequestException("empty 'title'");
    //     }
    //     TodoItem existingItem = todoSource.findById(id).orElseThrow(() -> new NotFoundException("item not found"));
    //     existingItem.setCompleted(item.isCompleted());
    //     existingItem.setTitle(item.getTitle());
    //     existingItem = todoSource.save(existingItem);
    //     return existingItem;
    // }

    // @DeleteMapping("/{id}")
    // @ResponseStatus(value = HttpStatus.NO_CONTENT)
    // public void deleteTodo(@PathVariable("id") Long id) throws Exception {
    //     this.runPenaltyIfRequired();
    //     todoSource.findById(id).orElseThrow(() -> new NotFoundException("item not found"));
    //     todoSource.deleteById(id);
    // }

}
