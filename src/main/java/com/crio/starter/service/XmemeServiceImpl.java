package com.crio.starter.service;

import com.crio.starter.data.XmemeEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.XmemeRepository;
import com.crio.starter.utils.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class XmemeServiceImpl implements XmemeService {

  @Autowired
  private final XmemeRepository xmemeRepository;

  @Autowired
  private SequenceGeneratorService seqGen;

  @Autowired
  private MongoTemplate mTemplate;

  @Autowired
  private ModelMapper mapper;


  public ResponseDto saveMemes(ResponseDto responseDto){
    
    boolean isTrue = validatePost(responseDto);
    if(!isTrue) return null;
    
    ResponseDto response = null;

    try {
      XmemeEntity xmemeEntity = mapper.map(responseDto, XmemeEntity.class);

      // Validating that the given data is already present in Db or not
      XmemeEntity xmeme = xmemeRepository.findByNameAndUrlAndCaption(xmemeEntity.getName(), xmemeEntity.getUrl(), xmemeEntity.getCaption());
      if(xmeme != null) {
        return null;
      }

      // setting the auto-gen id
      xmemeEntity.setId(seqGen.generateSequence());
      
      XmemeEntity dummy = xmemeRepository.save(xmemeEntity);
      response = mapper.map(dummy, ResponseDto.class);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return response;
  }

  public ResponseDto getMemeById(long id) {
    
    ResponseDto responseDto = null;
    XmemeEntity xmemeEntity = null;
    try {
      // Fetching the records from DB
      xmemeEntity = xmemeRepository.findById(id).get();

      // mapping from xmemeEntity to responseDto
      responseDto = mapper.map(xmemeEntity, ResponseDto.class);

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    
    return responseDto;
  }
  
  public List<ResponseDto> getMemes(){
    
    List<ResponseDto> responseDtos = new ArrayList();
    List<XmemeEntity> xmemeEntity;
    
    try {
      // Aggregating the data in dec order and fetching 100 records
      Aggregation aggregation = Aggregation.newAggregation(
      Aggregation.sort(Sort.Direction.DESC, "_id"),
      Aggregation.limit(100));

      // Fetching all the records from DB
      xmemeEntity = mTemplate.aggregate(aggregation, "xmeme", XmemeEntity.class).getMappedResults();


      // mapping from xmemeEntity to responseDto
      for(XmemeEntity entity : xmemeEntity){
        responseDtos.add(mapper.map(entity, ResponseDto.class));
      }

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return responseDtos;
  }

  public ResponseDto deleteMemeById(long id){
    
    ResponseDto responseDto = null;
    XmemeEntity xmemeEntity = null;
    try {
      // Fetching all the records from DB
      xmemeEntity = xmemeRepository.findById(id).get();
      
      // If no record is present for the given id then returning null
      if(xmemeEntity == null) return null;
      
      xmemeRepository.deleteById(id);

      // mapping from xmemeEntity to responseDto
      responseDto = mapper.map(xmemeEntity, ResponseDto.class);

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    
    return responseDto;
  }

  private boolean validatePost(ResponseDto post){

    if(post == null) return false;

    String name = post.getName();
    String url = post.getUrl();
    String caption = post.getCaption();

    if (name == null || name.isBlank()){
      return false;
    }

    if (url == null || url.isBlank()){
      return false;
    }

    if (caption == null || caption.isBlank()) {
      return false;
    }
    return true;
  }

}
