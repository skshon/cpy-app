package lsis.pal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-entries")
public class UserEntryController {
    private JdbcUserEntryRepository userEntriesRepo;
    private UserEntryBean userEntryBean;

    public UserEntryController(JdbcUserEntryRepository userEntriesRepo, UserEntryBean userEntryBean) {
        this.userEntriesRepo = userEntriesRepo;
        this.userEntryBean = userEntryBean;
    }

    @PostMapping
    public ResponseEntity<UserEntry> create(@RequestBody UserEntry userEntry) {
        //UserEntry createdUserEntry = userEntriesRepo.create(userEntry);
        userEntryBean.addUserEntry(userEntry);

        return new ResponseEntity<>(userEntry, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserEntry> read(@PathVariable Long id) {
        //UserEntry userEntry = userEntriesRepo.find(id);
        UserEntry userEntry = userEntryBean.find(id);
        if (userEntry != null) {
            return new ResponseEntity<>(userEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserEntry>> list() {

        //return new ResponseEntity<>(userEntriesRepo.list(), HttpStatus.OK);
        return new ResponseEntity<>(userEntryBean.getUserEntries(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserEntry> update(@PathVariable Long id, @RequestBody UserEntry userEntry) {
        //UserEntry updatedTimeEntry = userEntriesRepo.update(id, userEntry);
        userEntryBean.updateUserEntry(userEntry);
        UserEntry updatedTimeEntry = userEntryBean.find(id);

        if (updatedTimeEntry != null) {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        //userEntriesRepo.delete(id);
        UserEntry deleteEntry = userEntryBean.find(id);
        userEntryBean.deleteUserEntry(deleteEntry);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
