package com.nhnacademy.controller;

import com.nhnacademy.command.Command;
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

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/Users/suhan/Downloads/test";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //id,pwd,name,profile 로 들어온 데이터를 userRepository 받아서 setAtrrbute 하기

        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        System.out.println("userRepository.getUsers() = " + userRepository.getUsers());

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        String name = req.getParameter("name");

//        String UPLOAD_DIR = "/WEB-INF/classes/img";

//        System.out.println("UPLOAD_DIR = " + UPLOAD_DIR + "   " + id + " " + pwd + " " + name);

        try {
            for (Part part : req.getParts()) {
                String contentDisposition = part.getHeader(CONTENT_DISPOSITION);

                if (contentDisposition.contains("filename=")) {
                    String fileName = extractFileName(contentDisposition);

                    if (part.getSize() > 0) {
                        String filePath = UPLOAD_DIR + File.separator + fileName;
                        part.write(filePath); //임시 저장된 파일을 내가 원하는 곳에 쓰고
                        part.delete(); //임시파일은 지워버림

                        GeneralUser user = new GeneralUser(id, pwd, name);
                        user.setProfileFileName(fileName);
                        user.setProfilePath(filePath);
                        userRepository.add(user);
                    }
                } else {
                    String formValue = req.getParameter(part.getName());
                    log.error("{}={}", part.getName(), formValue);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


        return "/admin/addUser.jsp";
    }

    private String extractFileName(String contentDisposition) {
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                String fileName = token.substring(token.indexOf("=") + 1).trim().replace("\"", "");
                int index = fileName.lastIndexOf(File.separator);
                return fileName.substring(index + 1);
            }
        }

        return null;
    }
}
