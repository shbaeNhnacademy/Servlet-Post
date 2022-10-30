package com.nhnacademy.controller.user;

import com.nhnacademy.command.Command;
import com.nhnacademy.command.CommandUtil;
import com.nhnacademy.domain.repository.UserRepository;
import com.nhnacademy.domain.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Map;

@Slf4j
public class UserDeleteController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        Map<String, HttpSession> sessionMap = (Map<String, HttpSession>) req.getServletContext().getAttribute("sessionMap");


        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            String id = names.nextElement();
            UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
            User remove = userRepository.remove(id);


            File file = new File(CommandUtil.UPLOAD_DIR+File.separator+remove.getProfileFileName());

            if( file.exists() ){
                try {
                    cleanUp(file.toPath());
                    log.error("파일삭제 성공");
                } catch (IOException e) {
                    log.error("파일삭제 실패");
                }
            }else{
                log.error("파일이 존재하지 않습니다.");
            }

            if (sessionMap.containsKey(id)) {
                HttpSession httpSession = sessionMap.remove(id);
                httpSession.removeAttribute(id);
                httpSession.invalidate();
            }

        }

        return "/admin/userList.jsp";
    }

    public void cleanUp(Path path) throws NoSuchFileException, DirectoryNotEmptyException, IOException {
        Files.delete(path);
    }
}
