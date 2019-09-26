package cloud.lab.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by yanming on 2019/9/25
 */
@RestController
@RefreshScope
public class HelloController {

    @Value("${user.role:none}")
    private String role;

    @GetMapping("/hello")
    public String getMineRole() {
        return String.format("your role is %s", role);
    }


}
