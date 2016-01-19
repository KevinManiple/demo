package utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelDemo {

	public static Map<String, String> proMap = new HashMap<String, String>();

	public static void main(String[] args) {
		generatePro();
		generateCity();
	}

	public static void generatePro() {
		try {
			FileInputStream file = new FileInputStream(new File("C:/Users/Administrator/Desktop/1.xls"));
			//Get the workbook instance for XLS file 
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			List<ExcelEntryPro> list = new ArrayList<ExcelEntryPro>();
			while (rowIterator.hasNext()) {
				ExcelEntryPro entry = new ExcelEntryPro();
				Row row = rowIterator.next();
				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int index = cell.getColumnIndex();
					if (index == 0) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							entry.setFcode(cell.getBooleanCellValue() + "");
							break;
						case Cell.CELL_TYPE_NUMERIC:
							entry.setFcode((int) cell.getNumericCellValue() + "");
							break;
						case Cell.CELL_TYPE_STRING:
							entry.setFcode(cell.getStringCellValue() + "");
							break;
						}
					}
					if (index == 1) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							entry.setFname(cell.getBooleanCellValue() + "");
							break;
						case Cell.CELL_TYPE_NUMERIC:
							entry.setFname(cell.getNumericCellValue() + "");
							break;
						case Cell.CELL_TYPE_STRING:
							entry.setFname(cell.getStringCellValue() + "");
							break;
						}
					}
				}
				if (entry.getFcode().equals("编码")) {
					continue;
				}
				entry.setFid(UUID.randomUUID().toString().replaceAll("-", ""));
				entry.setFcreator("admin");
				entry.setFcreatedate(new Date());
				entry.setFstatus("1");
				list.add(entry);
			}
			System.out.println("--省列表个数: " + list.size());
			System.out.println("--省JSON列表: " + list.toString());
			file.close();
			StringBuffer sb = new StringBuffer();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			for (ExcelEntryPro pro : list) {
				sb.append("INSERT INTO T_BS_AREA_PROVINCE VALUES('").append(pro.getFid()).append("','")
						.append(pro.getFcode()).append("','").append(pro.getFname()).append("',")
						.append(pro.getFstatus()).append(",'").append(pro.getFcreator()).append("',")
						.append("TO_DATE('").append(sdf.format(pro.getFcreatedate())).append("','YYYY/MM/DD HH:MI:SS'")
						.append("));").append("\n");
				proMap.put(pro.getFname(), pro.getFcode());
			}
			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void generateCity() {
		try {
			FileInputStream file = new FileInputStream(new File("C:/Users/Administrator/Desktop/2.xls"));
			//Get the workbook instance for XLS file 
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(1);
			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			List<ExcelCity> list = new ArrayList<ExcelCity>();
			while (rowIterator.hasNext()) {
				ExcelCity entry = new ExcelCity();
				Row row = rowIterator.next();
				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int index = cell.getColumnIndex();
					if (index == 0) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							entry.setFcode(cell.getBooleanCellValue() + "");
							break;
						case Cell.CELL_TYPE_NUMERIC:
							entry.setFcode((int) cell.getNumericCellValue() + "");
							break;
						case Cell.CELL_TYPE_STRING:
							entry.setFcode(cell.getStringCellValue() + "");
							break;
						}
					}
					if (index == 1) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							entry.setFname(cell.getBooleanCellValue() + "");
							break;
						case Cell.CELL_TYPE_NUMERIC:
							entry.setFname(cell.getNumericCellValue() + "");
							break;
						case Cell.CELL_TYPE_STRING:
							entry.setFname(cell.getStringCellValue() + "");
							break;
						}
					}
					if (index == 2) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							entry.setFprovincecode(cell.getBooleanCellValue() + "");
							break;
						case Cell.CELL_TYPE_NUMERIC:
							entry.setFprovincecode(cell.getNumericCellValue() + "");
							break;
						case Cell.CELL_TYPE_STRING:
							entry.setFprovincecode(cell.getStringCellValue() + "");
							break;
						}
					}
				}

				if (StringUtils.isBlank(entry.getFcode()) || entry.getFcode().equals("城市编码")) {
					continue;
				}
				entry.setFid(UUID.randomUUID().toString().replaceAll("-", ""));
				entry.setFcreator("admin");
				entry.setFcreatedate(new Date());
				entry.setFstatus("1");
				list.add(entry);
			}
			System.out.println("--市列表数量: " + list.size());
			System.out.println("--市列表JSON: " + list.toString());
			file.close();
			StringBuffer sb = new StringBuffer();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			for (ExcelCity pro : list) {
				sb.append("INSERT INTO T_BS_AREA_PROVINCE_CITY VALUES('").append(pro.getFid()).append("','")
						.append(pro.getFcode()).append("','").append(pro.getFname()).append("','")
						.append(proMap.get(pro.getFprovincecode())).append("',").append(pro.getFstatus()).append(",'")
						.append(pro.getFcreator()).append("',").append("TO_DATE('")
						.append(sdf.format(pro.getFcreatedate())).append("','YYYY/MM/DD HH:MI:SS'").append("));")
						.append("\n");
			}
			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class ExcelEntryPro {
	private String fid;
	private String fcode;
	private String fname;
	private String fstatus;
	private String fcreator;
	private Date fcreatedate;

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFcode() {
		return fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFstatus() {
		return fstatus;
	}

	public void setFstatus(String fstatus) {
		this.fstatus = fstatus;
	}

	public String getFcreator() {
		return fcreator;
	}

	public void setFcreator(String fcreator) {
		this.fcreator = fcreator;
	}

	public Date getFcreatedate() {
		return fcreatedate;
	}

	public void setFcreatedate(Date fcreatedate) {
		this.fcreatedate = fcreatedate;
	}

	@Override
	public String toString() {
		return "ExcelEntryPro [fid=" + fid + ", fcode=" + fcode + ", fname=" + fname + ", fstatus=" + fstatus
				+ ", fcreator=" + fcreator + ", fcreatedate=" + fcreatedate + "]";
	}

}

class ExcelCity {

	private String fid;
	private String fcode;
	private String fname;
	private String fprovincecode;
	private String fstatus;
	private String fcreator;
	private Date fcreatedate;

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFcode() {
		return fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFprovincecode() {
		return fprovincecode;
	}

	public void setFprovincecode(String fprovincecode) {
		this.fprovincecode = fprovincecode;
	}

	public String getFstatus() {
		return fstatus;
	}

	public void setFstatus(String fstatus) {
		this.fstatus = fstatus;
	}

	public String getFcreator() {
		return fcreator;
	}

	public void setFcreator(String fcreator) {
		this.fcreator = fcreator;
	}

	public Date getFcreatedate() {
		return fcreatedate;
	}

	public void setFcreatedate(Date fcreatedate) {
		this.fcreatedate = fcreatedate;
	}

	@Override
	public String toString() {
		return "ExcelCity [fid=" + fid + ", fcode=" + fcode + ", fname=" + fname + ", fprovincecode=" + fprovincecode
				+ ", fstatus=" + fstatus + ", fcreator=" + fcreator + ", fcreatedate=" + fcreatedate + "]";
	}

}
