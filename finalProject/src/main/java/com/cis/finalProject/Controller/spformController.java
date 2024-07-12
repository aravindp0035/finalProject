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
import org.springframework.web.multipart.MultipartFile;

import com.cis.finalProject.superPeer1.*;

@Controller
public class spformController {
	
	@Autowired
	private ClientService cs;
	
	@PostMapping("/superPeer/dashboard")
	public void blockchainSize(Model model) {
		int size = cs.bcSize();
		model.addAttribute("size", size);
		
	}
	
	

	 @PostMapping("/createBlock")
	    public void createBlock(@RequestParam("dropdown") String dropdown,@RequestParam("modelver") String modelver, @RequestParam("mlfileUpload") MultipartFile mlfile, @RequestParam("reviewfileUpload") MultipartFile reviewfile, Model model) {
	        // Handle form 1 submission
	        model.addAttribute("modelname", dropdown);
	        model.addAttribute("modelver", modelver);
	        model.addAttribute("mlfileName", mlfile.getOriginalFilename());
	        model.addAttribute("reviewfileName", reviewfile.getOriginalFilename());
	        model.addAttribute("success", true);
	        
	        String modelID = dropdown.substring(0, 4)+modelver;
	       
	        cs.createNewBlock(modelID, mlfile, reviewfile);
	        
	        
//	        return "dashboard";
	        
	    }
	 
	 @PostMapping("/addReviews")
	 public void handleForm2(@RequestParam("dropdown1") String modelname,@RequestParam("dropdown2") String modelver, @RequestParam("textarea") String text, Model model) {
	        // Handle form 1 submission
	        model.addAttribute("modelname", modelname);
	        model.addAttribute("modelname", modelver);
	        
	        String modelID = modelname.substring(0, 4)+modelver;
	         
	        cs.updateReviewFile(modelID, text);
	        
	       
	        
	    }
	 @PostMapping("/extractMLfile")
	 public ResponseEntity<Object> extractMLfile(@RequestParam("dropdown3") String dropdown3,@RequestParam("dropdown4") String dropdown4, Model model) {
	        // Handle form 1 submission
	        model.addAttribute("dropdown3",dropdown3);
	        model.addAttribute("dropdown4",dropdown4);
	        
	        String modelID = dropdown3.substring(0, 4)+ dropdown4;
	        System.out.println(modelID);
	        byte[] fileData = null;
	        fileData = cs.ExtractMLfile(modelID);
	        System.out.println(fileData==null);
	        
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
	 
	 @PostMapping("/extractReviews")
	 public ResponseEntity<Object> handleForm4(@RequestParam("dropdown1") String modelname,@RequestParam("dropdown2") String modelver, Model model) {
	        // Handle form 1 submission
	        model.addAttribute("modelname", modelname);
	        model.addAttribute("modelname", modelver);
	        
	        String modelID = modelname.substring(0, 4)+modelver;
	        
	        
	        byte[] fileData = null;
	        fileData = cs.ExtractReviewsfile(modelID);
	        
	        if (fileData != null) {
	            ByteArrayResource resource = new ByteArrayResource(fileData);

	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + modelID+".txt")
	                    .contentType(MediaType.TEXT_PLAIN)
	                    .contentLength(fileData.length)
	                    .body(resource);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	        
	    }
	 
	 @PostMapping("/Search")
	 public String handleForm5(@RequestParam("dropdown1") String modelname,@RequestParam("dropdown2") String modelver, Model model) {
	        // Handle form 1 submission
	        model.addAttribute("modelname", modelname);
	        model.addAttribute("modelname", modelver);
	        
	        String modelID = modelname.substring(0, 4)+modelver;
	        
	        cs.Search(modelID);
	        return "/superPeer/dashboard";
	    }
	 
}
