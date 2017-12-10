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
import java.util.Iterator;

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

            String Path="F:\\Sem-1\\cmpe-273\\SpringBootDemoCode\\SpringBootDemoCode\\SpringBootDemo\\public";

            Path=Path+'\\'+file.getOriginalFilename();
            upload_info t=new upload_info();
            t.setEmail(session.getAttribute("name").toString());
            t.setPath(file.getOriginalFilename());
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


    @PostMapping(path="/star",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public @ResponseBody String star (@RequestBody String path,HttpSession session ) {
        Iterable<upload_info> filesfetched= fileService.starfile(session.getAttribute("name").toString());
        Iterator<upload_info> iter=filesfetched.iterator();
        while(iter.hasNext())
        {
            upload_info nextfile = iter.next();
            String currfile=nextfile.getPath();
            currfile='"'+currfile+'"';
            if(currfile.equals(path))
            {
                System.out.println("in star");
                nextfile.setStarred(1);
                fileService.save(nextfile);
            }
        }

        return "201";
    }


    @PostMapping(path="/unstar",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public @ResponseBody String unstar (@RequestBody String path,HttpSession session ) {
        Iterable<upload_info> filesfetched= fileService.starfile(session.getAttribute("name").toString());
        Iterator<upload_info> iter=filesfetched.iterator();
        while(iter.hasNext())
        {
            upload_info nextfile = iter.next();
            String currfile=nextfile.getPath();
            currfile='"'+currfile+'"';
            if(currfile.equals(path))
            {
                System.out.println("in unstar");
                nextfile.setStarred(0);
                fileService.save(nextfile);
            }
        }

        return "201";
    }

    @PostMapping(path="/delete",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public @ResponseBody String delete (@RequestBody String path,HttpSession session ) {
        Iterable<upload_info> filesfetched= fileService.starfile(session.getAttribute("name").toString());
        Iterator<upload_info> iter=filesfetched.iterator();
        while(iter.hasNext())
        {
            upload_info nextfile = iter.next();
            String currfile=nextfile.getPath();
            currfile='"'+currfile+'"';
            if(currfile.equals(path))
            {
                System.out.println("in unstar");
                fileService.remove(nextfile);
            }
        }

        return "201";
    }



    @PostMapping(path="/share",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<?> share (@RequestBody String concat,HttpSession session ) {


        concat=concat.substring(1,concat.length()-1);
        String [] concatdata=concat.split("&");
        String path= concatdata[0];
        String shareto= concatdata[1];
        upload_info t=new upload_info();
        t.setStarred(0);
        t.setPath(path);
        t.setPermission(0);
        t.setIsdirec(0);
        t.setEmail(shareto);
        fileService.save(t);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }




    @PostMapping(path="/directory",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<?> directory (@RequestBody String path,HttpSession session ) {

        upload_info t=new upload_info();
        path=path.substring(1,path.length()-1);
        t.setStarred(0);
        t.setPath(path);
        t.setPermission(1);
        t.setIsdirec(1);
        t.setEmail(session.getAttribute("name").toString());
        fileService.save(t);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

}

