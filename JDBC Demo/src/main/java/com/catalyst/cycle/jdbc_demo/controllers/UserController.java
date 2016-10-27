package com.catalyst.cycle.jdbc_demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * In this example, our UserController simply delivers pages without interacting with the Business Logic Layer.
 * Our pages contain AJAX calls that interact with our Web Services. This helps reduce the performance
 * load on the server because we let their computers process the data and form the final view. 
 * The downside is that the client computer may experience momentary lag while the AJAX requests are 
 * retrieving data and building the page. One thing you will notice is that the UpdateUser page has a
 * brief moment where the dropdown box at the top is empty. The user must was for AJAX to get the contents
 * and display it before they will see anything. As long as there are not any major performance issues with the 
 * server, the delay will be mostly unnoticable.
 * @author egover
 *
 */
@Controller
public class UserController {

    
    @RequestMapping(value="/AddUser", method=RequestMethod.GET)
    public final ModelAndView addUserGet(){
        return new ModelAndView("AddUser");
    }
    
    
 
    
    @RequestMapping(value="/UpdateUser", method = RequestMethod.GET)
    public final ModelAndView updateUserGet(){
        return new ModelAndView("UpdateUser");
    }
}
