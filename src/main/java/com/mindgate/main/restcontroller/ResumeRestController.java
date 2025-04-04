package com.mindgate.main.restcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;

@RestController
@RequestMapping("rest/pdf")
public class ResumeRestController {

	@PostMapping("checkSkills")
	public ResponseEntity<String> checkResumeHaveRequiredSkillMentioned(@RequestParam("file") MultipartFile file,@RequestParam List<String> SkillList){
		
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("No file uploaded");
//        }
//		
//        try(InputStream inputStream = file.getInputStream();
//                PDDocument document = PDDocument.load(inputStream)){
//        	  PDFTextStripper pdfStripper = new PDFTextStripper();
//              String extractedText = pdfStripper.getText(document).toLowerCase(); // Convert to lowercase for case-insensitive matching
//
//              List<String> matchedSkills = new ArrayList<>();
//              for (String skill : SkillList) {
//                  if (extractedText.contains(skill.toLowerCase())) {
//                      matchedSkills.add(skill);
//                  } 
//              }
//
//              if (matchedSkills.isEmpty()) {
//                  return ResponseEntity.ok("No required skills found in the resume.");
//              }
//
//              return ResponseEntity.ok("Matched Skills: " + matchedSkills);
//
//        	
//        	
//        } catch (IOException e) {
//		
//			e.printStackTrace();
//		}
		
		return null;
	}
	
}
