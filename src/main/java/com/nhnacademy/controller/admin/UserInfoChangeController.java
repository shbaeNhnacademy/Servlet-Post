package com.nhnacademy.controller.admin;

import com.nhnacademy.command.Command;
import com.nhnacademy.command.CommandUtil;
import com.nhnacademy.domain.repository.UserRepository;
import com.nhnacademy.domain.user.GeneralUser;
import com.nhnacademy.domain.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class UserInfoChangeController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        String id = req.getParameter("id");

        User user = userRepository.getUser(id);
        if (!Objects.isNull(user) && user.getId().equals(id)) {
            // 변경한건지 확인 필요
            String pwd = req.getParameter("pwd");
            String name = req.getParameter("name");
            if (!user.getPassword().equals(pwd) && !pwd.equals("")) {
                //pwd가 공백이 아니고 이전 비밀번호와 값이 같지 않다면 변경
                user.setPassword(pwd);
            }
            if (!user.getName().equals(name) && !name.equals("")) {
                //name이 공백이 아니고 이전 이름과 값이 같지 않다면 변경
                user.setName(name);
            }
            String file = verifyChangingFile(req, user);
            if (!file.equals("")) {
                user.setProfileFileName(file);
            }
            log.warn("{}",userRepository.getUsers());
            return "/admin/userList.jsp";
        }else {
            //null으로 아예 안들어 왔거나, 맞는 아이디가 없거나 -> userList.jsp로 바로 넘겨버리기
            return "redirect:/admin/userList.jsp";
        }

    }

    private String verifyChangingFile(HttpServletRequest req, User user) {
        try {
            for (Part part : req.getParts()) {
                String contentDisposition = part.getHeader(CommandUtil.CONTENT_DISPOSITION);

                if (contentDisposition.contains("filename=")) {
                    String fileName = CommandUtil.extractFileName(contentDisposition);
                    if (!user.getProfileFileName().equals(fileName) && part.getSize() > 0) {
                        String filePath = CommandUtil.UPLOAD_DIR + File.separator + fileName;
                        part.write(filePath); //임시 저장된 파일을 내가 원하는 곳에 쓰고
                        part.delete(); //임시파일은 지워버림
                        return fileName;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
