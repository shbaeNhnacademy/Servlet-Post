package com.nhnacademy.controller.user;

import com.nhnacademy.command.Command;
import com.nhnacademy.command.CommandUtil;
import com.nhnacademy.domain.repository.UserRepository;
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
public class UserModifyController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        String id = req.getParameter("id");

        User user = userRepository.getUser(id);
        if (!Objects.isNull(user) && user.getId().equals(id)) {
            String pwd = req.getParameter("pwd");
            String name = req.getParameter("name");
            if (!user.getPassword().equals(pwd) && !pwd.equals("")) {
                user.setPassword(pwd);
            }
            if (!user.getName().equals(name) && !name.equals("")) {
                user.setName(name);
            }
            String file = verifyChangingFile(req, user);
            if (!file.equals("")) {
                user.setProfileFileName(file);
            }
            return "/admin/userList.jsp";
        }else {
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
                        part.write(filePath);
                        part.delete();
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
