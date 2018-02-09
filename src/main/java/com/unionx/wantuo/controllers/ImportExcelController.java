package com.unionx.wantuo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.service.ImportExcelService;

@Controller
@RequestMapping("/excel")
public class ImportExcelController {
	@Autowired
	private ImportExcelService importExcelService;
	@ResponseBody
	@RequestMapping("/import")
	public void importExcel(String file){
		file = "C:\\Users\\yangxiao\\Desktop\\jigouguanli.xlsx";
		System.out.println("import");
		importExcelService.importExcel(file);
		
		
	}

}
