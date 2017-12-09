package com.controller;

import com.entity.upload_info;
import com.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/upload_info")

public class FileController{
    @Autowired
    private FileService fileService;

        @PostMapping(path = "/add") // Map ONLY POST Requests
        public @ResponseBody  ResponseEntity<?> addNewUser(
                @RequestParam(name="mypic") MultipartFile file, HttpSession session) {
            // @ResponseBody means the returned String is the response, not a view name
            // @RequestParam means it is a parameter from the GET or POST request
            // userService.addUser(user);

            String Path="F:\\Sem-1\\cmpe-273\\SpringBootDemoCode\\SpringBootDemoCode\\front-end\\public";

            System.out.println(file.getName());
            System.out.println(Path);
            Path=Path+'\\'+file.getOriginalFilename();
            System.out.println(session.getAttribute("name").toString());
            //System.out.println(Path.getNam);
            upload_info t=new upload_info();
            t.setEmail(session.getAttribute("name").toString());
            t.setPath(Path);
            t.setIsdirec(0);
            t.setStarred(0);
            t.setPermission(1);

            fileService.savepath(t);
            return new ResponseEntity(null, HttpStatus.CREATED);
        }

    @GetMapping(path="/getimg",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<upload_info> getAllFiles(HttpSession session) {
        // This returns a JSON with the users
        return fileService.getAllFiles(session.getAttribute("name").toString());
    }
}

