package com.controller;
import com.entity.upload_info;
import com.entity.user_activity;
import com.service.ActivityService;
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
import java.util.Date;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/upload_info")

public class FileController{
    @Autowired
    private FileService fileService;

    @Autowired
    private ActivityService activityService;

        @PostMapping(path = "/add") // Map ONLY POST Requests
        public @ResponseBody  ResponseEntity<?> addNewUser(
                @RequestParam(name="mypic") MultipartFile file, HttpSession session) {

            user_activity t=new user_activity();
            t.setEmail(session.getAttribute("name").toString());
            Date d=new Date();
            t.setACTIVITY_TIME(d.toString());
            t.setACTIVITY("File uploaded");
            activityService.saverec(t);

            String Path="F:\\Sem-1\\cmpe-273\\SpringBootDemoCode\\SpringBootDemoCode\\SpringBootDemo\\public";

            Path=Path+'\\'+file.getOriginalFilename();
            upload_info t1=new upload_info();
            t1.setEmail(session.getAttribute("name").toString());
            t1.setPath(file.getOriginalFilename());
            t1.setIsdirec(0);
            t1.setStarred(0);
            t1.setPermission(1);
            Date d1=new Date();
            t1.setActivity_time(d1.toString());

            fileService.savepath(t1);
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
         user_activity t=new user_activity();
         t.setEmail(session.getAttribute("name").toString());
         Date d=new Date();
         t.setACTIVITY_TIME(d.toString());
         t.setACTIVITY("File Starred");
        activityService.saverec(t);
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
        user_activity t=new user_activity();
        t.setEmail(session.getAttribute("name").toString());
        Date d=new Date();
        t.setACTIVITY_TIME(d.toString());
        t.setACTIVITY("File UnStarred");
        activityService.saverec(t);
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
        user_activity t=new user_activity();
        t.setEmail(session.getAttribute("name").toString());
        Date d=new Date();
        t.setACTIVITY_TIME(d.toString());
        t.setACTIVITY("File Deleted");
        activityService.saverec(t);
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


        user_activity t=new user_activity();
        t.setEmail(session.getAttribute("name").toString());
        Date d=new Date();
        t.setACTIVITY_TIME(d.toString());
        t.setACTIVITY("File Shared");
        activityService.saverec(t);

        concat=concat.substring(1,concat.length()-1);
        String [] concatdata=concat.split("&");
        String path= concatdata[0];
        String shareto= concatdata[1];
        upload_info t1=new upload_info();
        t1.setStarred(0);
        t1.setPath(path);
        t1.setPermission(0);
        t1.setIsdirec(0);
        t1.setEmail(shareto);
        fileService.save(t1);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }




    @PostMapping(path="/directory",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<?> directory (@RequestBody String path,HttpSession session ) {

        user_activity t=new user_activity();
        t.setEmail(session.getAttribute("name").toString());
        Date d=new Date();
        t.setACTIVITY_TIME(d.toString());
        t.setACTIVITY("Directory created");
        activityService.saverec(t);

        upload_info t1=new upload_info();
        path=path.substring(1,path.length()-1);
        t1.setStarred(0);
        t1.setPath(path);
        t1.setPermission(1);
        t1.setIsdirec(1);
        t1.setEmail(session.getAttribute("name").toString());
        fileService.save(t1);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

}

