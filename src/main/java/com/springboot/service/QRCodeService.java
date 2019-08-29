package com.springboot.service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@Service
public class QRCodeService {

	public void getQRCode(HttpServletResponse resp) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("丛丽娜", "是个小仙女");
		jsonObject.put("age", "25");
		jsonObject.put("Birthday", "11月21日");
		String jsonString = jsonObject.toString();
		HashMap<EncodeHintType, Object> hashMap = new HashMap<EncodeHintType,Object>();
		hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix multiFormatWriter = new MultiFormatWriter().encode(jsonString, BarcodeFormat.QR_CODE, 200, 200,hashMap);
		String path="F://";
		String fileName="cln.jpg";
		Path path2 = FileSystems.getDefault().getPath(path, fileName);
		ServletOutputStream respOutputStream = resp.getOutputStream();
		MatrixToImageWriter.writeToPath(multiFormatWriter, "jpg", path2);	
		
	}
	
	

}
