package com.kingdee.internet.finance.controller;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import utils.ImageUtil;

@Controller
@RequestMapping(value = "img", produces = "application/json;charset=utf-8")
public class ImageController {

	/**
	 * 缩略图
	 * 
	 * @param id
	 * @param width
	 * @param height
	 * @param request
	 * @param response
	 * @return
	 * @see
	 */
	@RequestMapping(value = "/thumbnail", method = RequestMethod.GET)
	@ResponseBody
	public void index(@RequestParam("id") String id, @RequestParam("width") int width,
			@RequestParam("height") int height, HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("image/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			ImageIO.write(ImageUtil.resize("http://172.20.10.48/cmbc/18675559296/" + id, width, height), "JPEG",
					response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
