package com.crio.starter.service;


import com.crio.starter.App;
import com.crio.starter.exchange.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = {App.class})
public class XmemeServiceTest {

    @Autowired
    private XmemeService xmemeService;

    private ResponseDto responseDto;

    @BeforeEach
    public void setup() {
        responseDto = new ResponseDto(1L, "Meme", "https://test.com/meme", "Caption");
        xmemeService.saveMemes(responseDto);
    }

    @Test
    public void testSaveMemes() {

        ResponseDto dummy = new ResponseDto(2L, "Meme2", "https://test2.com/meme", "Caption2");

        xmemeService.saveMemes(dummy);

        ResponseDto response = xmemeService.getMemeById(dummy.getId());

        assertEquals(dummy.getId(), response.getId());
        assertEquals(dummy.getName(), response.getName());
        assertEquals(dummy.getUrl(), response.getUrl());
        assertEquals(dummy.getCaption(), response.getCaption());

    }

    @Test
    public void testGetMemeById() {
        
        ResponseDto response = xmemeService.getMemeById(responseDto.getId());

        boolean actual = false;
        if(response != null) actual = true;

        assertEquals(true , actual);

    }

    @Test
    public void testGetMemes() {
        
        ResponseDto dummy = new ResponseDto(3L, "Meme3", "https://test3.com/meme", "Caption3");

        xmemeService.saveMemes(dummy);

        List<ResponseDto> responseDtos = xmemeService.getMemes();

        assertTrue(responseDtos.size() >= 2);

    }

}