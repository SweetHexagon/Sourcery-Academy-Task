package sourceryacademy.library.services.Interfaces;

import sourceryacademy.library.data.User;

public interface UserInterface {
    User findUserByUsername(String username);
}
