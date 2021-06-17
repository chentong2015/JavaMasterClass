package OtherTech.POI;

import java.io.IOException;

/**
 * POI官方教程: https://poi.apache.org/components/spreadsheet/quick-guide.html#NewWorkbook
 * 添加jar到项目的Classpath
 * poi-VERSION.jar                ==> basic 2003 Excel format
 * poi-ooxml-VERSION.jar          ==> For 2007 Excel format
 * poi-ooxml-schemas-VERSION.jar
 * xmlbeans-VERSION.jar
 * commons-collections.jar        ==> ListValuedMap
 * commons-compress.jar           ==> Zip/ZipFile
 * commons-math.jar               ==> for POIFSFileSystem
 */

/**
 * poi读取的三种模式:
 * https://blog.csdn.net/zl_momomo/article/details/80703533?utm_medium=distribute.pc_relevant_bbs_down.none-task-blog-baidujs-1
 * .nonecase&depth_1-utm_source=distribute.pc_relevant_bbs_down.none-task-blog-baidujs-1.nonecase
 * 1. SXSSF      只写: 内存中保留一定行数数据，超过行数，将索引最低的数据刷入硬盘
 * 2. eventmodel 只读: cup和内存消耗低
 * 3. usermodel  可读可写: cpu和内存消耗大
 */
public class BasePOI {

    /**
     * 1. SXSSF API ==> 构建在XSSF之上, 兼容于api的XSSF的流扩展, 适用于处理生成大的电子表格
     * 2. 设置在内存中暂存的数据row行数，超过指定的行数之后，自动的刷到硬盘中
     * 3. 可以在数据量过大时，减少存储在内存当中的数据 !!
     */
    private void testSXSSF() {
        //  SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        //  Sheet sheet = workbook.createSheet("name");
        //  Row row = sheet.createRow(1);
        //  Cell cell1 = row.createCell(1);
        //  cell1.setCellValue("value");

        //  try (FileOutputStream outputStream = new FileOutputStream("sxssf.xlsx")) {
        //      workbook.write(outputStream);
        //  } catch (IOException exception) {
        //      exception.printStackTrace();
        //  }
        //  workbook.dispose();
    }

    /**
     * XSSF and SAX (Event API) ==> 构建一个org.apache.poi.xssf.eventmodel.xssfreader的实例
     * 1. excel2003是以二进制的方式存储, 65536行 + 256列
     * 2. excel2007采用了基于XML的ooxml开放文档标准，ooxml使用XML和ZIP技术结合进行文件存储, 1048576行 + 16384列
     * 3. excel2007之后通过操作原始xml数据的方法获得数据
     */
    private void testXSSF(String filename) throws Exception {
        // try (OPCPackage pkg = OPCPackage.open(filename, PackageAccess.READ)) {
        //     XSSFReader r = new XSSFReader(pkg);
        //     SharedStringsTable sst = r.getSharedStringsTable();
        //     // XMLReader parser = fetchSheetParser(sst);
        //     // try(InputStream sheet = r.getSheetsData().next()){
        //     //     InputSource sheetSource = new InputSource(sheet);
        //     //     parser.parse(sheetSource);
        //     // }
        // }
    }

    /**
     * User API (HSSF and XSSF)
     */
    private void testOutputExcel() throws IOException {
        //  Workbook wb1 = new HSSFWorkbook();
        //  try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
        //      wb1.write(fileOut);
        //  }
        //  Workbook wb2 = new XSSFWorkbook();
        //  try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
        //      wb2.write(fileOut);
        //  }
    }

    ////////// 所有的FileInputStream都必须关闭

    /**
     * 使用Workbook  ==>  Workbook可以不需要考虑lifecycle
     * 1. 使用File对象可以降低内存消耗
     * 2. 使用InputStream需要更多的内存，因为它必须缓冲整个文件
     */
    private void testInputExcel() throws Exception {
        // create(POIFSFileSystem fs)
        // create(NPOIFSFileSystem fs)
        // create(OPCPackage pkg)
        // Workbook wb1 = WorkbookFactory.create(new File("MyExcel.xls"));
        // Workbook wb2 = WorkbookFactory.create(new FileInputStream("MyExcel.xlsx"));
    }

    /**
     * 直接使用HSSFWorkbook或者XSSFWorkbook  ====> 必须考虑控制声明周期
     * 1. 使用FileInputStream来读取流
     * 2. 需要使用NPOIFSFileSystem或OPCPackage来完全控制Workbook的生命周期 !!!
     */
    private void testReadExcel() throws Exception {
        //  HSSFWorkbook workbook1 = new HSSFWorkbook(new FileInputStream("text.xlsx")); // 不推荐
        //  NPOIFSFileSystem fs = new NPOIFSFileSystem(new File("file.xls"));
        //  HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);
        //  fs.close();
        //  NPOIFSFileSystem fs1 = new NPOIFSFileSystem(new FileInputStream("MyExcel.xlsx"));
        //  HSSFWorkbook wb3 = new HSSFWorkbook(fs.getRoot(), true);
        //  fs1.close();

        //  XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("text.xlsx")); // 不推荐
        //  OPCPackage pkg = OPCPackage.open(new File("file.xlsx"));
        //  XSSFWorkbook wb4 = new XSSFWorkbook(pkg);
        //  pkg.close();
        //  OPCPackage pkg1 = OPCPackage.open(new FileInputStream("MyExcel.xlsx"));
        //  XSSFWorkbook wb5 = new XSSFWorkbook(pkg);
        //  pkg1.close();
    }
}