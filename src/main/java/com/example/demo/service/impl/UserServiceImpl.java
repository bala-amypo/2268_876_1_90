@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    public User registerUser(User user) {
        if (repo.findByEmail(user.getEmail()).isPresent())
            throw new RuntimeException("email exists");

        if (user.getPassword().length() < 8)
            throw new RuntimeException("password");

        return repo.save(user);
    }

    public User getUser(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
