package filesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileSystemHanlder {
    private List<User> users = new ArrayList<>();
    private final String FILE_PATH = "src\\java\\filesystem\\users.txt";
    
    public FileSystemHanlder() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String id = parts[0].trim();
                    String username = parts[1].trim();
                    String password = parts[2].trim();
                    users.add(new User(Integer.valueOf(id), username, password));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public List<User> getUsers() {
        return users;
    }
    
    public boolean isValudUserById(Integer id, String password) {
        for (User user : users) {
            if (Objects.equals(user.getId(), id) && user.getPassword().equals(password))
                return true;
        }
        
        return false;
    }
    
    public boolean isValidUserByUsername(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return true;
        }
        
        return false;
    }
    
    public int getTotalUserCount() {
        return users.size();
    }
}
