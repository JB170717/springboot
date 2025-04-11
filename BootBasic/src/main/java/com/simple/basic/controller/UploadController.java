package com.simple.basic.controller;


import com.simple.basic.command.UploadVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/upload")
public class UploadController {

    @Value("${com.coding404.myweb.upload.path}")
    private String uploadPath;

    //폴더생성함수
    private String makeFolder(){
        String filepath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        File file = new File(uploadPath+ "/" + filepath);

        if(file.exists()==false){ //해당 위치에 파일 or 폴더가 존재하면 true
            file.mkdirs(); //폴더를 생성..
        }
        return filepath;
    }

    //화면처리
    @GetMapping("/upload")
    public String upload() {
        return "upload/upload";
    }

    //단일파일 업로드
    @PostMapping("/upload_ok")
    public String upload_ok(@RequestParam("file") MultipartFile file) {

        //1. 동일한 이름의 파일명으로 올라오면 기존 파일이 지워지는 문제 (랜덤난수명칭 이름을 바꿈)
        //2. 각 브라우저 별로 업로드패스가 다를 수도 있음.
        //3. 윈도우의 한 폴더에 최대 파일저장개수 65536개임
        //4. 확장자 체크
        //5. 사진의 크기를 똑같이 맞추는 썸네일 고려
        //filename, uuid, filepath(날짜) 이 값을 디비에 저장하겠다~~

        try {
            String originName = file.getOriginalFilename();
            String filename = originName.substring(originName.lastIndexOf("/") +1 );


            Long size = file.getSize();
            byte[] arr = file.getBytes();
            String contentType = file.getContentType();

            UUID uuid = UUID.randomUUID(); //16진수형태의 랜덤문자열을 반환
            String filepath = makeFolder(); //파일이 저장된 해당날짜 폴더

            String path = uploadPath+ "/"+ filepath +"/"+ uuid + "_"+ filename; // 업로드 패스

            File saveFile = new File(path);
            file.transferTo(saveFile); //파일업로드를 처리함

//            log.info("파일명"+originName); //실제이름
//            log.info("크기:"+size); //파일크기
//            log.info("파일데이터:"+arr); //파일데이터
//            log.info("컨텐츠타입:"+contentType); //마임타입

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "upload/upload_ok";
    }

    //멀티플 속성 다중파일
    @PostMapping("/upload_ok2")
    public String upload2(MultipartHttpServletRequest files) {

        List<MultipartFile> list= files.getFiles("file");  // 태그의 name값

        for(MultipartFile file : list){
            //이하 여백....
            try {
                String originName = file.getOriginalFilename();
                String filename = originName.substring(originName.lastIndexOf("/") +1 );
                UUID uuid = UUID.randomUUID(); //16진수형태의 랜덤문자열을 반환
                String filepath = makeFolder(); //파일이 저장된 해당날짜 폴더

                String path = uploadPath+ "/"+ filepath +"/"+ uuid + "_"+ filename; // 업로드 패스

                File saveFile = new File(path);
                file.transferTo(saveFile); //파일업로드를 처리함
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "upload/upload_ok";
    }

    //복수태그를 이용해서 여러파일 - 우리 프로젝트에서 예시로 사용해볼 유형
    @PostMapping("/upload_ok3")
    public String upload3(@RequestParam("file") List<MultipartFile> list ) {

        //리스트안에 multipartfile의 값이 비었으면 제거
        list = list.stream().filter( f -> f.isEmpty() == false ).collect(Collectors.toList());

        for(MultipartFile file : list) {
            //이하여백
            try {
                String originName = file.getOriginalFilename();
                String filename = originName.substring( originName.lastIndexOf("/") + 1 );
                UUID uuid = UUID.randomUUID(); //16진수형태의 랜덤문자열을 반환
                String filepath = makeFolder(); //파일이 저장된 해당날짜 폴더

                String path = uploadPath + "/" + filepath + "/" + uuid + "_" + filename ; //업로드 패스

                File saveFile = new File( path);
                file.transferTo(saveFile); //파일업로드를 처리함
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "upload/upload_ok";
    }

    //ajax 요청을 받는 함수
    //값을 받을떄는 @RequestParam쓰거나 VO객체 맴핑..
    @ResponseBody
    @PostMapping("/upload_ok4")
    public String upload4(UploadVO vo){

        MultipartFile file = vo.getFile();
        try {
            String originName = file.getOriginalFilename();
            String filename = originName.substring( originName.lastIndexOf("/") + 1 );
            UUID uuid = UUID.randomUUID(); //16진수형태의 랜덤문자열을 반환
            String filepath = makeFolder(); //파일이 저장된 해당날짜 폴더

            String path = uploadPath + "/" + filepath + "/" + uuid + "_" + filename ; //업로드 패스

            File saveFile = new File( path);
            file.transferTo(saveFile); //파일업로드를 처리함
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }



}
