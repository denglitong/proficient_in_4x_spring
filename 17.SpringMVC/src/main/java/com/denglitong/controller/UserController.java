package com.denglitong.controller;

import com.denglitong.domain.User;
import com.denglitong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/27
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public String register() {
        // 返回的字符串代表逻辑视图名
        return "user/register";
    }

    // @RequestBody 常用来处理 Content-type 不是 application/x-www-forum-urlencoded 编码的内容
    // 比如 application/json, multipart/form-data
    @RequestMapping(value = "/create")
    // 方法返回 ModelAndView
    public ModelAndView createUser(@RequestBody User user) {
        userService.createUser(user);
        ModelAndView mav = new ModelAndView("user/createSuccess");
        mav.addObject("user", user);
        return mav;
    }

    // @PathVariable
    // @RequestParam 常用来处理简单类型的绑定，或者用来处理 Context-Type:application/x-www-form-urlencoded 编码的内容
    @RequestMapping("/detail/{userId}")
    public ModelAndView showDetail(@PathVariable("userId") String userId,
                                   @RequestParam(value = "userName", required = false) String userName,
                                   @RequestHeader("Accept-Encoding") String encoding,
                                   @CookieValue("JSESSIONID") String cookie) {
        ModelAndView mav = new ModelAndView("user/showUser");
        mav.addObject("user", userService.getUserById(userId));
        return mav;
    }

    // 直接将 http 请求对象传递给处理方法
    // （使用 Servlet API 对象作为入参，类似的还有 HttpSession, HttpServletResponse）
    @RequestMapping("/httpTest")
    public String httpTest(HttpServletRequest request) {
        return "httpTest";
    }

    @RequestMapping("webRequest1")
    public String webRequest1(WebRequest request) {
        return "webRequest";
    }

    @RequestMapping("webRequest2")
    public String webRequest2(NativeWebRequest request) {
        return "webRequest";
    }

    @RequestMapping("outputStream")
    public void outputStream(OutputStream os) throws IOException {
        Resource res = new ClassPathResource("/image.png");
        // 将图片写到输出流中
        FileCopyUtils.copy(res.getInputStream(), os);
    }

    // GET /books/22;a=11;b=22
    @RequestMapping("/books/{bookId}")
    public void matrixVar1(@PathVariable String bookId, @MatrixVariable int a) {
        // a = 11
    }

    // GET /books/42;a=11/authors/21;q=22
    @RequestMapping("/books/{bookId}/authors/{authorId}")
    public void matrixVar2(@PathVariable String bookId,
                           @PathVariable String authorId,
                           @MatrixVariable(value = "a", required = false) int q1,
                           @MatrixVariable(value = "b", required = false) int q2) {
        // q1 = 11, q2 = 22
    }

    // 将请求报文体转换为字符串绑定到 requestBody 入参中
    @RequestMapping("/httpMessageConverter1")
    public String httpMessageConverter1(@RequestBody String requestBody) {
        System.out.println(requestBody);
        return "success";
    }

    // 读取一张图片，并将图片数据输出到响应流中，客户端将显示这张图片
    @ResponseBody
    @RequestMapping("/httpMessageConverter2/{imageId}")
    public byte[] httpMessageConverter2(@PathVariable("imageId") String imageId,
                                        @RequestParam("type") String fileType) throws IOException {
        System.out.println("load image of " + imageId + "." + fileType);
        Resource res = new ClassPathResource(imageId + "." + fileType);
        return FileCopyUtils.copyToByteArray(res.getInputStream());
    }

    @RequestMapping("/httpEntity1")
    public String httpEntity1(HttpEntity<String> httpEntity) {
        long contentLength = httpEntity.getHeaders().getContentLength();
        System.out.println(httpEntity.getBody());
        return "success";
    }

    @RequestMapping("/httpEntity2/{imageId}")
    public ResponseEntity<byte[]> httpEntity2(@PathVariable("imageId") String imageId) throws IOException {
        Resource res = new ClassPathResource("/image.png");
        byte[] fileData = FileCopyUtils.copyToByteArray(res.getInputStream());
        // 在方法中返回 HttpEntity<byte[]>对象，ByteArrayHttpMessageConverter 负责将其输出到响应流中
        return new ResponseEntity<>(fileData, HttpStatus.OK);
    }

    @RequestMapping("/xmlJson")
    public ResponseEntity<User> xmlJson(HttpEntity<User> requestEntity) {
        User user = requestEntity.getBody();
        user.setUserId("1000");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // 通过 @ModelAttribute 注解可将入参添加到模型数据中
    @RequestMapping("/modelView1")
    public String modelView1(@ModelAttribute("user") User user) {
        user.setUserId("1001");
        return "/user/createSuccess";
    }

    // 在 UserController 的任何一个请求处理方法前，
    // Spring MVC 都会先执行 getUser() 方法将其返回值添加到模型数据中
    @ModelAttribute("user")
    public User getUser() {
        User user = new User();
        user.setUserId("1001");
        return user;
    }

    // Spring MVC 在调用请求方法前会创建一个隐含的模型对象，
    // 当请求处理方法的入参为 Map 或 Model 类型时，隐含的模型对象会传递给这些入参，
    // 这样在这些入参里就可以访问到模型中的所有数据
    @RequestMapping("/modelMap1")
    public String modelMap1(ModelMap modelMap) {
        modelMap.addAttribute("testAttr", "value1");
        // 访问模型中的数据
        User user = (User)modelMap.get("user");
        user.setUserName("tom");
        return "/user/createSuccess";
    }

    @RequestMapping("/stringConverter")
    public String stringConverter(@RequestParam("user") User user, ModelMap modelMap) {
        modelMap.put("user", user);
        return "/user/createSuccess";
    }

    @RequestMapping(value = "/formatter")
    public String formatter(User user) {
        return "/user/createSuccess";
    }

    @RequestMapping("/validator")
    public String validator(@Valid @ModelAttribute("user") User user,
                            BindingResult bindingResult) {
        // @Valid 校验的结果放在入参后面的 BindingResult 类型变量中
        // 校验结果会自动被放入"隐含的数据模型中"
        return "/user/createSuccess";
    }

    @RequestMapping(value = "/register3")
    public String register3() {
        return "/user/register3";
    }

    @RequestMapping(value = "/register4")
    public String register4() {
        return "/user/register4";
    }

    @RequestMapping(value = "/handle91")
    public String handle91(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult, ModelMap mm) {
        System.out.println(user);
        System.out.println(bindingResult.hasErrors());
        System.out.println(mm);
        if (bindingResult.hasErrors()) {
            return "/user/register3";
        } else {
            return "/user/showUser";
        }
    }

    @RequestMapping("/showUserListByFtl")
    public String showUserListInFtl(ModelMap modelMap) throws ParseException {
        modelMap.addAttribute("userList", getUserList());
        return "userListFtl";
    }

    @RequestMapping("/showUserListJson")
    public String showUserListInJson(ModelMap modelMap) throws ParseException {
        modelMap.addAttribute("userList", getUserList());
        return "userListJson";
    }

    private List<User> getUserList() throws ParseException {
        List<User> userList = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        User user1 = new User();
        user1.setUserName("user1");
        user1.setBirthDay(dateFormat.parse("1980-01-01"));
        userList.add(user1);

        User user2 = new User();
        user2.setUserName("user2");
        user2.setBirthDay(dateFormat.parse("1980-12-01"));
        userList.add(user2);

        return userList;
    }

    @RequestMapping("/uploadPage")
    public String uploadPage() {
        return "/uploadPage";
    }

    @RequestMapping("/upload")
    public String uploadThumb(@RequestParam("name") String name,
                              @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            file.transferTo(new File("/Users/leon/tmp/" +
                    (StringUtils.isEmpty(name) ? file.getOriginalFilename() : name)));
            return "redirect:/success";
        } else {
            return "redirect:/fail";
        }
    }

    @RequestMapping("/requestContextHolder")
    public void requestContextHolder() {
        // 使用 RequestContextHolder 获取 HttpServletRequest
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();

        HttpServletResponse response = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getResponse();
    }

    @RequestMapping("/interceptor/show")
    public void interceptor() {
        System.out.println("process...");
    }
}
