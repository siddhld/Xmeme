package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "xmeme")
@NoArgsConstructor
public class XmemeEntity {

  @Id
  private long id;
  private String name;
  private String url;
  private String caption;

  // public XmemeEntity() {}

  // public XmemeEntity(long id, String name, String url, String caption) {
  //   this.id = id;
  //   this.name = name;
  //   this.url = url;
  //   this.caption = caption;
  // }
  
  // public long getId() {
  //   return id;
  // }
  // public void setId(long id) {
  //   this.id = id;
  // }
  // public String getName() {
  //   return name;
  // }
  // public void setName(String name) {
  //   this.name = name;
  // }
  // public String getUrl() {
  //   return url;
  // }
  // public void setUrl(String url) {
  //   this.url = url;
  // }
  // public String getCaption() {
  //   return caption;
  // }
  // public void setCaption(String caption) {
  //   this.caption = caption;
  // }

  // @Override
  // public String toString() {
  //   return "XmemeEntity [caption=" + caption + ", id=" + id + ", name=" + name + ", url=" + url
  //       + "]";
  // }

}
