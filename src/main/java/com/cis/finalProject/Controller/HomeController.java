package com.cis.finalProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	@GetMapping("/")
	public String Home() {
		return "Home";
	}

    @GetMapping("/superPeerlogin")
    public String superPeerlogin() {
        return "superPeer/superPeerlogin";
    }
    @GetMapping("/superPeer/dashboard")
    public String dashboard() {
    
    	return "superPeer/dashboard";
    }
    
    @GetMapping("/regularPeer/dashboard")
    public String dashboard1() {
    
    	return "regularPeer/rpdashboard";
    }
    @GetMapping("/regularPeerLogin")
    public String regularPeerlogin() {
        return "regularPeer/regularPeerLogin";
    }
    
    @GetMapping("/searchSuccess")
    public String searchSuccess() {
        return "superPeer/searchSuccess";
    }
}
