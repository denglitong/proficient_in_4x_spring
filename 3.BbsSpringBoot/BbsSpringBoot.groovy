import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * spring run BbsSpringBoot.groovy
 */
@RestController
class BbsSpringBoot {
    @RequestMapping("/")
    String index() {
        "欢迎光临小春论坛！"
    }
}