package com.cis.finalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cis.finalProject.superPeer1.*;
import com.cis.finalProject.superPeer2.*;

@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner{

	@Autowired
    private superPeer1Server bcs;
	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
		
		
	}
	@Override
    public void run() throws Exception {
        bcs.Service();
    }
	

}
