package com.crio.starter.controller;

import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.XmemeService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class XmemeController {

  @Autowired
  private final XmemeService xmemeService;

  @PostMapping("memes")
  public ResponseEntity<ResponseDto> saveMemes(@Valid @RequestBody ResponseDto responseDto){
    ResponseDto response = null;
    response = xmemeService.saveMemes(responseDto);
    if(response == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
    return ResponseEntity.ok(response);
  }
  
  @GetMapping("memes/{id}")
  public ResponseEntity<ResponseDto> getMemeById(@PathVariable("id") long id){
    ResponseDto response = null;
    response = xmemeService.getMemeById(id);
    if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    return ResponseEntity.ok(response);
  }

  @GetMapping("memes")
  public ResponseEntity<List<ResponseDto>> getMemes(){
    List<ResponseDto> responses = null;
    responses = xmemeService.getMemes();
    if(responses == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    return ResponseEntity.ok(responses);
  }

  @DeleteMapping("memes/{id}")
  public ResponseEntity<ResponseDto> deleteMemeById(@PathVariable("id") long id) {
    ResponseDto response = null;
    response = xmemeService.deleteMemeById(id);
    if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    return ResponseEntity.ok(response);
  }

}
