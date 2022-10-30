package com.nhnacademy.controller.user;

import com.nhnacademy.command.Command;
import com.nhnacademy.command.CommandUtil;
import com.nhnacademy.domain.repository.UserRepository;
import com.nhnacademy.domain.user.GeneralUser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@Slf4j
public class UserAddController implements Command {



    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //id,pwd,name,profile 로 들어온 데이터를 userRepository 받아서 setAtrrbute 하기

        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        String name = req.getParameter("name");


        try {
            GeneralUser user = new GeneralUser(id, pwd, name);
            for (Part part : req.getParts()) {
                String contentDisposition = part.getHeader(CommandUtil.CONTENT_DISPOSITION);

                if (contentDisposition.contains("filename=")) {
                    String fileName = CommandUtil.extractFileName(contentDisposition);

                    if (part.getSize() > 0) {
                        String filePath = CommandUtil.UPLOAD_DIR + File.separator + fileName;
                        part.write(filePath); //임시 저장된 파일을 내가 원하는 곳에 쓰고
                        part.delete(); //임시파일은 지워버림
                        user.setProfileFileName(fileName);

                    }
                }
            }
            userRepository.add(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


        return "/admin/userList.jsp";
    }


}
