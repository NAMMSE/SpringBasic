package com.ezen.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
// 0808 ����
@Log4j2
@Controller
public class FileUploadController {
	
	@GetMapping(value="/fileupload/form")
	public String uploadForm() {
		return "upload/form";
	}
	// Multipart : �� ��û�� �ް� http�� �ٵ�κп� �����κ����� ������ ������ ��
	// �츮�� ������ �������� ������µ� http�� �ɰ��� ���� ������ �������� ���ڵ��� ����� �ϰ� �ش� ������ð� commons-filepload�̴�
	@PostMapping(value="/fileupload/")
	public String handleFileUpload( // RequestParam : getParameter�̶� ���� ���� 
			@RequestParam("file") MultipartFile file){//, HttpServletRequest request) {
		
		// request.getrealpath �Ἥ ���� ��� Ȯ���ϱ�
		//ServletContext app = request.getServletContext();
		//log.info("real path : " + app.getRealPath("/")); // ���� ���� ���� ��ġ
		
		//Path rootLocation = Paths.get("target/files/"); // ��θ� �˱����� Ŭ����
		
		Path rootLocation = Paths.get("target/files/" + Math.abs(new Random().nextLong())); // ��ĥ Ȯ���� �� ���߱� ���� �����̸����� �������ڸ� �ٿ��ش�
		
		log.info("rootLocatoion : " + rootLocation);
		log.info("abs rootLocaiont" + rootLocation.toAbsolutePath());
		
		// ������ ������� �� ����ó��
		if(file.isEmpty()) {
			log.error("����ִ� ������ ������ �� �����ϴ�.");
			return "redirect:/message";
		}
		
		// Path.resolve() : �ش� Path�κ��� �Ѱ��� ��� ��θ� ������ �ν��Ͻ��� ��ȯ / �տ� ��ο� ���� ������� ��θ� �߰����ش�
		// Path.normalize() : �ش� Path�� ./, ../���� ����ε��� �ּ�ȭ�� ��θ� ��ȯ / target\files\..\message\123 ���� target\message\123��
		
		//Path destinationFile = rootLocation.resolve("../message/123").normalize();
		
		// ���� �̸��� ��ġ�� �ʵ��� �տ� �ִ��� ��ġ�� �ʴ� ���ڸ� �־��ִ� ���� ����..
		// �̷� ������ ���ִ� Ŭ������ UUID�̴� / �������� �ִ°ͺ��� ����
		UUID uuid = UUID.randomUUID(); // ���� �ĺ���
		Path destinationFile = rootLocation.resolve( 
				Paths.get(uuid+"_" + file.getOriginalFilename()));
		
//		Path destinationFile = rootLocation.resolve( // �����̸��� ��ĥ ��� ���� ������ �����Ƿ� ���� �̸��տ� ���� ���ڸ� �ٿ��ش�
//				Paths.get(Math.abs(new Random().nextLong())+"_" + file.getOriginalFilename()));
		
		log.info("destinationFile : " + destinationFile);
		
		// ���� ����
		try(InputStream in = file.getInputStream() ){ // multipart�� �ؼ��� inputstream
			Files.createDirectories(destinationFile); // nio�� mkdirs (�ش� ��ο� �ʿ��� ��� ���� ���丮���� �����Ѵ�)
			// ������ �״�� ����(����° ���ڴ� ǥ�� ���� �ɼ����� ��å�� �����ϴ� ��)
			Files.copy(in, destinationFile, StandardCopyOption.REPLACE_EXISTING);  
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.info("file : " + file);
		log.info("filename : "+file.getOriginalFilename());
		log.info("file size : " + file.getSize() + "Byte(s)");
		
		return "redirect:/"; // homecontroller mapping�� / �̰� return ���� Ȩ�̴� 
	}
	
	
	@GetMapping("/fileupload/") // �ڿ� / ������� �ڿ� ��� ������ ���� ������ uri���� �а� �������� �ֻ��� �𷺿��� �߸���.
	public String listUploadedFiles(Model model) {
		// ��Ʈ�ѷ����� ��Ʈ����Ʈ�� ���� �� model�� Ȱ���� �� �ִ�. request ���
		
		List<File> files = new ArrayList<>();
		
		File rootLocation = new File("target/files/");
		
		for(File f : rootLocation.listFiles()) {
			if(f.isDirectory()) {
				for(File f2 : f.listFiles()) { // �����ȿ� �����̾ƴ϶� ������ �������� �����Ƿ� ��ͷε����� ���ϵ��� �о �����Ѵ�
					files.add(f2);
				}
			}else {
				files.add(f);
			}
			log.info("file : " + f.getPath());
		}
		
		model.addAttribute("files", files);
		
		return "upload/list";
	}
	
	@GetMapping("/fileupload/target/files/{parent}/{filename:.+}") // files�ȿ� 51��° �ٿ� ���� �����ȿ� �ִ� ���ϸ� ������ �ȴ�
	@ResponseBody							// ���ν� {}�ȿ� ���� ���� ������ ����Ѵ�.
	public ResponseEntity<Resource> serveFile(@PathVariable String parent, @PathVariable String filename) throws Exception{
		
		log.info("parent folder : "+ parent);
		log.info("file name : " + filename);
		// files�ڿ� �θ����� ��� �߰� + ���� �̸� �߰� / ���� �޼���� ������ ��� ��� �״�� 
		Path file = Paths.get("target/files/").resolve(parent).resolve(filename); 
		
		try {
			Resource resource = new UrlResource(file.toUri());
			// ������ �ٿ������� �Ҷ��� ����� content-disposition �׸��� attachment�� �ٲ�� �Ѵ� 
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"hello.jpg\"").body(resource);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//throw new Exception("���� �ٿ�ε� �� ������ �߻��Ͽ����ϴ�.");
			return (ResponseEntity<Resource>) ResponseEntity.notFound();
		}
	
	}

}