package com.hendreoestevao.spring.controllers;

import com.hendreoestevao.spring.exception.UnsopportedMathOperationException;
import com.hendreoestevao.spring.math.SimpleMath;
import com.hendreoestevao.spring.request.converters.NumberConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    private SimpleMath math = new SimpleMath();

    @RequestMapping("/sum/{a}/{b}")
    public Double sum(@PathVariable("a") String a, @PathVariable("b") String b) throws Exception {
        if(!NumberConverter.isNumerico(a) || NumberConverter.isNumerico(b)) throw  new UnsopportedMathOperationException("Please enter a numeric value");
        return math.sum(NumberConverter.convertToDouble(a),NumberConverter.convertToDouble(b));
    }

}
