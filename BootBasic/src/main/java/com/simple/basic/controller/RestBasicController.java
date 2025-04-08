package com.simple.basic.controller;

import com.simple.basic.command.SimpleVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController //일반컨트롤러랑은 다른의미 - return에 담기는 데이터가 요청한 곳으로 응답하게 된다.
//@CrossOrigin("......")//다 열어준다
//@Controller + @ResponseBody = @RestController
public class RestBasicController {

    /*브라우저에서 요청하고 브라우저에서 데이터를 return을 받아/ 브라우저로 응답이 온다*/
    @GetMapping("/hello")
    public String hello() {
        return "<h3>Hello World</h3>";

    }

    //데이터를 보   내는 방법
    //1. 객체반환
    //ResponseBody와 Json-databind라이브러리가 해줍니다(자동으로)
    @GetMapping("/bye")
    public SimpleVO bye(){
        return new SimpleVO(1, "홍길동" , LocalDateTime.now());
    }

    //2. Map을 반환
    @GetMapping("/getMap")
    public Map<String, Object> getMap(){
        Map<String, Object> map = new HashMap<>();

        map.put("name","손유경");
        map.put("age",20);
        map.put("data",new SimpleVO(1,"홍길동",LocalDateTime.now()));

        return map;

    }

    //3. list을 반환
    @GetMapping("/getList")
    public List<SimpleVO> getList(){

        List<SimpleVO> list = new ArrayList<>();
        list.add(new SimpleVO(1,"홍길동",LocalDateTime.now()));
        list.add(new SimpleVO(2,"김영희",LocalDateTime.now()));

        return list;
    }


    //////////////
    //값을 받는 방법

    //요청의 다양한 타입 get <셋중하나 반드시>
    //@RequestParam이나, VO를 통해서 받을수 있음
    //http://localhost:8181/getData?name=홍길동&sno=2
    @GetMapping("/getData")
    public String getData(@RequestParam("name") String name,
                          @RequestParam("sno") int sno){

        log.info(name+", "+sno);


        return "success";
    }
    //VO
    //http://localhost:8181/getData2?name=홍길동&sno=2
    @GetMapping("/getData2")
    public String getData2(SimpleVO vo){
        log.info(vo.toString());
        return "success";
    }

    //PathVariable
    //http://localhost:8181/getData3/박종범/2
    @GetMapping("/getData3/{name}/{sno}")
    public String getData3(@PathVariable("name") String name,
                           @PathVariable("sno") int sno){
        log.info(name+", "+sno);
        return "success";
    }

    //////////////////////////
    //post방식으로 데이터 받기
    //상대방이 데이터를 보내는 contentType을 지정함(form타입, JSON타입)

    //보내는 입장이 form타입으로 보내는경우

    //보내는 입장이 form형식이라는 것을 반드시 써줘야함
//    @PostMapping("/getForm")
//    public String getForm(SimpleVO vo){
//
//        log.info(vo.toString());
//        return "success";
//    }

    @PostMapping("/getForm")
    public String getForm(@RequestParam("name") String name,
                          @RequestParam("sno") int sno){

        log.info(name+", "+sno);
        return "success";
    }

    //보내는 입장이 json타입으로 보내는경우 -->> VO 또는 MAP타입으로 받아야함


    //@RequestBody가 없다면, 서버에서 JSON형식의 데이터를 클라이언트가 JSON->VO로 맵핑을 못함.. 그걸 @RequestBody가 도와줌
    //@RequestBody -->>JSON데이터를 Object맵핑
//    @PostMapping("/getJson")
//    public String getJson(@RequestBody SimpleVO vo){
//
//        log.info(vo.toString());
//        return "success";
//    }

    @PostMapping("/getJson")
    public String getJson(@RequestBody Map<String, Object> map){

        log.info(map.toString());
        return "success";
        // {name=홍길자, sno=2}
    }

    /////////////////////////////////////////////////////////////////////
    //produces -서버에서 보내는 타입에 대한 명세 (아무것도 안적으면 기본 JSON타입)
    //consumers- "너 반드시 이렇게 데이터 보내" 명세

    @PostMapping(value = "/getResult",
                 produces = "text/plain",
                consumes = "text/plain")
    public String getResult(@RequestBody String str){

        log.info(str);

        return "<h3>html타입</h3>";
    }

    ////////////////////////////////////////////////////
    //응답 문서 작성하기 ResponseEntity<보낼데이터타입>

    @PostMapping("/createEntity")
    public ResponseEntity<SimpleVO> createEntity(){

        SimpleVO vo = new SimpleVO(2,"홍길동", LocalDateTime.now());

        //응답문서에 헤더에 대한 내역을 추가할 수 있음.
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        headers.add("authorization", "JSON WEB TOKEN");
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<>(vo, headers , HttpStatus.OK);
    }

    ///////////////////////////////////////
    //명세서에 맞춰 부메랑으로 호출
    /*
    요청주소 - /api/v1/getData
    메서드 - get
    클라이언트에서 보내는 데이터 - num, name
    서버에서 응답하는 데이터는 - SimptleVO
    responceEntity로 응답
     */

    //@CrossOrigin("http://127.0.0.1:5500") //서버가 다를시에... 해당 클라이언트 서버를 허용해준다
    //@CrossOrigin({"첫번째주소","두번째주소"})
    @CrossOrigin("*")
    @GetMapping("/api/v1/getData")
    public ResponseEntity<SimpleVO> getData(@RequestParam("num") int num,
                                            @RequestParam("name") String name){

        log.info(num+", "+name);

        SimpleVO vo= new SimpleVO(2, name,LocalDateTime.now());

        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    /*
     요청주소 - /api/v1/getInfo
     메서드 - post
     클라이언트에서 보내는 데이터 - JSON타입의 num, name
     서버에서 응답하는 데이터는 - List<SimpleVO>
     responceEntity로 응답
     */

    @CrossOrigin("*")
    @PostMapping("/api/v1/getInfo")
    public ResponseEntity<List<SimpleVO>> getInfo(@RequestBody Map<String,Object> map){

        log.info(map.toString());

        List<SimpleVO> list = new ArrayList<>();
        list.add(new SimpleVO(1,"홍길동",LocalDateTime.now()));
        list.add(new SimpleVO(2,"김영희",LocalDateTime.now()));

        return new ResponseEntity<>(list, HttpStatus.OK);
    }



}
