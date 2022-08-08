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
// 0808 수업
@Log4j2
@Controller
public class FileUploadController {
	
	@GetMapping(value="/fileupload/form")
	public String uploadForm() {
		return "upload/form";
	}
	// Multipart : 웹 요청을 받고 http의 바디부분에 여러부분으로 나눠서 보내는 것
	// 우리가 파일을 보낼때는 상관없는데 http가 쪼개서 보낸 파일을 받을때는 인코딩을 해줘야 하고 해당 디펜던시가 commons-filepload이다
	@PostMapping(value="/fileupload/")
	public String handleFileUpload( // RequestParam : getParameter이랑 같은 역할 
			@RequestParam("file") MultipartFile file){//, HttpServletRequest request) {
		
		// request.getrealpath 써서 실제 경로 확인하기
		//ServletContext app = request.getServletContext();
		//log.info("real path : " + app.getRealPath("/")); // 실제 파일 저장 위치
		
		//Path rootLocation = Paths.get("target/files/"); // 경로를 알기위한 클래스
		
		Path rootLocation = Paths.get("target/files/" + Math.abs(new Random().nextLong())); // 겹칠 확률을 더 낮추기 위해 폴더이름에도 랜덤숫자를 붙여준다
		
		log.info("rootLocatoion : " + rootLocation);
		log.info("abs rootLocaiont" + rootLocation.toAbsolutePath());
		
		// 파일이 비어있을 때 예외처리
		if(file.isEmpty()) {
			log.error("비어있는 파일을 저장할 수 없습니다.");
			return "redirect:/message";
		}
		
		// Path.resolve() : 해당 Path로부터 넘겨준 상대 경로를 적용한 인스턴스를 반환 / 앞에 경로에 내가 적고싶은 경로를 추가해준다
		// Path.normalize() : 해당 Path의 ./, ../같은 상대경로들을 최소화한 경로를 반환 / target\files\..\message\123 에서 target\message\123로
		
		//Path destinationFile = rootLocation.resolve("../message/123").normalize();
		
		// 파일 이름이 겹치지 않도록 앞에 최대한 겹치지 않는 숫자를 넣어주는 것이 좋다..
		// 이런 역할을 해주는 클래스가 UUID이다 / 랜덤숫자 넣는것보다 좋다
		UUID uuid = UUID.randomUUID(); // 고유 식별자
		Path destinationFile = rootLocation.resolve( 
				Paths.get(uuid+"_" + file.getOriginalFilename()));
		
//		Path destinationFile = rootLocation.resolve( // 파일이름이 겹칠 경우 기존 파일을 덮어씌우므로 파일 이름앞에 랜덤 숫자를 붙여준다
//				Paths.get(Math.abs(new Random().nextLong())+"_" + file.getOriginalFilename()));
		
		log.info("destinationFile : " + destinationFile);
		
		// 파일 쓰기
		try(InputStream in = file.getInputStream() ){ // multipart로 해석한 inputstream
			Files.createDirectories(destinationFile); // nio의 mkdirs (해당 경로에 필요한 모든 상위 디렉토리들을 생성한다)
			// 파일을 그대로 복사(세번째 인자는 표준 복사 옵션으로 정책을 설정하는 것)
			Files.copy(in, destinationFile, StandardCopyOption.REPLACE_EXISTING);  
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.info("file : " + file);
		log.info("filename : "+file.getOriginalFilename());
		log.info("file size : " + file.getSize() + "Byte(s)");
		
		return "redirect:/"; // homecontroller mapping이 / 이고 return 값이 홈이다 
	}
	
	
	@GetMapping("/fileupload/") // 뒤에 / 적어줘야 뒤에 경로 적을때 현재 매핑한 uri까지 읽고 안적으면 최상위 디렉에서 잘린다.
	public String listUploadedFiles(Model model) {
		// 컨트롤러에서 어트리뷰트를 실을 때 model을 활용할 수 있다. request 대신
		
		List<File> files = new ArrayList<>();
		
		File rootLocation = new File("target/files/");
		
		for(File f : rootLocation.listFiles()) {
			if(f.isDirectory()) {
				for(File f2 : f.listFiles()) { // 폴더안에 파일이아니라 폴더가 있을수도 있으므로 재귀로돌려서 파일들을 읽어서 저장한다
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
	
	@GetMapping("/fileupload/target/files/{parent}/{filename:.+}") // files안에 51번째 줄에 적은 폴더안에 있는 파일만 매핑이 된다
	@ResponseBody							// 매핑시 {}안에 적은 값을 변수로 사용한다.
	public ResponseEntity<Resource> serveFile(@PathVariable String parent, @PathVariable String filename) throws Exception{
		
		log.info("parent folder : "+ parent);
		log.info("file name : " + filename);
		// files뒤에 부모폴더 경로 추가 + 파일 이름 추가 / 현재 메서드로 매핑한 경로 양식 그대로 
		Path file = Paths.get("target/files/").resolve(parent).resolve(filename); 
		
		try {
			Resource resource = new UrlResource(file.toUri());
			// 파일을 다운받으라고 할때는 헤더의 content-disposition 항목을 attachment로 바꿔야 한다 
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"hello.jpg\"").body(resource);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//throw new Exception("파일 다운로드 중 문제가 발생하였습니다.");
			return (ResponseEntity<Resource>) ResponseEntity.notFound();
		}
	
	}

}