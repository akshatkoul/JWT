//package com.wings.may24.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.wings.may24.model.Employee;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.async.DeferredResult;
//import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
//@RestController
//@RequestMapping("/employee")
//public class EmployeeController {
//
//    @Async
//    @GetMapping
//    public CompletableFuture<ResponseEntity<StreamingResponseBody>> getAllEmployees(){
//        List<Employee> employees = new ArrayList<>();
//        DeferredResult deferredResult = new DeferredResult<>();
////        for (long i = 0; i < 10000000; i++) {
////            Employee employee = Employee.builder().id(i).name("name "+i).build();
////            employees.add(employee);
////        }
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        StreamingResponseBody streamingResponseBody = outputStream -> {
//
//            for (long i = 0; i < 100000000; i++) {
//                Employee employee = Employee.builder().id(i).name("name "+i).build();
//                String json = objectMapper.writeValueAsString(employee);
//                outputStream.write(json.getBytes(StandardCharsets.UTF_8));
//                outputStream.write("#".getBytes(StandardCharsets.UTF_8));
//            }
//        };
//        deferredResult.setResult(streamingResponseBody);
////        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
////                .body(streamingResponseBody);
//        return deferredResult;
//    }
//}
