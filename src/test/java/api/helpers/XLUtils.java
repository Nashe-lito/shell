package api.helpers;

import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtils
{
  public static FileInputStream fi;
  public static FileOutputStream fo;
  public static XSSFWorkbook wb;
  public static XSSFSheet ws;
  public static XSSFRow row;
  public static XSSFCell cell;


  public static int getRowCount(String xlfile,String xlsheet) throws IOException
  {
   // int rowcount;
    fi=new FileInputStream(xlfile);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(xlsheet);
    int rowcount = ws.getLastRowNum();
    wb.close();
    fi.close();
    return rowcount;
  }
  public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
  {
   // int cellcount;
    fi= new FileInputStream(xlfile);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(xlsheet);
    row =  ws.getRow(rownum);
   int cellcount = row.getLastCellNum();
    wb.close();
    fi.close();
    return cellcount;
  }

  public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
  {
    String data;
    fi = new FileInputStream(xlfile);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(xlsheet);
    row = ws.getRow(rownum);
    cell = row.getCell(colnum);
    try
    {
      data = cell.getStringCellValue();
      return data;

    } catch (Exception e) {
      data="";
      return data;
    }
  }

  public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
  {
    fi = new FileInputStream(xlfile);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(xlsheet);
    row = ws.getRow(rownum);
    cell = row.createCell(colnum);
    cell.setCellValue(data);
    fo = new FileOutputStream(xlfile);
    wb.write(fo);
    fi.close();
    fo.close();
  }
}