package HotelManagement.roles;

import HotelManagement.employee.Permissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/roles")
public class RoleController {
        private final RoleService roleService;

        @Autowired
        public RoleController(RoleService roleService) {
            this.roleService = roleService;
        }

        @PostMapping("/create")
        @PreAuthorize("hasAuthority('ADMIN_CREATE')")
        public Role createRole(@RequestParam String roleName, @RequestParam Set<Permissions> permissions, @RequestParam String postedBy) {
            return roleService.createNewRole(roleName, permissions, postedBy);
        }

        @PutMapping("/update/{roleId}")
        @PreAuthorize("hasAuthority('ADMIN_UPDATE')")
        public Role updateRolePermissions(@PathVariable Long roleId, @RequestParam Set<Permissions> permissions) {
            Role role = new Role();
            role.setId(role.getId());
            return roleService.updateRolePermissions(roleId, permissions);
        }
        @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ADMIN_READ')")
     public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
    }


