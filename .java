import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Main {
    private static final String FILE_NAME = "C:\\Users\\ADMIN\\Documents\\details1.py.xlsx";
    private static Workbook workbook;
    private static Sheet sheet;
    private static Scanner scanner = new Scanner(System.in);

    private static boolean isExistingUser(String aadhaarNumber) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(FILE_NAME));
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getCell(0).getStringCellValue().equals(aadhaarNumber)) {
                inputStream.close();
                return true;
            }
        }
        inputStream.close();
        return false;
    }

    private static void newUser(String aadhaarNumber) throws IOException {
        String name, gender, phoneNumber;
        System.out.println("Enter your name:");
        name = scanner.nextLine();
        System.out.println("Enter your Gender:");
        gender = scanner.nextLine();
        System.out.println("Enter your Phone Number:");
        phoneNumber = scanner.nextLine();

        FileInputStream inputStream = new FileInputStream(new File(FILE_NAME));
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum();
        Row row = sheet.createRow(lastRowNum + 1);
        row.createCell(0).setCellValue(aadhaarNumber);
        row.createCell(1).setCellValue(name);
        row.createCell(2).setCellValue(gender);
        row.createCell(3).setCellValue(phoneNumber);

        FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        inputStream.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Enter the Aadhaar number-->:");
        String aadhaarNumber = scanner.nextLine();

        if (isExistingUser(aadhaarNumber)) {
            System.out.println("Existing user");
        } else {
            System.out.println("New user");
            newUser(aadhaarNumber);
        }
    }
}
