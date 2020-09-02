package com.mayun.demo.controller;

import com.mayun.demo.dto.AccessTokenDTO;
import com.mayun.demo.dto.MaYunUser;
import com.mayun.demo.provider.MaYunProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private MaYunProvider maYunProvider;

    private String clientId="41357a8fe319f1712b6490bcc726380121cfc5723919677b6ed6cc51c75098ba";

    private String clientSecret="a13f1a172bd4a9f95639bcf297d094fa0a26dc2b09363c3adad3c3f96d553fcd";

    private String redirectUrl="http://localhost:8080/callback";


    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,Model model){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        String accessToken = maYunProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        MaYunUser maYunUser = maYunProvider.getUser(accessToken);
        System.out.println(maYunUser);
        model.addAttribute("maYunUser",maYunUser);
        return "redirect:/";
    }
}
