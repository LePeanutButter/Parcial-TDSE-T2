package co.escuelaing.controller;

import co.escuelaing.service.PellSequence;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    private final PellSequence pell = new PellSequence();

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }
    @GetMapping("/pell")
    public String pellseq(@RequestParam(value = "n") int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder value = new StringBuilder("0");

        if (n > 1) {
            for (int i = 1; i < n + 1; i++) {
                value.append(", ").append(String.valueOf(pell.pellseq(i)));
            }
        }
        return "operation: Secuencia de Pell\ninput:" + n + "\noutput:"+ value;
    }
}
