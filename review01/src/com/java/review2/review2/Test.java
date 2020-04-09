package com.java.review2.review2;//package com.java.review2;
//
///**
// * @author JUNSHI 405773808@qq.com
// * @description:
// * @create 2020-03-26 20:24
// */
//public class Test {
//    //跳转到登录页面
//    @RequestMapping(value="/login", method = RequestMethod.GET)
//    public String userLogin(){
//
//        return "/adminUser/login";
//    }
//
//    //实现登录功能
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String adminLogin(String id, String password,Model model, HttpServletRequest request){
//        //在service实现的登录功能校验
//        Teacher teacher = teacherService.login(id, password);
//        if (teacher != null){
//            HttpSession session = request.getSession();
//            session.setAttribute("teacherInfo", teacher);
//            return "redirect:/index";
//        }
//        model.addAttribute("message", "用户名或密码错误!");
//        return "/adminUser/login";
//    }
//
//    //跳转到修改密码页面
//    @RequestMapping(value="/updatePassword", method=RequestMethod.GET)
//    public String userUpdatePasswordView(){
//
//        return "/adminUser/updatePassword";
//    }
//
//    //实现密码修改功能
//    @RequestMapping(value="/updatePassword", method=RequestMethod.POST)
//    @ResponseBody
//    public AjaxResult userUpdatePassword(String id, String password, String newpassword, String renewpassword){
//
//        if (newpassword != null){
//            if (newpassword.equals(renewpassword)){
//                //在service实现了修改密码功能
//                boolean flag = teacherService.updatePassword(id, password, newpassword);
//                if (flag){
//                    return AjaxResult.successInstance("修改密码成功");
//                }else{
//                    return AjaxResult.errorInstance("原密码错误");
//                }
//            }else{
//                return AjaxResult.errorInstance("您两次输入的密码不一致");
//            }
//        }
//
//        return AjaxResult.errorInstance("您两次输入的密码不一致");
//    }
//
//    //退出系统功能代码
//    @RequestMapping("/logout")
//    public String userLogout(HttpServletRequest request){
//
//        HttpSession session = request.getSession();
//        session.setAttribute("teacherInfo", null);
//        return "redirect:/adminUser/login";
//    }
//    //实现登录拦截器
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        //在handle执行之前处理
//        //判断用户是否登录
//        //1.获取Session
//        HttpSession session = request.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("teacherInfo");
//
//        if (teacher == null){
//            response.sendRedirect("/adminUser/login");
//            return false;
//        }
//
//        return true;
//    }
//
//}
