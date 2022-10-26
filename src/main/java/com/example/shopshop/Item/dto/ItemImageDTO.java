package com.example.shopshop.Item.dto;

import com.example.shopshop.Item.domain.Item;
import lombok.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemImageDTO {

    private String uuid;

    private String imgName;

    private String imgPath;

    public String getImageURL() {
        try {
            return URLEncoder.encode(imgPath + "/" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL() {
        try {
            return URLEncoder.encode(imgPath + "/s_" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
