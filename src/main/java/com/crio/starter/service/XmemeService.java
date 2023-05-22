package com.crio.starter.service;


import com.crio.starter.exchange.ResponseDto;
import java.util.List;


public interface XmemeService {
  public ResponseDto saveMemes(ResponseDto responseDto);
  public ResponseDto getMemeById(long id);
  public ResponseDto deleteMemeById(long id);
  public List<ResponseDto> getMemes();
}
