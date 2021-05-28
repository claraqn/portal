package kr.ac.jejunu;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;

@Controller
//@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @RequestMapping(path="/user")
//    public User getUser(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response
//            , HttpSession session){
//        User user = (User) session.getAttribute("user");
//        if(user == null)
//            user=userDao.findById(id);
//        session.setAttribute("user", User.builder().name("Session").build());
//        return user;
//    }
//    public ModelAndView getUser(@PathVariable("id") Integer id, @CookieValue("JSESSIONID") String sessionId){
//        ModelAndView modelAndView = new ModelAndView("user");
//        User user = userDao.findById(id);
//        user.setName(sessionId);
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }
//    public void getUser(@RequestParam("id") Integer id, Map map){
//        map.put("user", userDao.findById(id));
//    }
//    public void getUser(@RequestParam("id") Integer id, Model model){
//        model.addAttribute("user", userDao.findById(id));
//    }
//    public String getUser(@ModelAttribute User user){
//        Integer id = user.getId();
//        User user2 = userDao.findById(id);
//        user.setName(user2.getName());
//        return "redirect:/upload";
//    }
//    public @ModelAttribute User getUser(@RequestParam("id") Integer id){
//        return userDao.findById(id);
//    }
//    public View getUser(@RequestParam("id") Integer id){
//        return new RedirectView(("/upload"));
//    }
    public User getUser(@RequestParam("id") Integer id){
        return userDao.findById(id);
    }

    @RequestMapping("/exception")
    public void exception(){
        throw new RuntimeException("어이쿠");
    }

//    @RequestMapping(path = "/upload", method = RequestMethod.GET)
    @GetMapping("/upload")
    public void upload(){

    }

//    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        File path = new File(request.getServletContext().getRealPath("/")+"/WEB-INF/static/"+file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.close();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", "/images/"+file.getOriginalFilename());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("e", e);
        return modelAndView;
    }
}
