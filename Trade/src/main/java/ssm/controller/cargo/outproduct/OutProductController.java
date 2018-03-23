package ssm.controller.cargo.outproduct;

import com.sun.deploy.net.HttpResponse;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ssm.controller.BaseController;
import ssm.service.OutProductService;
import ssm.utils.DownloadUtil;
import ssm.vo.OutProductVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class OutProductController extends BaseController{

    @Resource
    private OutProductService outProductService;

    //转向输入年月的页面
    @RequestMapping("/cargo/outproduct/toedit.action")
    public String toedit() {
        return "/cargo/outproduct/OutProduct.jsp";
    }

    @RequestMapping("/cargo/outproduct/printNotemplate.action")
    public void printNotemplate(String inputDate, HttpServletResponse response) throws IOException { //inputDate 格式 yyyy-MM
        List<OutProductVO> dataList = outProductService.find(inputDate);

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = null;
        Cell cell = null;

        int rowNo = 0;
        int colNo = 1;

        //声明样式对象和字体对象
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();

        sheet.setColumnWidth(0,2*300); //列宽
        sheet.setColumnWidth(1,26*300);    //列宽BUG API底层设置不够精确256
        sheet.setColumnWidth(2,12*300);
        sheet.setColumnWidth(3,29*300);
        sheet.setColumnWidth(4,10*300);
        sheet.setColumnWidth(5,12*300);
        sheet.setColumnWidth(6,8*300);
        sheet.setColumnWidth(7,10*300);
        sheet.setColumnWidth(8,10*300);
        sheet.setColumnWidth(9,8*300);



        //大标题 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,1,9)); //开始行 结束行 开始列 结束列
        //合并单元格的内容是写在合并前的第一个单元格中
        row = sheet.createRow(rowNo++);
        row.setHeightInPoints(36);  //行高

        cell = row.createCell(1);
        cell.setCellValue(inputDate.replaceFirst("-0","-").replaceFirst("-","年")+"月份出货表");
        cell.setCellStyle(this.bigTitle(workbook,cellStyle,font));

        String[] title = new String[]{"客户","订单号","货号","数量","工厂","附件","工厂日期","船期","贸易条款"};
        row = sheet.createRow(rowNo++);
        row.setHeightInPoints(26.25f);

        //初始化
        cellStyle = workbook.createCellStyle();
        font = workbook.createFont();
        for (int i=0; i<title.length; i++) {
            cell = row.createCell(i+1);
            cell.setCellValue(title[i]);
        }

        //初始化
        cellStyle = workbook.createCellStyle();
        font = workbook.createFont();
        //处理数据
        for (int j=0; j<dataList.size(); j++){
            OutProductVO outProductVO = dataList.get(j);
            colNo = 1 ;
            row = sheet.createRow(rowNo++);
            row.setHeightInPoints(24);
            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getCustomName());
            this.text(workbook,cellStyle,font);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getContractNo());
            this.text(workbook,cellStyle,font);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getProductNo());
            this.text(workbook,cellStyle,font);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getCnumber());
            this.text(workbook,cellStyle,font);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getFactoryName());
            this.text(workbook,cellStyle,font);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getExts());
            this.text(workbook,cellStyle,font);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getDeliveryPeriod());
            this.text(workbook,cellStyle,font);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getShipTime());
            this.text(workbook,cellStyle,font);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getTradeTerms());
            this.text(workbook,cellStyle,font);
        }


//        OutputStream outputStream = new FileOutputStream(new File("d:\\testOutProductPOI.xls"));
//        workbook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();

        //下载
        DownloadUtil downloadUtil = new DownloadUtil();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        downloadUtil.download(byteArrayOutputStream,response,"出货表.xls");
        System.out.println(inputDate);
    }

    //模板开发
    @RequestMapping("/cargo/outproduct/printHSSF.action")
    public void printHSSF(String inputDate, HttpServletRequest request,HttpServletResponse response) throws IOException { //inputDate 格式 yyyy-MM
        List<OutProductVO> dataList = outProductService.find(inputDate);

        String path= request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";

        InputStream inputStream = new FileInputStream(path + "tOUTPRODUCT.xls");
        Workbook workbook = new HSSFWorkbook(inputStream); //打开一个模板文件 工作薄
        Sheet sheet = workbook.getSheetAt(0);            //获得第一个工作表
        Row row = null;
        Cell cell = null;

        int rowNo = 0;
        int colNo = 1;

        //获取模板上的单元格样式
        row = sheet.getRow(2);
        //客户样式
        cell = row.getCell(1);
        CellStyle customStyle = cell.getCellStyle();
        //订单号样式
        cell = row.getCell(2);
        CellStyle ordrtNoStyle = cell.getCellStyle();
        //货号样式
        cell = row.getCell(3);
        CellStyle productNoStyle = cell.getCellStyle();
        //数量样式
        cell = row.getCell(4);
        CellStyle cnumberStyle = cell.getCellStyle();
        //工厂样式
        cell = row.getCell(5);
        CellStyle factoryNameStyle = cell.getCellStyle();
        //附件样式
        cell = row.getCell(6);
        CellStyle extsStyle = cell.getCellStyle();
        //工厂交期样式
        cell = row.getCell(7);
        CellStyle deliveryPeriodStyle = cell.getCellStyle();
        //船期样式
        cell = row.getCell(8);
        CellStyle shipTimeStyle = cell.getCellStyle();
        //贸易条款样式
        cell = row.getCell(9);
        CellStyle tradeTermsStyle = cell.getCellStyle();

        //大标题
        row = sheet.getRow(rowNo++);    //获取一个行对象
        cell = row.getCell(colNo);    //获取一个单元格对象
        CellStyle titleStyle = cell.getCellStyle();
        cell.setCellValue(inputDate.replaceFirst("-0","-").replaceFirst("-","年")+"月份出货表");
        cell.setCellStyle(titleStyle);
        rowNo++;

        //处理数据
        for (int j=0; j<dataList.size(); j++){
            OutProductVO outProductVO = dataList.get(j);
            colNo = 1 ;
            row = sheet.createRow(rowNo++);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getCustomName());
            cell.setCellStyle(customStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getContractNo());
            cell.setCellStyle(ordrtNoStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getProductNo());
            cell.setCellStyle(productNoStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getCnumber());
            cell.setCellStyle(cnumberStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getFactoryName());
            cell.setCellStyle(factoryNameStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getExts());
            cell.setCellStyle(extsStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getDeliveryPeriod());
            cell.setCellStyle(deliveryPeriodStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getShipTime());
            cell.setCellStyle(shipTimeStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getTradeTerms());
            cell.setCellStyle(tradeTermsStyle);
        }


//        OutputStream outputStream = new FileOutputStream(new File("d:\\testOutProductPOI.xls"));
//        workbook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();

        //下载
        DownloadUtil downloadUtil = new DownloadUtil();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        downloadUtil.download(byteArrayOutputStream,response,"出货表.xls");
        System.out.println(inputDate);
    }

    //模板开发
    @RequestMapping("/cargo/outproduct/print.action")
    public void print(String inputDate, HttpServletRequest request,HttpServletResponse response) throws IOException { //inputDate 格式 yyyy-MM
        List<OutProductVO> dataList = outProductService.find(inputDate);

        String path= request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";

        InputStream inputStream = new FileInputStream(path + "tOUTPRODUCT.xlsx");
        Workbook workbook = new XSSFWorkbook(inputStream); //打开一个模板文件 工作薄2007以上
        Sheet sheet = workbook.getSheetAt(0);            //获得第一个工作表
        Row row = null;
        Cell cell = null;

        int rowNo = 0;
        int colNo = 1;

        //获取模板上的单元格样式
        row = sheet.getRow(2);
        //客户样式
        cell = row.getCell(1);
        CellStyle customStyle = cell.getCellStyle();
        //订单号样式
        cell = row.getCell(2);
        CellStyle ordrtNoStyle = cell.getCellStyle();
        //货号样式
        cell = row.getCell(3);
        CellStyle productNoStyle = cell.getCellStyle();
        //数量样式
        cell = row.getCell(4);
        CellStyle cnumberStyle = cell.getCellStyle();
        //工厂样式
        cell = row.getCell(5);
        CellStyle factoryNameStyle = cell.getCellStyle();
        //附件样式
        cell = row.getCell(6);
        CellStyle extsStyle = cell.getCellStyle();
        //工厂交期样式
        cell = row.getCell(7);
        CellStyle deliveryPeriodStyle = cell.getCellStyle();
        //船期样式
        cell = row.getCell(8);
        CellStyle shipTimeStyle = cell.getCellStyle();
        //贸易条款样式
        cell = row.getCell(9);
        CellStyle tradeTermsStyle = cell.getCellStyle();

        //大标题
        row = sheet.getRow(rowNo++);    //获取一个行对象
        cell = row.getCell(colNo);    //获取一个单元格对象
        CellStyle titleStyle = cell.getCellStyle();
        cell.setCellValue(inputDate.replaceFirst("-0","-").replaceFirst("-","年")+"月份出货表");
        cell.setCellStyle(titleStyle);
        rowNo++;

        //处理数据
        for (int j=0; j<dataList.size(); j++){
            OutProductVO outProductVO = dataList.get(j);
            colNo = 1 ;
            row = sheet.createRow(rowNo++);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getCustomName());
            cell.setCellStyle(customStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getContractNo());
            cell.setCellStyle(ordrtNoStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getProductNo());
            cell.setCellStyle(productNoStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getCnumber());
            cell.setCellStyle(cnumberStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getFactoryName());
            cell.setCellStyle(factoryNameStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getExts());
            cell.setCellStyle(extsStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getDeliveryPeriod());
            cell.setCellStyle(deliveryPeriodStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getShipTime());
            cell.setCellStyle(shipTimeStyle);

            cell = row.createCell(colNo++);
            cell.setCellValue(outProductVO.getTradeTerms());
            cell.setCellStyle(tradeTermsStyle);
        }


//        OutputStream outputStream = new FileOutputStream(new File("d:\\testOutProductPOI.xls"));
//        workbook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();

        //下载
        DownloadUtil downloadUtil = new DownloadUtil();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        downloadUtil.download(byteArrayOutputStream,response,"出货表.xlsx");
        System.out.println(inputDate);
    }

    //大标题样式
    public CellStyle bigTitle(Workbook workbook,CellStyle cellStyle,Font font) {
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);//横向居中
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//纵向居中

        return cellStyle;
    }



    //标题样式
    public CellStyle title(Workbook workbook,CellStyle cellStyle,Font font) {
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);//横向居中
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//纵向居中
        cellStyle.setFont(font);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);

        return cellStyle;
    }

    //文字样式
    public CellStyle text(Workbook workbook,CellStyle cellStyle,Font font) {
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);//横向居中
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//纵向居中
        cellStyle.setFont(font);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);

        return cellStyle;
    }
}
