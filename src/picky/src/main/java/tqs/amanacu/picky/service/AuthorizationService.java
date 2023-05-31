package tqs.amanacu.picky.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tqs.amanacu.picky.models.User;
import tqs.amanacu.picky.repositories.UserRepository;
import tqs.amanacu.picky.repositories.RoleRepository;
import tqs.amanacu.picky.security.UserDetailsImpl;
import tqs.amanacu.picky.models.ERole;
import tqs.amanacu.picky.models.Role;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    public UserDetailsImpl login(String username, String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return userDetails;
    }

    public boolean register(String username, String email, String password, Set<String> strRoles) {
        if (userRepository.existsByUsername(username)) {
            return false;
        }

        if (userRepository.existsByEmail(email)) {
            return false;
        }

        User user = new User(username, email, encoder.encode(password));

        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "estore":
                    Role eStoreRole= roleRepository.findByName(ERole.ROLE_ESTORE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(eStoreRole);
                    break;
                case "acp":
                    Role acpRole = roleRepository.findByName(ERole.ROLE_ACP)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(acpRole);
                    break;
                case "admin":
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                    break;
                default:
                    throw new RuntimeException("Error: Role is not found.");
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return true;
    }
}
