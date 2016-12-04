package skyglass.demo.controller.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import skyglass.demo.data.model.security.Token;
import skyglass.demo.data.security.TokenData;

@RestController
@RequestMapping("/rest/security")
public class SecurityController {

    @Autowired
    private TokenData tokenRepo;   

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/security/tokens", method = RequestMethod.GET)
    public @ResponseBody
    List<Token> getTokens () {
        List<Token> tokens = tokenRepo.findAll();
        for(Token t:tokens) {
            t.setSeries(null);
            t.setValue(null);
        }
        return tokens;
    }
    




}
