package io.pivotal.training.controller;

import io.pivotal.training.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    private Logger logger = LoggerFactory.getLogger(PageController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createAddress")
    public ModelAndView createAddress() {
        ModelAndView modelAndView = new ModelAndView("createAddress");
        modelAndView.addObject("address", new Address());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/address")
    public ModelAndView postForm(@ModelAttribute Address address) {
        ModelAndView modelAndView = new ModelAndView("address");
        modelAndView.addObject("address", address);
        return modelAndView;
    }
}
