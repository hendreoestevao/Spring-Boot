package com.hendreoestevao.spring.math;

import com.hendreoestevao.spring.exception.UnsopportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public class SimpleMath {
    public Double sum(@PathVariable("a") Double a, @PathVariable("b") Double b) {
        return a + b;
    }
}
