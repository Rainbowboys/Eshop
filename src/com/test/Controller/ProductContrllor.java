package com.test.Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.Dao.ProductDao;
import com.test.Model.ProductBean;
import com.test.util.DateUtil;
import com.test.util.StringUtil;

@Controller
@RequestMapping("/product")
public class ProductContrllor {

	@Value("#{configProperties['pic_upload_path']}")
	private String pic_upload_path;
	@Value("#{configProperties['pic_show_path']}")
	private String pic_show_path;

	@Autowired
	ProductDao productDao;

	@RequestMapping("/add.do")
	public String add(HttpServletRequest req, HttpServletResponse reps,
			String option) {

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
		try {
			List<FileItem> fileitems = upload.parseRequest(req);
			PrintWriter pw = reps.getWriter();
			ProductBean productBean = new ProductBean();
			for (FileItem fileItem : fileitems) {
				fileItem.getString("utf-8");
				if (fileItem.isFormField()) {
					// 处理表单内容
					processFormField(fileItem, pw, productBean);
				} else {
					// 上传文件
					processUploadFile(fileItem, pw, productBean);

				}
			}

			if (productBean.getId() == 0) {
				productDao.add(productBean);
			} else {
				productDao.update(productBean);
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "admin/product/add";

	}

	@RequestMapping("/delete.do")
	public String delete(String id) {
		productDao.delete(StringUtil.StringToInt(id));
		return "redirect:tolist.do";

	}

	@RequestMapping("/tolist.do")
	public String list(HttpServletRequest req, HttpServletResponse reps,
			Model model) {
		List productBeans = productDao.productBeanlist();
		model.addAttribute("productBeans", productBeans);
		return "admin/product/list";
	}

	@RequestMapping("/detail.do")
	public String detail(String id, Model model) {

		ProductBean productBean = productDao.getProductBeanById(StringUtil
				.StringToInt(id));
		model.addAttribute("productBean", productBean);
		return "admin/product/details";
	}

	@RequestMapping("/update.do")
	public String toupdate(String id, Model model) {
		ProductBean productBean = productDao.getProductBeanById(StringUtil
				.StringToInt(id));
		model.addAttribute("productBean", productBean);
		return "admin/product/add";
	}

	/**
	 * 处理表单数据
	 * 
	 * @param fileItem
	 * @param pw
	 * @param productBean
	 * @throws UnsupportedEncodingException
	 */

	private void processFormField(FileItem fileItem, PrintWriter pw,
			ProductBean productBean) throws UnsupportedEncodingException {
		String name = fileItem.getFieldName();
		String value = new String(fileItem.getString("utf-8"));
		System.out.println(name);
		switch (name) {
		case "id":
			productBean.setId(StringUtil.StringToInt(value));

			break;
		case "intro":
			productBean.setIntro(value);
			break;
		case "number":
			productBean.setNumber(StringUtil.StringToInt(value));
			break;
		case "price":
			productBean.setPrice(StringUtil.strToFlo(value));

			break;
		case "originalPrice":
			productBean.setOriginalPrice(StringUtil.strToFlo(value));

			break;
		case "productTypeId":
			int productTypeId = Math.max(productBean.getProductTypeId(),
					StringUtil.StringToInt(value));
			productBean.setProductTypeId(productTypeId);

			break;
		case "name":
			productBean.setName(value);
			break;
		case "option":
			String options = productBean.getProductProperties();
			if (options == null) {
				productBean.setProductProperties(value);
			} else {
				productBean.setProductProperties(options + "," + value);
			}

			break;

		default:
			break;
		}

	}

	/**
	 * 处理非表单数据 文件等
	 * 
	 * @param fileItem
	 * @param pw
	 * @param productBean
	 */

	private void processUploadFile(FileItem fileItem, PrintWriter pw,
			ProductBean productBean) {
		String name = fileItem.getName();
		int index = name.lastIndexOf(".");
		name = name.substring(index + 1, name.length());
		String picPath = pic_show_path + "/" + DateUtil.getDateStr() + "/"
				+ DateUtil.getTimeStr() + "." + name;
		long fileSize = fileItem.getSize();
		if ("".equals(name) && fileSize == 0) {
			return;
		}
		// 新建文件夹，日期为文件夹名，时间为文件名
		File file = new File(pic_upload_path + "/" + DateUtil.getDateStr());
		file.mkdirs();
		File uploadFile = new File(pic_upload_path + "/"
				+ DateUtil.getDateStr() + "/" + DateUtil.getTimeStr() + "."
				+ name);

		try {
			fileItem.write(uploadFile);
			productBean.setPic(picPath);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
