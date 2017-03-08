package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Witaj, %s, wszystkiego najlepszego z okazji Dnia Kobiet!!!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "") String name) {
        if (StringUtils.isEmpty(name)) {
            return new Greeting(counter.incrementAndGet(), "Brakuje parametru 'name', dodaj go do linka w takiej formie: ?name=imie");
        }
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
