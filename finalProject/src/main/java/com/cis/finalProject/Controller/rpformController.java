package com.cis.finalProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.cis.finalProject.regularPeer.*;

@Controller
public class rpformController {
	
	@Autowired
	private rpClientService blockchain;

	
	 
	 @PostMapping("/addReviews_rp")
	 public void addReviews_rp(@RequestParam("dropdown1") String dropdown1,@RequestParam("dropdown2") String dropdown2,@RequestParam("textarea") String text, Model model) {
		 model.addAttribute("dropdown1",dropdown1);
	        model.addAttribute("dropdown2",dropdown2);
	        
	        String modelID = dropdown1.substring(0, 4)+ dropdown2;
	         
	        blockchain.updateReviewFile(modelID,text);
	      	        
	    }
	 @PostMapping("/extractMLfile_rp")
	 public ResponseEntity<Object> handleForm3(@RequestParam("dropdown3") String dropdown3,@RequestParam("dropdown4") String dropdown4, Model model) {
	        // Handle form 1 submission
		 model.addAttribute("dropdown3",dropdown3);
	        model.addAttribute("dropdown4",dropdown4);
	        
	        String modelID = dropdown3.substring(0, 4)+ dropdown4;
	        
	        byte[] fileData = null;
	        fileData = blockchain.ExtractMLfile(modelID);
	        
	        if (fileData != null) {
	            ByteArrayResource resource = new ByteArrayResource(fileData);

	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + modelID)
	                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                    .contentLength(fileData.length)
	                    .body(resource);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	        
	    }
	 
	 @PostMapping("/extractReviews_rp")
	 public ResponseEntity<Object> handleForm4(@RequestParam("dropdown1") String modelname,@RequestParam("dropdown2") String modelver, Model model) {
	        // Handle form 1 submission
	        model.addAttribute("modelname", modelname);
	        model.addAttribute("modelname", modelver);
	        
	        String modelID = modelname.substring(0, 4)+modelver;
	        
	        
	        byte[] fileData = null;
	        fileData = blockchain.ExtractReviewsfile(modelID);
	        
	        if (fileData != null) {
	            ByteArrayResource resource = new ByteArrayResource(fileData);

	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + modelID)
	                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                    .contentLength(fileData.length)
	                    .body(resource);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	        
	    }
	 
	 @PostMapping("/rpSearch")
	 public void handleForm5(@RequestParam("dropdown1") String modelname,@RequestParam("dropdown2") String modelver, Model model) {
	        // Handle form 1 submission
	        model.addAttribute("modelname", modelname);
	        model.addAttribute("modelname", modelver);
	        
	        String modelID = modelname.substring(0, 4)+modelver;
	        
	        blockchain.Search(modelID);
	        //return "/regularPeer/dashboard";
	    }
	 
}

