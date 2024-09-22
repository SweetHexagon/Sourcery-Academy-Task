package sourceryacademy.library.services.realizations;

import sourceryacademy.library.data.User;
import sourceryacademy.library.exceptions.UserNotFoundException;
import sourceryacademy.library.repositories.UserRepository;
import sourceryacademy.library.services.Interfaces.UserInterface;

import java.util.Optional;

public class UserService implements UserInterface {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()){
            return user.get();
        }else {
            throw new UserNotFoundException("user with such name is not found" + username);
        }
    }
}
